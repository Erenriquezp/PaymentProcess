package ec.edu.uce.payment.services;

import ec.edu.uce.payment.models.Payable;
import ec.edu.uce.payment.models.QualifierPayment;
import jakarta.inject.Inject;

public class PaymentService {

    @Inject
    @QualifierPayment("paypal") // Select the PayPal implementation
    private Payable payable;

    public void processPayment(String account, double amount) {
        String result = payable.processPayment(account, amount, "paypal");
        System.out.println(result);
    }
}

