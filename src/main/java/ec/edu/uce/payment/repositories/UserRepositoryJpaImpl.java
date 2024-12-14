package ec.edu.uce.payment.repositories;

import ec.edu.uce.payment.annotations.Repository;
import ec.edu.uce.payment.models.entities.User;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@Repository
public class UserRepositoryJpaImpl implements CrudRepository<User> {
    @Inject
    private EntityManager em;

    @Override
    public List<User> list() throws Exception {
        return em.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    public User byId(Long id) throws Exception {
        return em.find(User.class, id);
    }

    @Override
    public void save(User user) throws Exception {
        if (user.getId() == null || user.getId() <= 0) {
            em.persist(user);  // Persistir un nuevo usuario
        } else {
            // Si el usuario ya tiene un ID, actualizamos (merge)
            em.merge(user);
        }
    }

    @Override
    public void update(User user) throws Exception {
        this.save(user);
    }

    @Override
    public void delete(Long id) throws Exception {
        User user = this.byId(id);
        if (user != null) {
            em.remove(user);
        }
    }
}