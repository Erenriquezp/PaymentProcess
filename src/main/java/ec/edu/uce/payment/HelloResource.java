package ec.edu.uce.payment;

import ec.edu.uce.payment.models.PurchaseProcessor;
import ec.edu.uce.payment.models.entities.Product;
import ec.edu.uce.payment.models.entities.User;
import ec.edu.uce.payment.services.CrudService;
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
    private CrudService<Product> productService;

    @Inject
    private CrudService<User> userService;

    @GET
    @Path("/test")
    @Produces(MediaType.TEXT_PLAIN)
    public String testEndpoint() {
        return "Payment processing service is up and running! " + userService.findById(1L).get().getName();
    }

    @GET
    @Path("/process")
    @Produces(MediaType.TEXT_PLAIN)
    public Response processPurchase(@QueryParam("userId") Long userId, @QueryParam("paymentMethod") String paymentMethod) {
        try {
            // Fetch user by ID
            User user = userService.findById(userId).orElseThrow(() ->
                    new WebApplicationException("User not found with ID: " + userId, Response.Status.NOT_FOUND));

            // Fetch products from the database
            List<Product> products = productService.list();

            // Ensure there are products available
            if (products.isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("No products available for purchase.")
                        .build();
            }

            // Process the purchase using the PurchaseProcessor
            String result = purchaseProcessor.processPurchase(user, products, paymentMethod);
            return Response.ok(result).build();
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
    @Path("/simple")
    @Produces(MediaType.APPLICATION_JSON)
    public Response simplePurchaseTest() {
        // Retornar una respuesta simple para probar
        String result = "{\"message\": \"GET request successful!\"}";
        return Response.ok(result).build();
    }
}
