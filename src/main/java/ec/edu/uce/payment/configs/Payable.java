package ec.edu.uce.payment.configs;

public interface Payable {

    String processPayment(String account, double amount, String paymentMethod);
}
