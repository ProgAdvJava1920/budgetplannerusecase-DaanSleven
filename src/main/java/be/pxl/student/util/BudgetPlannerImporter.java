package be.pxl.student.util;

import be.pxl.student.entity.Account;
import be.pxl.student.entity.Payment;
import be.pxl.student.mapper.AccountMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Util class to import csv file
 */
public class BudgetPlannerImporter {

    private static final Logger LOGGER = LogManager.getLogger(BudgetPlannerImporter.class);
    private static final PathMatcher CSV_PATH_MATCHER = FileSystems.getDefault().getPathMatcher("glob:**/*.csv");

    public void importCvs(Path path){
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

        try(BufferedReader reader = Files.newBufferedReader(path)){
            reader.readLine();

            String line = null;
            while((line=reader.readLine())!=null){
                try {
                    Account account = AccountMapper.map(line);
                    LOGGER.debug(account.toString());
                }catch(IllegalArgumentException exception){
                    LOGGER.fatal("Error mapping line: " + exception.getMessage());
                }
            }
        }catch(Exception exception){
            LOGGER.fatal(exception.getMessage());
        }
    }
}
