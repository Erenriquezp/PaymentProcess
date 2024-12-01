package ec.edu.uce.payment.models;

import ec.edu.uce.payment.configs.Payable;
import ec.edu.uce.payment.configs.QualifierPayment;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PaymentProcessor {

    @Inject
    @QualifierPayment("paypal") // Puedes cambiar el nombre a otro tipo de pago
    private Payable payable;

    public String processPayment(String account, double amount, String paymentMethod) {
        // Aquí puedes agregar más lógica para seleccionar el método de pago si es necesario
        return payable.processPayment(account, amount, paymentMethod);
    }
}
