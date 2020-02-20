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
    //https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

    public static Account map(String input){
        String[] splitInput = input.split(",");

        //Check if splitInput has enough fields
        if(splitInput.length!=7){
            throw new IllegalArgumentException("Input should contain 7 fields");
        }

        //Make payment
        List<Payment> payments = new ArrayList<>();
        try {
            LocalDateTime date = LocalDateTime.parse(splitInput[3], FORMATTER);
            double amount = Double.parseDouble(splitInput[4]);
            String currency = splitInput[5];
            String detail = splitInput[6];
            Payment payment = new Payment(date, amount, currency, detail);

            payments.add(payment);
        }catch(Exception exception){
            throw new IllegalArgumentException("No correct payment can be created");
        }

        //Make account
        Account account = new Account();
        account.setName(splitInput[0]);
        account.setIBAN(splitInput[1]);
        account.setPayments(payments);

        return account;
    }
}
