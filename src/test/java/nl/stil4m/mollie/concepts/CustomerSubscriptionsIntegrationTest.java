package nl.stil4m.mollie.concepts;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import nl.stil4m.mollie.Client;
import nl.stil4m.mollie.ResponseOrError;
import nl.stil4m.mollie.domain.*;
import nl.stil4m.mollie.domain.customerpayments.FirstRecurringPayment;
import nl.stil4m.mollie.domain.subpayments.ideal.CreateIdealPayment;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static nl.stil4m.mollie.TestUtil.TEST_TIMEOUT;
import static nl.stil4m.mollie.TestUtil.VALID_API_KEY;
import static nl.stil4m.mollie.TestUtil.strictClientWithApiKey;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.fail;

public class CustomerSubscriptionsIntegrationTest {

    private CustomerPayments customerPayments;
    private CustomerSubscriptions customerSubscriptions;

    @Before
    public void before() throws InterruptedException, IOException {
        Thread.sleep(TEST_TIMEOUT);
        Client client = strictClientWithApiKey(VALID_API_KEY);

        String uuid = UUID.randomUUID().toString();
        Map<String, Object> defaultMetadata = new HashMap<>();
        defaultMetadata.put("foo", "bar");
        defaultMetadata.put("id", uuid);

        String name = "Test Customer " + uuid;
        Customer customer = client.customers().create(new CreateCustomer(name, uuid + "@foobar.com", Optional.empty(), defaultMetadata)).getData();

        customerPayments = client.customerPayments(customer.getId());
        customerSubscriptions = client.customerSubscriptions(customer.getId());

        //setup selenium
        ChromeDriverManager.getInstance().setup();
    }

    @Test
    public void testList() throws IOException, URISyntaxException {
        ResponseOrError<Page<Subscription>> list = customerSubscriptions.list(Optional.empty(), Optional.empty());

        assertThat(list.getSuccess(), is(true));
    }

    @Test
    public void testCreate() throws IOException, URISyntaxException, InterruptedException {
        // https://www.mollie.com/en/docs/reference/subscriptions/create
        //create payment that creates membate (required to create subscription), this is kinda a confirm for the payment info to do the recurring payment over
        CreatePayment createIdealPayment = new CreateIdealPayment(10d, "test payment for subscription", "https://example.com/thank/you", Optional.empty(), null, null);
        FirstRecurringPayment customerPayment = new FirstRecurringPayment(createIdealPayment);
        Payment payment = customerPayments.create(customerPayment).getData();

        String paymentUrl = payment.getLinks().getPaymentUrl();

        //open paymentUrl and set it to paid

        //setup selenium
        WebDriver driver = new ChromeDriver();

        driver.get(paymentUrl);
        // Find ok button and click it
        driver.findElement(By.name("issuer")).click();

        //find "paid" radiobutton and click it
        driver.findElements(By.name("final_state")).forEach(radioButton -> {
            if (radioButton.getAttribute("value").equals("paid")) {
                radioButton.click();
            }
        });

        //submit form
        driver.findElement(By.cssSelector("#footer > button")).click();

        driver.quit();

        //check if payment is complete
        payment = customerPayments.get(payment.getId()).getData();//get new version of payment
        if (!payment.getStatus().equals("paid")) {
            fail("completing payment failed");
            return;
        }

        Double amount = 10d;
        Integer times = 2;
        String interval = "7 days";
        Date startDate = new Date();
        String description = "test subscription";
        String webhookUrl = "https://example.com/api/payment";

        CreateSubscription createSubscription = new CreateSubscription(amount, times, interval, startDate, description, null, webhookUrl);

        ResponseOrError<Subscription> result = customerSubscriptions.create(createSubscription);

        assertThat(result.getSuccess(), is(true));
        assertThat(result.getData().getId(), notNullValue());
        assertThat(result.getData().getAmount(), is(amount));
        assertThat(result.getData().getTimes(), is(times));
        assertThat(result.getData().getInterval(), is(interval));
        assertThat(result.getData().getStartDate(), notNullValue());//time will differ due to timezone-stuff
        assertThat(result.getData().getDescription(), is(description));
        assertThat(result.getData().getLinks().getWebhookUrl(), is(webhookUrl));
    }
}
