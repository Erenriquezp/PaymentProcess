package ec.edu.uce.payment.configs;

import ec.edu.uce.payment.util.JpaUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.inject.Inject;
import java.util.logging.Logger;

@ApplicationScoped
public class ProducerResources {

    @Inject
    private Logger log;

    @Produces
    private EntityManager beanEntityManager() {
        return JpaUtil.getEntityManager();
    }

    public void closeEntityManager(@Disposes EntityManager em) {
        if (em.isOpen()) {
            em.close();
            log.info("EntityManager closed");
        }
    }

    @Produces
    private Logger beanLogger() {
        return Logger.getLogger(ProducerResources.class.getName());
    }
}
