package ec.edu.uce.payment.services;

import ec.edu.uce.payment.annotations.Service;
import ec.edu.uce.payment.annotations.TransactionalJpa;
import ec.edu.uce.payment.models.entities.Payment;
import ec.edu.uce.payment.repositories.CrudRepository;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@Service
@TransactionalJpa
public class PaymentServiceImpl implements CrudService<Payment> {

    @Inject
    private CrudRepository<Payment> repository;

    @Override
    public List<Payment> list() {
        try {
            return repository.list();
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Payment> findById(Long id) {
        try {
            return Optional.ofNullable(repository.byId(id));
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void save(Payment payment) {
        try {
            repository.save(payment);
        } catch (Exception e) {
            throw new ServiceJdbcException("Error saving payment: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            repository.delete(id);
        } catch (Exception e) {
            throw new ServiceJdbcException("Error deleting payment: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(Payment payment) {
        try {
            repository.update(payment);
        } catch (Exception e) {
            throw new ServiceJdbcException("Error updating payment: " + e.getMessage(), e);
        }
    }
}
