package nl.stil4m.mollie;

import nl.stil4m.mollie.concepts.*;

public class Client {

    private final DynamicClient dynamicClient;
    private final String apiKey;

    public Client(DynamicClient dynamicClient, String apiKey) {
        this.dynamicClient = dynamicClient;
        this.apiKey = apiKey;
    }

    public Payments payments() {
        return dynamicClient.payments(apiKey);
    }

    public Status status() {
        return dynamicClient.status(apiKey);
    }

    public Methods methods() {
        return dynamicClient.methods(apiKey);
    }

    public Issuers issuers() {
        return dynamicClient.issuers(apiKey);
    }
    
    public Refunds refunds() {
    	return dynamicClient.refunds(apiKey);
    }

    public PaymentRefunds paymentRefunds(String paymentId) {
        return dynamicClient.paymentRefunds(apiKey, paymentId);
    }

    public Customers customers() {
        return dynamicClient.customers(apiKey);
    }

    public CustomerPayments customerPayments(String customerId) {
        return dynamicClient.customerPayments(apiKey, customerId);
    }

    public CustomerMandates customerMandates(String customerId) {
        return dynamicClient.customerMandates(apiKey, customerId);
    }

    public CustomerSubscriptions customerSubscriptions(String customerId) {
        return dynamicClient.customerSubscriptions(apiKey, customerId);
    }
}
