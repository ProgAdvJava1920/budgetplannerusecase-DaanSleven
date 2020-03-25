package be.pxl.student.util;

import be.pxl.student.entity.Account;
import be.pxl.student.entity.Payment;
import be.pxl.student.mapper.AccountMapper;
import be.pxl.student.mapper.CounterAccountMapper;
import be.pxl.student.mapper.PaymentMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.BufferedReader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Util class to import csv file
 */
public class BudgetPlannerImporter {

    private static final Logger LOGGER = LogManager.getLogger(BudgetPlannerImporter.class);
    private static final PathMatcher CSV_PATH_MATCHER = FileSystems.getDefault().getPathMatcher("glob:**/*.csv");

    public void importCvs(Path path, EntityManager entityManager){
        //Test if path exists
        if(!Files.exists(path)){
            LOGGER.error(path.getFileName() + " does not exist");
            return;
        }

        //Test if ends with csv
        if(!CSV_PATH_MATCHER.matches(path)){
            LOGGER.error(path.getFileName() + " is not a csv file");
            return;
        }

        EntityTransaction transaction = entityManager.getTransaction();
        HashMap<String, Account> addedAccounts = new HashMap<>();

        try(BufferedReader reader = Files.newBufferedReader(path)){
            reader.readLine();

            transaction.begin();
            String line = null;
            while((line=reader.readLine())!=null){
                String[] splitLine = line.split(",");
                Account account = AccountMapper.map(splitLine);
                Account counterAccount = CounterAccountMapper.map(splitLine);
                Payment payment = PaymentMapper.map(splitLine);

                //Counter Account
                if(!addedAccounts.containsKey(counterAccount.getIBAN())) {
                    addedAccounts.put(counterAccount.getIBAN(), counterAccount);
                    entityManager.persist(counterAccount);
                    payment.setCounterAccount(counterAccount);
                }else{
                    payment.setCounterAccount(addedAccounts.get(counterAccount.getIBAN()));
                }

                //Account
                if(!addedAccounts.containsKey(account.getIBAN())) {
                    addedAccounts.put(account.getIBAN(), account);
                    entityManager.persist(account);
                    payment.setAccount(account);
                }else if (addedAccounts.get(account.getIBAN()).getName() == null){
                    addedAccounts.get(account.getIBAN()).setName(account.getName());
                    payment.setAccount(addedAccounts.get(account.getIBAN()));
                }else{
                    payment.setAccount(addedAccounts.get(account.getIBAN()));
                }

                entityManager.persist(payment);
            }

            transaction.commit();
        }catch(Exception exception){
            LOGGER.fatal(exception.getMessage());
        }
    }
}
