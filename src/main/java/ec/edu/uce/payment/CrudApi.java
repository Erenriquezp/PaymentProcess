package ec.edu.uce.payment;

import ec.edu.uce.payment.models.entities.Product;
import ec.edu.uce.payment.models.entities.User;
import ec.edu.uce.payment.services.CrudService;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/crud")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CrudApi {
    // CRUD operaciones para productos
    @Inject
    private CrudService<Product> productService;

    @Inject
    private CrudService<User> userService;

    @POST
    @Path("/products")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProduct(Product product) {
        try {
            productService.save(product);
            return Response.status(Response.Status.CREATED)
                    .entity("Product created: " + product.getName())
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error creating product: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/products")
    public Response getAllProducts() {
        try {
            List<Product> products = productService.list();
            return Response.ok(products).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error fetching products: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/products/{id}")
    public Response getProductById(@PathParam("id") Long id) {
        try {
            Product product = productService.findById(id).orElseThrow(() ->
                    new WebApplicationException("Product not found with ID: " + id, Response.Status.NOT_FOUND));
            return Response.ok(product).build();
        } catch (WebApplicationException e) {
            return Response.status(e.getResponse().getStatus())
                    .entity(e.getMessage())
                    .build();
        }
    }

    @PUT
    @Path("/products/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProduct(@PathParam("id") Long id, Product product) {
        try {
            product.setId(id);
            productService.update(product);
            return Response.ok("Product updated: " + product.getName()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error updating product: " + e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("/products/{id}")
    public Response deleteProduct(@PathParam("id") Long id) {
        try {
            productService.delete(id);
            return Response.ok("Product deleted successfully.").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error deleting product: " + e.getMessage())
                    .build();
        }
    }

    // CRUD operaciones para usuarios

    @POST
    @Path("/users")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(User user) {
        try {
            userService.save(user);
            return Response.status(Response.Status.CREATED)
                    .entity("User created: " + user.getName())
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error creating user: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/users")
    public Response getAllUsers() {
        try {
            List<User> users = userService.list();
            return Response.ok(users).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error fetching users: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/users/{id}")
    public Response getUserById(@PathParam("id") Long id) {
        try {
            User user = userService.findById(id).orElseThrow(() ->
                    new WebApplicationException("User not found with ID: " + id, Response.Status.NOT_FOUND));
            return Response.ok(user).build();
        } catch (WebApplicationException e) {
            return Response.status(e.getResponse().getStatus())
                    .entity(e.getMessage())
                    .build();
        }
    }

    @PUT
    @Path("/users/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("id") Long id, User user) {
        try {
            user.setId(id);
            userService.update(user);
            return Response.ok("User updated: " + user.getName()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error updating user: " + e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("/users/{id}")
    public Response deleteUser(@PathParam("id") Long id) {
        try {
            userService.delete(id);
            return Response.ok("User deleted successfully.").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error deleting user: " + e.getMessage())
                    .build();
        }
    }
}
