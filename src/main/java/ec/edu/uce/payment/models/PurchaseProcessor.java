package ec.edu.uce.payment.models;

import ec.edu.uce.payment.configs.Payable;
import ec.edu.uce.payment.configs.QualifierPayment;
import ec.edu.uce.payment.models.entities.Product;
import ec.edu.uce.payment.models.entities.Purchase;
import ec.edu.uce.payment.models.entities.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class PurchaseProcessor {

    @Inject
    private EntityManager entityManager;

    @Inject
    @QualifierPayment("paypal") // Puedes cambiar esto según el método de pago elegido
    private Payable payable;

    @Transactional
    public String processPurchase(User user, List<Product> products, String paymentMethod) {
        // Calcular el total
        double totalAmount = products.stream()
                .mapToDouble(Product::getPrice)
                .sum();

        // Procesar el pago
        String paymentResult = payable.processPayment(user.getEmail(), totalAmount, paymentMethod);

        // Registrar la compra
//        Purchase purchase = new Purchase();
//        purchase.setUser(user);
//        purchase.setProducts(products);
//        purchase.setPaymentMethod(paymentMethod);
//        purchase.setTotalAmount(totalAmount);
//
//        entityManager.persist(purchase);

        return paymentResult + "\nPurchase registered successfully.";
    }
}
