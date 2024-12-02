package ec.edu.uce.payment.services;

import java.util.List;
import java.util.Optional;

public interface CrudService<T> {
    List<T> list();
    Optional<T> findById(Long id);
    void save(T entity);
    void delete(Long id);
    void update(T entity);
}