package ec.edu.uce.payment.services;

import ec.edu.uce.payment.configs.Service;
import ec.edu.uce.payment.interceptors.TransactionalJpa;
import ec.edu.uce.payment.models.entities.User;
import ec.edu.uce.payment.repositories.CrudRepository;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@Service
@TransactionalJpa
public class UserService implements CrudService<User> {

    @Inject
    private CrudRepository<User> repository;

    @Override
    public List<User> list() {
        try {
            return repository.list();
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        try {
            return Optional.ofNullable(repository.byId(id));
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void save(User user) {
        try {
            repository.save(user);
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
    public void update(User user) {
        try {
            repository.update(user);
        } catch (Exception e) {
            throw new ServiceJdbcException("Error updating user: " + user.getName(), e);
        }
    }
}
