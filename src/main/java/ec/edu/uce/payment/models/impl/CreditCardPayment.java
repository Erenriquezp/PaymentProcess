package ec.edu.uce.payment.models.impl;

import ec.edu.uce.payment.annotations.QualifierPayment;
import ec.edu.uce.payment.models.Payable;
import ec.edu.uce.payment.models.entities.Product;
import ec.edu.uce.payment.models.entities.User;
import jakarta.enterprise.context.ApplicationScoped;

import java.math.BigDecimal;

@QualifierPayment("creditCard")
@ApplicationScoped
public class CreditCardPayment implements Payable {

    @Override
    public String processPayment(BigDecimal amount, User user, Product product) {
        // Simulación de procesamiento de tarjeta de crédito
        return String.format(
                "Payment processed successfully!\n" +
                        "---------------------------------\n" +
                        "User Details:\n" +
                        " - Name: %s\n" +
                        " - Email: %s\n" +
                        "\n" +
                        "Product Details:\n" +
                        " - Name: %s\n" +
                        " - Price: $%.2f\n" +
                        "\n" +
                        "Payment Details:\n" +
                        " - Payment Method: Credit Card\n" +
                        " - Amount: $%.2f\n" +
                        "---------------------------------\n" +
                        "Thank you for your purchase!",
                user.getName(), user.getEmail(),
                product.getName(), product.getPrice(),
                amount
        );
    }

    @Override
    public String getPaymentMethodName() {
        return "creditCard";
    }
}
