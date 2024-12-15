package ec.edu.uce.payment.services;

public class ServiceJdbcException extends RuntimeException {
    // Excepción personalizada puede ser utilizada cuando se produce un error en una operación en la Base de datos
    public ServiceJdbcException(String message, Throwable cause) {
        super(message, cause);
    }
}