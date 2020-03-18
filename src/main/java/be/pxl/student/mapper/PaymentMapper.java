package be.pxl.student.mapper;

import be.pxl.student.entity.Payment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class PaymentMapper {
    //https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

    public static Payment map(String[] splitInput){
        try {
            LocalDateTime date = LocalDateTime.parse(splitInput[3], FORMATTER);
            double amount = Double.parseDouble(splitInput[4]);
            String currency = splitInput[5];
            String detail = splitInput[6];
            return new Payment(date, amount, currency, detail);
        } catch (Exception exception) {
            throw new IllegalArgumentException("No correct payment can be created");
        }
    }
}
