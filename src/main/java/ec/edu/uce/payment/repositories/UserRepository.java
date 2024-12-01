package ec.edu.uce.payment.repositories;


import ec.edu.uce.payment.models.entities.User;

public interface UserRepository extends CrudRepository<User> {
    User byUsername(String username) throws Exception;

}