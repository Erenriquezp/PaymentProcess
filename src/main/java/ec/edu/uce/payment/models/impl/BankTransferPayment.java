package ec.edu.uce.payment.models.impl;

import ec.edu.uce.payment.annotations.QualifierPayment;
import ec.edu.uce.payment.models.Payable;
import ec.edu.uce.payment.models.entities.Product;
import ec.edu.uce.payment.models.entities.User;
import jakarta.enterprise.context.RequestScoped;

import java.math.BigDecimal;

@QualifierPayment("bankTransfer")
@RequestScoped
public class BankTransferPayment implements Payable {

    @Override
    public String processPayment(BigDecimal amount, User user, Product product) {
        // Simulación de transferencia bancaria
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
                        " - Payment Method: Bank Transfer\n" +
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
        return "bankTransfer";
    }
}
