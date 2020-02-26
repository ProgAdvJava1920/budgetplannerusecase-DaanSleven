package be.pxl.student;

import be.pxl.student.util.BudgetPlannerImporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.nio.file.Paths;

public class BudgetPlanner {
private static final Logger LOGGER = LogManager.getLogger(BudgetPlanner.class);

    public static void main(String[] args) {
        LOGGER.debug("Started reading file");
        BudgetPlannerImporter importer = new BudgetPlannerImporter();
        importer.importCvs(Paths.get("./src/main/resources/account_payments.csv"));
        LOGGER.debug("Stopped reading file");
    }
}
