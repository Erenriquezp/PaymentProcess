package ec.edu.uce.payment.services;

import ec.edu.uce.payment.annotations.Service;
import ec.edu.uce.payment.models.Payable;
import ec.edu.uce.payment.models.entities.Payment;
import ec.edu.uce.payment.models.entities.PaymentMethod;
import ec.edu.uce.payment.models.entities.Product;
import ec.edu.uce.payment.models.entities.User;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PaymentProcessor {

    @Inject
    private CrudService<Payment> paymentService;
    @Inject
    private CrudService<PaymentMethod> paymentMethodService;

    @Inject
    @Any
    private Instance<Payable> processors; // Contenedor que almacena objetos que implementan la interfaz Payable

    @PostConstruct
    public void init() {
        // Verifica si existen procesadores de pago inyectados
        System.out.println("*********----------------------------Processors injected: " + (processors != null));
        if (processors.isUnsatisfied()) {
            System.out.println("No payment processors found.");
        }
    }

    public String processPayment(User user, BigDecimal totalAmount, List<Product> products, String paymentMethodId) {

        // Obtener el método de pago desde la base de datos
        PaymentMethod method = paymentMethodService.list()
                .stream()
                .filter(pm -> pm.getDescription().equalsIgnoreCase(paymentMethodId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No payment method found with id: " + paymentMethodId));

        // Crear y persistir el pago
        Payment payment = new Payment();
        payment.setUser(user);
        payment.setAmount(totalAmount);
        payment.setMethod(method);

        // Asociar productos al pago
        products.forEach(payment::addProduct); // Usamos el método addProduct para asociar correctamente

        // Guardar el pago en la base de datos
        paymentService.save(payment);

        // Procesar el pago a través del método específico
        return processors.stream()
                .filter(processor -> processor.getPaymentMethodName().equalsIgnoreCase(method.getDescription()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No processor found for payment method: " + method.getId()))
                .processPayment(totalAmount, user, products);
    }
}
