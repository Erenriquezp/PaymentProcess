package ec.edu.uce.payment.services;

import ec.edu.uce.payment.models.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> list();
    Optional<Product> findById(Long id);
    void save(Product product);
    void delete(Long id);
    void update(Product product);

}