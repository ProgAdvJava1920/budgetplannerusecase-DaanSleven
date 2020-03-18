package be.pxl.student.mapper;

import be.pxl.student.entity.Account;
import be.pxl.student.entity.Payment;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AccountMapper {
    public static Account map(String[] splitInput) {

        //Check if splitInput has enough fields
        if (splitInput.length != 7) {
            throw new IllegalArgumentException("Input should contain 7 fields");
        }

        //Make account
        Account account = new Account();
        account.setName(splitInput[0]);
        account.setIBAN(splitInput[1]);

        return account;
    }
}
