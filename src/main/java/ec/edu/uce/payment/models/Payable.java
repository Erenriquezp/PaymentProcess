package ec.edu.uce.payment.models;

import ec.edu.uce.payment.models.entities.Product;
import ec.edu.uce.payment.models.entities.User;

import java.math.BigDecimal;
import java.util.List;

public interface Payable {

    String processPayment(BigDecimal amount, User user, List<Product> products);

    String getPaymentMethodName();
}
