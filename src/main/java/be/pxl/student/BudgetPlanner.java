package be.pxl.student;

import be.pxl.student.entity.Account;
import be.pxl.student.entity.Payment;
import be.pxl.student.util.BudgetPlannerImporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class BudgetPlanner {
private static final Logger LOGGER = LogManager.getLogger(BudgetPlanner.class);

    public static void main(String[] args) {
        importCsv();
    }

    private static void getPayments(String name){
        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("paymentsdb_pu");
            entityManager = entityManagerFactory.createEntityManager();

            TypedQuery<Payment> query = entityManager.createQuery("SELECT payment from Payment payment", Payment.class);
        }
        finally {
            if (entityManager != null) {
                entityManager.close();
            }
            if (entityManagerFactory != null) {
                entityManagerFactory.close();
            }
        }
    }

    private static void importCsv(){
        LOGGER.debug("Started reading file");
        BudgetPlannerImporter importer = new BudgetPlannerImporter();
        LOGGER.debug("Stopped reading file");

        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("paymentsdb_pu");
            entityManager = entityManagerFactory.createEntityManager();

            importer.importCvs(Paths.get("./src/main/resources/account_payments_v2.csv"), entityManager);
        }
        finally {
            if (entityManager != null) {
                entityManager.close();
            }
            if (entityManagerFactory != null) {
                entityManagerFactory.close();
            }
        }
    }
}
