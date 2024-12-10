package ec.edu.uce.payment.models;

import ec.edu.uce.payment.models.entities.Product;
import ec.edu.uce.payment.models.entities.User;

import java.math.BigDecimal;

public interface Payable {

    String processPayment(BigDecimal amount, User user, Product product);
    String getPaymentMethodName();

}
