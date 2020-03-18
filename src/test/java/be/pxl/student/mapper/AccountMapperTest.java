package be.pxl.student.mapper;

import be.pxl.student.entity.Account;
import be.pxl.student.entity.Payment;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

public class AccountMapperTest {

    @Test
    public void aValidLineIsMappedToAnAccount(){
        String validLine = "Jos,BE69771770897312,BE17795215960626,Thu Feb 13 05:47:35 CET 2020,265.8,EUR,Some description.";
        Account account = AccountMapper.map(validLine.split(","));

        assertNotNull(account);
        assertEquals("Jos",account.getName());
        assertEquals("BE69771770897312",account.getIBAN());
        assertEquals(1,account.getPayments().size());

        Payment payment = account.getPayments().get(0);
        assertEquals(LocalDateTime.of(2020,2,13,5,47,35),payment.getDate());
        assertEquals("EUR", payment.getCurrency());
        assertEquals(265.8,payment.getAmount());
        assertEquals("Some description.",payment.getDetail());
    }

    @Test
    public void anInvalidLineThrowsAPaymentException(){
        String inValidLine = "Jos,BE69771770897312,BE17795215960626,Thu Feb 13 05:47:35 CET 2020,tweehonderd,EUR,Some description.";

        Exception exception = assertThrows(IllegalArgumentException.class,()->AccountMapper.map(inValidLine.split(",")));
        Assertions.assertTrue(exception.getMessage().toLowerCase().contains("payment"));
    }

    @Test
    public void anInvalidLineFieldCountThrowsFieldException(){
        String inValidLine = "Jos,BE69771770897312,BE17795215960626,Thu Feb 13 05:47:35 CET 2020,265.8,EUR";

        Exception exception = assertThrows(IllegalArgumentException.class,()->AccountMapper.map(inValidLine.split(",")));
        Assertions.assertTrue(exception.getMessage().toLowerCase().contains("field"));
    }
}
