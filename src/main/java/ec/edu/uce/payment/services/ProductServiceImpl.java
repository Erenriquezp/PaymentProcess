package ec.edu.uce.payment.services;

import ec.edu.uce.payment.annotations.Service;
import ec.edu.uce.payment.annotations.TransactionalJpa;
import ec.edu.uce.payment.models.entities.Product;
import ec.edu.uce.payment.repositories.CrudRepository;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@Service
@TransactionalJpa
public class ProductServiceImpl implements CrudService<Product> {

    @Inject
    private CrudRepository<Product> repository;

    @Override
    public List<Product> list() {
        try {
            return repository.list();
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Product> findById(Long id) {
        try {
            return Optional.ofNullable(repository.byId(id));
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void save(Product product) {
        try {
            repository.save(product);
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void delete(Long id) {
        try {
            repository.delete(id);
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void update(Product product) {
        try {
            repository.update(product);
        } catch (Exception e) {
            throw new ServiceJdbcException("Error updating product: " + product.getName(), e);
        }
    }
}