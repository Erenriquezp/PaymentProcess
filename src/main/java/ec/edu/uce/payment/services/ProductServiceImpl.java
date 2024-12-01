package ec.edu.uce.payment.services;

import ec.edu.uce.payment.configs.Service;
import ec.edu.uce.payment.interceptors.TransactionalJpa;
import ec.edu.uce.payment.models.entities.Product;
import ec.edu.uce.payment.repositories.CrudRepository;
import ec.edu.uce.payment.repositories.RepositoryJpa;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@Service
//@ProductServiceMain
@TransactionalJpa
public class ProductServiceImpl implements ProductService {

    @Inject
    @RepositoryJpa
    private CrudRepository<Product> repositoryJdbc;

    @Override
    public List<Product> list() {
        try {
            return repositoryJdbc.list();
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Product> findById(Long id) {
        try {
            return Optional.ofNullable(repositoryJdbc.byId(id));
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void save(Product product) {
        try {
            repositoryJdbc.save(product);
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void delete(Long id) {
        try {
            repositoryJdbc.delete(id);
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void update(Product product) {

    }

}