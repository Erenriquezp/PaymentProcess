package ec.edu.uce.payment.repositories;

import ec.edu.uce.payment.annotations.Repository;
import ec.edu.uce.payment.models.entities.PaymentMethod;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@Repository
public class PaymentMethodRepositoryJpaImpl implements CrudRepository<PaymentMethod> {

    @Inject
    private EntityManager em;

    @Override
    public List<PaymentMethod> list() throws Exception {
        return em.createQuery("SELECT pm FROM PaymentMethod pm", PaymentMethod.class).getResultList();
    }

    @Override
    public PaymentMethod byId(Long id) throws Exception {
        return em.find(PaymentMethod.class, id);
    }

    @Override
    public void save(PaymentMethod paymentMethod) throws Exception {
        if (paymentMethod.getId() != null && paymentMethod.getId() > 0) {
            em.merge(paymentMethod);
        } else {
            em.persist(paymentMethod);
        }
    }

    @Override
    public void update(PaymentMethod paymentMethod) throws Exception {
        this.save(paymentMethod);
    }

    @Override
    public void delete(Long id) throws Exception {
        PaymentMethod paymentMethod = this.byId(id);
        if (paymentMethod != null) {
            em.remove(paymentMethod);
        }
    }
}
