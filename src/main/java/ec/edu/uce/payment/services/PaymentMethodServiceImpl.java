package ec.edu.uce.payment.services;

import ec.edu.uce.payment.annotations.Service;
import ec.edu.uce.payment.annotations.TransactionalJpa;
import ec.edu.uce.payment.models.entities.PaymentMethod;
import ec.edu.uce.payment.repositories.CrudRepository;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@Service
@TransactionalJpa
public class PaymentMethodServiceImpl implements CrudService<PaymentMethod> {

    @Inject
    private CrudRepository<PaymentMethod> repository;

    @Override
    public List<PaymentMethod> list() {
        try {
            return repository.list();
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<PaymentMethod> findById(Long id) {
        try {
            return Optional.ofNullable(repository.byId(id));
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void save(PaymentMethod paymentMethod) {
        try {
            repository.save(paymentMethod);
        } catch (Exception e) {
            throw new ServiceJdbcException("Error saving payment method: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            repository.delete(id);
        } catch (Exception e) {
            throw new ServiceJdbcException("Error deleting payment method: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(PaymentMethod paymentMethod) {
        try {
            repository.update(paymentMethod);
        } catch (Exception e) {
            throw new ServiceJdbcException("Error updating payment method: " + paymentMethod.getId(), e);
        }
    }
}
