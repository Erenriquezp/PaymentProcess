package ec.edu.uce.payment.configs;

import ec.edu.uce.payment.util.JpaUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.inject.Inject;
import java.util.logging.Logger;

@ApplicationScoped // Una única instancia de esta clase se crea durante el inicio de la aplicación y se mantiene hasta su finalización.
public class ProducerResources {

    @Inject // Logger para hacer el registro de eventos
    private Logger log;

    @Produces
    @RequestScoped // Un nuevo EntityManager será creado para cada solicitud
    private EntityManager beanEntityManager() {
        // No es necesario invocar el método explícitamente
        // El contenedor CDI se encarga de crear e inyectar el EntityManager cuando sea necesario.
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
