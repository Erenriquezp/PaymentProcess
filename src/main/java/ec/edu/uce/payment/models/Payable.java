package ec.edu.uce.payment.models;

public interface Payable {

    String processPayment(String account, double amount, String paymentMethod);
}
