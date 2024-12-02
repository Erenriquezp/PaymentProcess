package ec.edu.uce.payment.repositories;


import ec.edu.uce.payment.configs.Repository;
import ec.edu.uce.payment.models.entities.Product;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@Repository
public class ProductRepositoryJpaImpl implements CrudRepository<Product>{

    @Inject
    private EntityManager em;

    @Override
    public List<Product> list() throws Exception {
        return em.createQuery("SELECT p FROM Product p", Product.class).getResultList();
    }

    @Override
    public Product byId(Long id) throws Exception {
        return em.find(Product.class, id);
    }

    @Override
    public void save(Product product) throws Exception {
        if (product.getId() != null && product.getId() > 0) {
            em.merge(product);
        } else {
            em.persist(product);
        }
    }

    @Override
    public void update(Product product) throws Exception {
        this.save(product);
    }

    @Override
    public void delete(Long id) throws Exception {
        Product product = this.byId(id);
        em.remove(product);
    }
}