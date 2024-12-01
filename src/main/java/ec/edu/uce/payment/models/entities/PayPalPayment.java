package ec.edu.uce.payment.models.entities;

import ec.edu.uce.payment.configs.Payable;
import ec.edu.uce.payment.configs.QualifierPayment;
import jakarta.enterprise.context.ApplicationScoped;

@QualifierPayment("paypal")
@ApplicationScoped
public class PayPalPayment implements Payable {

    @Override
    public String processPayment(String account, double amount, String paymentMethod) {
        // Logic to process payment via PayPal
        return "Payment of " + amount + " processed via PayPal from account " + account;
    }
}
