package ec.edu.uce.payment.repositories;

import ec.edu.uce.payment.annotations.Repository;
import ec.edu.uce.payment.models.entities.Payment;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@Repository
public class PaymentRepositoryJpaImpl implements CrudRepository<Payment> {

    @Inject
    private EntityManager em;

    @Override
    public List<Payment> list() throws Exception {
        return em.createQuery("SELECT p FROM Payment p", Payment.class).getResultList();
    }

    @Override
    public Payment byId(Long id) throws Exception {
        return em.find(Payment.class, id);
    }

    @Override
    public void save(Payment payment) throws Exception {
        if (payment.getId() != null && payment.getId() > 0) {
            em.merge(payment);
        } else {
            em.persist(payment);
        }
    }

    @Override
    public void update(Payment payment) throws Exception {
        this.save(payment);
    }

    @Override
    public void delete(Long id) throws Exception {
        Payment payment = this.byId(id);
        if (payment != null) {
            em.remove(payment);
        }
    }
}
