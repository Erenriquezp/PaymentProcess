package ec.edu.uce.payment.models.impl;

import ec.edu.uce.payment.annotations.QualifierPayment;
import ec.edu.uce.payment.models.Payable;
import ec.edu.uce.payment.models.entities.Product;
import ec.edu.uce.payment.models.entities.User;
import jakarta.enterprise.context.ApplicationScoped;

import java.math.BigDecimal;
import java.util.List;

@QualifierPayment("creditCard")
@ApplicationScoped
public class CreditCardPayment implements Payable {

    @Override
    public String processPayment(BigDecimal amount, User user, List<Product> products) {
        // Simulación de procesamiento de tarjeta de crédito
        StringBuilder productDetails = new StringBuilder();
        products.forEach(product -> productDetails.append(
                String.format(" - Name: %s | Price: $%.2f%n", product.getName(), product.getPrice())));

        return String.format(
                """
                        Payment processed successfully!
                        ---------------------------------
                        User Details:
                         - Name: %s
                         - Email: %s
                        
                        Products:
                        %s\
                        
                        Payment Details:
                         - Payment Method: Credit Card
                         - Total Amount: $%.2f
                        ---------------------------------
                        Thank you for your purchase!""",
                user.getName(), user.getEmail(),
                productDetails,
                amount
        );
    }

    @Override
    public String getPaymentMethodName() {
        return "creditCard";
    }
}
