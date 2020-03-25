package be.pxl.student.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class EntityManagerUtil implements ServletContextListener {
    private static EntityManagerFactory entityManagerFactory;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        entityManagerFactory = Persistence.createEntityManagerFactory("studentdb_pu");
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }

    public static EntityManager createEntityManager() {
        if (entityManagerFactory == null) {
            throw new IllegalStateException("Context is not initialized yet.");
        }
        return entityManagerFactory.createEntityManager();
    }
}
