package ec.edu.uce.payment.models.impl;

import ec.edu.uce.payment.annotations.QualifierPayment;
import ec.edu.uce.payment.models.Payable;
import ec.edu.uce.payment.models.entities.Product;
import ec.edu.uce.payment.models.entities.User;
import jakarta.enterprise.context.RequestScoped;

import java.math.BigDecimal;
import java.util.List;

@QualifierPayment("bankTransfer")
@RequestScoped
public class BankTransferPayment implements Payable {

    @Override
    public String processPayment(BigDecimal amount, User user, List<Product> products) {
        // Simulación de transferencia bancaria
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
                        " - Payment Method: Bank Transfer\n" +
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
        return "bankTransfer";
    }
}
