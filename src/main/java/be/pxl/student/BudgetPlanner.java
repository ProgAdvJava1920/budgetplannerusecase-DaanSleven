package be.pxl.student;

import be.pxl.student.util.BudgetPlannerImporter;

import java.nio.file.Path;
import java.nio.file.Paths;

public class BudgetPlanner {



    public static void main(String[] args) {
        BudgetPlannerImporter importer = new BudgetPlannerImporter();
        importer.importCvs(Paths.get("./src/main/resources/account_payments.csv"));
    }
}
