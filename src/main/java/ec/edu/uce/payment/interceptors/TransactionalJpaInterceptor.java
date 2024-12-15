package ec.edu.uce.payment.interceptors;

import ec.edu.uce.payment.annotations.TransactionalJpa;
import ec.edu.uce.payment.services.ServiceJdbcException;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import jakarta.persistence.EntityManager;

import java.util.logging.Logger;

@TransactionalJpa
@Interceptor
public class TransactionalJpaInterceptor {
    // Interceptor en Jakarta EE para manejar transacciones de base de datos de manera automática.

    @Inject
    private EntityManager em;

    @Inject
    private Logger log;

    @AroundInvoke
    private Object transactional(InvocationContext invocationContext) throws Exception {
        try {
            log.info(" -----> Transactional started "+ invocationContext.getMethod().getName()
                    + " of the class: " + invocationContext.getMethod().getDeclaringClass());
            em.getTransaction().begin();
            Object result = invocationContext.proceed();
            em.getTransaction().commit();
            log.info(" -----> Transactional finished "+ invocationContext.getMethod().getName()
                    + " of the class: " + invocationContext.getMethod().getDeclaringClass());
            return result;
        } catch (ServiceJdbcException e) {
            em.getTransaction().rollback();
            throw e;
        }
    }
}