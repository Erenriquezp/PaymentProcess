package ec.edu.uce.payment;

import ec.edu.uce.payment.models.entities.Payment;
import ec.edu.uce.payment.models.entities.PaymentMethod;
import ec.edu.uce.payment.services.PaymentProcessor;
import ec.edu.uce.payment.models.entities.Product;
import ec.edu.uce.payment.models.entities.User;
import ec.edu.uce.payment.services.CrudService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Path("/purchase")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HelloResource {

    @Inject
    private CrudService<Product> productService;

    @Inject
    private CrudService<User> userService;

    @Inject
    private CrudService<Payment> paymentService;

    @Inject
    private CrudService<PaymentMethod> paymentMethodService;

    @Inject
    PaymentProcessor factory;

    // Endpoint de prueba para crear productos, usuarios y métodos de pago
    @GET
    @Path("/test")
    @Produces(MediaType.TEXT_PLAIN)
    public String testEndpoint() {

        if (productService.list().isEmpty()) {
            // Creando productos de ejemplo
            Product product1 = new Product();
            product1.setName("Miku Figure");
            product1.setPrice(BigDecimal.valueOf(500));

            Product product2 = new Product();
            product2.setName("Kim Jisoo Poster");
            product2.setPrice(BigDecimal.valueOf(150));

            productService.save(product1);
            productService.save(product2);
        }
        if (userService.list().isEmpty()) {
            // Creando usuario de ejemplo
            User user1 = new User();
            user1.setName("Ichika Nakano");
            user1.setEmail("ichika@gmail.com");

            User user2 = new User();
            user2.setName("Kim Jisoo");
            user2.setEmail("kimJiSoo@gmail.com");

            userService.save(user2);
        }
        // Creando métodos de pago por defecto
        PaymentMethod paypal = new PaymentMethod();
        paypal.setId(1L);
        paypal.setDescription("paypal");

        PaymentMethod creditCard = new PaymentMethod();
        creditCard.setId(2L);
        creditCard.setDescription("creditCard");

        PaymentMethod bankTransfer = new PaymentMethod();
        bankTransfer.setId(3L);
        bankTransfer.setDescription("bankTransfer");

        paymentMethodService.save(paypal);
        paymentMethodService.save(creditCard);
        paymentMethodService.save(bankTransfer);

        // Listar métodos de pago disponibles
        List<PaymentMethod> paymentMethods = paymentMethodService.list();

        // Mensaje de respuesta
        List<Product> products = productService.list();
        List<User> users = userService.list();

        StringBuilder response = getResponse(products, users, paymentMethods);

        return response.toString();
    }

    // Endpoint para procesar una compra con múltiples productos
    @GET
    @Path("/process")
    @Produces(MediaType.TEXT_PLAIN)
    public Response processPurchase(
            @QueryParam("userId") Long userId,
            @QueryParam("productIds") String productIds,
            @QueryParam("paymentMethod") String paymentMethod) {

        try {
            // Encontrar el usuario por el ID
            User user = userService.findById(userId).orElseThrow(() ->
                    new WebApplicationException("User not found with ID: " + userId, Response.Status.NOT_FOUND));

            // Encontrar los productos por sus ID's
            List<Product> products = new ArrayList<>();
            for (String productIdStr : productIds.split(",")) {
                Long productId = Long.parseLong(productIdStr.trim());
                Product product = productService.findById(productId).orElseThrow(() ->
                        new WebApplicationException("Product not found with ID: " + productId, Response.Status.NOT_FOUND));
                products.add(product);
            }

            products.forEach(System.out::println);
            // Calcular el monto total
            BigDecimal totalAmount = products.stream()
                    .map(Product::getPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            // Procesar el pago
            String response = factory.processPayment(user, totalAmount, products, paymentMethod) +
                    "\n---------------------------------\n" +
                    "\nPara ver los pagos ve al path payments:\n" +
                    "http://localhost:8080/payment-1.0-SNAPSHOT/api/purchase/payments";

            return Response.ok(response).build();

        } catch (WebApplicationException e) {
            return Response.status(e.getResponse().getStatus())
                    .entity(e.getMessage())
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error processing purchase: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/payments")
    public Response listPayments() {
        try {
            // Listar todos los pagos
            List<Payment> payments = paymentService.list();
            return Response.ok(payments).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error retrieving payments: " + e.getMessage())
                    .build();
        }
    }

    private static StringBuilder getResponse(List<Product> products, List<User> users, List<PaymentMethod> paymentMethods) {
        StringBuilder response = new StringBuilder();
        response.append("************** Datos de Ejemplo **************\n\n");
        response.append("Productos Disponibles:\n");
        response.append("---------------------------------------------\n");
        for (Product product : products) {
            response.append(String.format(
                    """
                             - ID: %d
                               Nombre: %s
                               Precio: $%.2f
                            ---------------------------------------------
                            """,
                    product.getId(), product.getName(), product.getPrice()
            ));
        }

        response.append("Usuarios Registrados:\n");
        response.append("---------------------------------------------\n");
        for (User user : users) {
            response.append(String.format(
                    """
                             - ID: %d
                               Nombre: %s
                               Email: %s
                            ---------------------------------------------
                            """,
                    user.getId(), user.getName(), user.getEmail()
            ));
        }

        response.append("Métodos de Pago Disponibles:\n");
        response.append("---------------------------------------------\n");
        for (PaymentMethod method : paymentMethods) {
            response.append(String.format(" - Nombre: %s\n", method.getDescription()));
        }

        response.append("\n************* Prueba del Programa ************\n");
        response.append("Para realizar una prueba de compra, utiliza el siguiente formato en la URL:\n");
        response.append(" /purchase/process?userId=<USER_ID>&productIds=<PRODUCT_IDS>&paymentMethod=<METHOD>\n\n");
        response.append("Ejemplo:\n");
        response.append(" /purchase/process?userId=1&productIds=1,2&paymentMethod=paypal\n");
        response.append("***********************************************");
        return response;
    }
}
