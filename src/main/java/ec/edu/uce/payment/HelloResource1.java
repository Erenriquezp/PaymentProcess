package ec.edu.uce.payment;

import ec.edu.uce.payment.homework.EmployeeService;
import ec.edu.uce.payment.homework.Employee;
import ec.edu.uce.payment.homework.Address;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/employee")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HelloResource1 {

    @Inject
    private EmployeeService employeeAddressService;  // Inyectamos el servicio de empleados

    // Endpoint para probar la relación entre empleados y direcciones
    @GET
    @Path("/testEmployees")
    public Response testEmployeesWithAddresses() {

        try {
            // Obtenemos todos los empleados con sus direcciones
            List<Employee> employees = employeeAddressService.getEmployeesWithAddresses();

            StringBuilder response = new StringBuilder();
            response.append("************** Empleados y Direcciones **************\n\n");

            // Iteramos sobre los empleados para mostrar sus direcciones
            for (Employee employee : employees) {
                response.append(String.format("Empleado: %s\n", employee.getName()));
                response.append("Direcciones:\n");
                for (Address address : employee.getAddresses()) {
                    response.append(String.format(" - Calle: %s, Ciudad: %s\n", address.getStreet(), address.getCity()));
                }
                response.append("---------------------------------------------\n");
            }

            return Response.ok(response.toString()).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error retrieving employees and addresses: " + e.getMessage())
                    .build();
        }
    }

    // Métodos para procesar compra y pagos como antes...
}
