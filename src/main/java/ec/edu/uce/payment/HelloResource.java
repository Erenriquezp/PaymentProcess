package ec.edu.uce.payment;

import ec.edu.uce.payment.models.PurchaseProcessor;
import ec.edu.uce.payment.models.entities.Product;
import ec.edu.uce.payment.models.entities.User;
import ec.edu.uce.payment.services.ProductServiceImpl;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

@Path("/purchase")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HelloResource {

    @Inject
    private PurchaseProcessor purchaseProcessor;

    @Inject
    private ProductServiceImpl productService;

    @GET
    @Path("/test")
    @Produces("text/plain")
    public String testEndpoint() {
        return "Payment processing service is up and running! " + productService.findById(1L).get().getName();
    }

    @POST
    @Path("/process")
    public Response processPurchase() {
        // Create an example user
        User user = new User();
        user.setName("Asuka Langley");
        user.setEmail("asuka@anime.com");

        // Create example products
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setName("Anime Figure");
        product1.setPrice(50.0);

        Product product2 = new Product();
        product2.setName("Manga Volume 1");
        product2.setPrice(15.0);

        products.add(product1);
        products.add(product2);

        // Process the purchase using the PurchaseProcessor
        try {
            String result = purchaseProcessor.processPurchase(user, products, "paypal");
            return Response.ok(result).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error processing purchase: " + e.getMessage())
                    .build();
        }
    }
}
