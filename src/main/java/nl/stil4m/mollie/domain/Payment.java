package nl.stil4m.mollie.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Payment {

    private final String resource;
    private final String id;
    private final String profileId;
    private final String mode;
    private final String customerId;
    private final String recurringType;
    private final Date createdDatetime;
    private final String status;
    private final Date paidDatetime;
    private final Date cancelledDatetime;
    private final Date expiredDatetime;
    private final Date failedDatetime;
    private final Double amount;
    private final Double amountRefunded;
    private final Double amountRemaining;
    private final String description;
    private final String method;
    private final Map<String, Object> details;
    private final Links links;
    private final Map<String, Object> metadata;
    private final String locale;
    private final String countryCode;
    private final String expiryPeriod;
    private final Optional<String> mandateId;
    private final Optional<Boolean> canBeCancelled;
    private final String subscriptionId;

    public Payment(@JsonProperty("resource") String resource,
            @JsonProperty("id") String id,
            @JsonProperty("profileId") String profileId,
            @JsonProperty("mode") String mode,
            @JsonProperty("customerId") String customerId,
            @JsonProperty("recurringType") String recurringType,
            @JsonProperty("createdDatetime") Date createdDatetime,
            @JsonProperty("status") String status,
            @JsonProperty("paidDatetime") Date paidDatetime,
            @JsonProperty("cancelledDatetime") Date cancelledDatetime,
            @JsonProperty("expiredDatetime") Date expiredDatetime,
            @JsonProperty("amount") Double amount,
            @JsonProperty("amountRefunded") Double amountRefunded,
            @JsonProperty("amountRemaining") Double amountRemaining,
            @JsonProperty("description") String description,
            @JsonProperty("method") String method,
            @JsonProperty("details") Map<String, Object> details,
            @JsonProperty("links") Links links,
            @JsonProperty("metadata") Map<String, Object> metadata,
            @JsonProperty("locale") String locale,
            @JsonProperty("countryCode") String countryCode,
            @JsonProperty("expiryPeriod") String expiryPeriod,
            @JsonProperty("mandateId") Optional<String> mandateId,
            @JsonProperty("canBeCancelled") Optional<Boolean> canBeCancelled,
            @JsonProperty("failedDatetime") Date failedDatetime,
            @JsonProperty("subscriptionId") String subscriptionId) {
        this.resource = resource;
        this.id = id;
        this.profileId = profileId;
        this.mode = mode;
        this.customerId = customerId;
        this.recurringType = recurringType;
        this.createdDatetime = createdDatetime;
        this.status = status;
        this.paidDatetime = paidDatetime;
        this.cancelledDatetime = cancelledDatetime;
        this.expiredDatetime = expiredDatetime;
        this.amount = amount;
        this.amountRefunded = amountRefunded;
        this.amountRemaining = amountRemaining;
        this.description = description;
        this.method = method;
        this.details = details;
        this.links = links;
        this.metadata = metadata;
        this.locale = locale;
        this.countryCode = countryCode;
        this.expiryPeriod = expiryPeriod;
        this.mandateId = mandateId;
        this.canBeCancelled = canBeCancelled;
        this.failedDatetime = failedDatetime;
        this.subscriptionId = subscriptionId;
    }

    public String getResource() {
        return resource;
    }

    public String getId() {
        return id;
    }

    public String getProfileId() {
        return profileId;
    }

    public String getMode() {
        return mode;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getRecurringType() {
        return recurringType;
    }

    public Date getCreatedDatetime() {
        return createdDatetime;
    }

    public String getStatus() {
        return status;
    }

    public Date getPaidDatetime() {
        return paidDatetime;
    }

    public Date getCancelledDatetime() {
        return cancelledDatetime;
    }

    public Date getExpiredDatetime() {
        return expiredDatetime;
    }

    public Double getAmount() {
        return amount;
    }

    public Double getAmountRefunded() {
        return amountRefunded;
    }

    public Double getAmountRemaining() {
        return amountRemaining;
    }

    public String getDescription() {
        return description;
    }

    public String getMethod() {
        return method;
    }

    public Map<String, Object> getDetails() {
        return details;
    }

    public Links getLinks() {
        return links;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public String getLocale() {
        return locale;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getExpiryPeriod() {
        return expiryPeriod;
    }

    public Optional<String> getMandateId() {
        return mandateId;
    }

    public Optional<Boolean> geCanBeCancelled() {
        return canBeCancelled;
    }

    public Date getFailedDatetime() {
        return failedDatetime;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }
}
