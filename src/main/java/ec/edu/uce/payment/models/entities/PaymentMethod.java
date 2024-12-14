package ec.edu.uce.payment.models.entities;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "payment_methods")
public class PaymentMethod {

    @Id
    private Long id; // Ejemplo: 'paypal', 'creditCard', 'bankTransfer'

    @Column(nullable = false, length = 100)
    private String description; // Nombre o descripción del método

    @Lob
    private String details; // Información adicional (por ejemplo, configuración JSON)

    public PaymentMethod() {
    }

    public PaymentMethod(Long id, String description, String details) {
        this.id = id;
        this.description = description;
        this.details = details;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentMethod that = (PaymentMethod) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(description, that.description) &&
                Objects.equals(details, that.details);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, details);
    }
}