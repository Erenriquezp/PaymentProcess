package ec.edu.uce.payment.models.impl;

import ec.edu.uce.payment.annotations.QualifierPayment;
import ec.edu.uce.payment.models.Payable;
import ec.edu.uce.payment.models.entities.Product;
import ec.edu.uce.payment.models.entities.User;
import jakarta.enterprise.context.ApplicationScoped;

import java.math.BigDecimal;

@ApplicationScoped
@QualifierPayment("paypal")
public class PayPalPayment implements Payable {

    @Override
    public String processPayment(BigDecimal amount, User user, Product  product) {
        // Simulación de integración con la API de PayPal
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
                        " - Payment Method: PayPal\n" +
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
        return "paypal";
    }
}
