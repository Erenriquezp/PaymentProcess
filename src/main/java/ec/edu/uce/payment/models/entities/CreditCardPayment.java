package ec.edu.uce.payment.models.entities;

import ec.edu.uce.payment.configs.Payable;
import ec.edu.uce.payment.configs.QualifierPayment;
import jakarta.enterprise.context.ApplicationScoped;

@QualifierPayment("creditCard")
@ApplicationScoped
public class CreditCardPayment implements Payable {

    @Override
    public String processPayment(String account, double amount, String paymentMethod) {
        // Logic to process payment via credit card
        return "Payment of " + amount + " processed via Credit Card from account " + account;
    }
}
