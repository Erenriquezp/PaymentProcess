package ec.edu.uce.payment.repositories;


import ec.edu.uce.payment.annotations.Repository;
import ec.edu.uce.payment.models.entities.Product;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;

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
            // Verificar si el producto existe antes de hacer merge
            Product existingProduct = em.find(Product.class, product.getId());
            if (existingProduct != null) {
                em.merge(product);
            } else {
                throw new EntityNotFoundException("Product not found for ID: " + product.getId());
            }
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