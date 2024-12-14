package ec.edu.uce.payment.models.impl;

import ec.edu.uce.payment.annotations.QualifierPayment;
import ec.edu.uce.payment.models.Payable;
import ec.edu.uce.payment.models.entities.Product;
import ec.edu.uce.payment.models.entities.User;
import jakarta.enterprise.context.ApplicationScoped;

import java.math.BigDecimal;
import java.util.List;

@ApplicationScoped
@QualifierPayment("paypal")
public class PayPalPayment implements Payable {

    @Override
    public String processPayment(BigDecimal amount, User user, List<Product> products) {
        // Simulación de integración con la API de PayPal
        StringBuilder productDetails = new StringBuilder();
        products.forEach(product -> productDetails.append(
                String.format(" - Name: %s | Price: $%.2f%n", product.getName(), product.getPrice())));

        return String.format(
                "Payment processed successfully!\n" +
                        "---------------------------------\n" +
                        "User Details:\n" +
                        " - Name: %s\n" +
                        " - Email: %s\n" +
                        "\n" +
                        "Products:\n%s" +
                        "\n" +
                        "Payment Details:\n" +
                        " - Payment Method: PayPal\n" +
                        " - Total Amount: $%.2f\n" +
                        "---------------------------------\n" +
                        "Thank you for your purchase!",
                user.getName(), user.getEmail(),
                productDetails,
                amount
        );
    }

    @Override
    public String getPaymentMethodName() {
        return "paypal";
    }
}
