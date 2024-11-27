package ec.edu.uce.payment.models.entities;

import ec.edu.uce.payment.models.Payable;
import ec.edu.uce.payment.models.QualifierPayment;
import jakarta.enterprise.context.ApplicationScoped;

@QualifierPayment("bankTransfer")
@ApplicationScoped
public class BankTransferPayment implements Payable {

    @Override
    public String processPayment(String account, double amount, String paymentMethod) {
        // Logic to process payment via bank transfer
        return "Payment of " + amount + " processed via Bank Transfer from account " + account;
    }
}
