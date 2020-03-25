package be.pxl.student.dao;

import be.pxl.student.entity.Account;
import be.pxl.student.entity.Payment;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;
import java.util.List;

public class AccountDao {
    private EntityManager entityManager;

    public AccountDao(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public List<Payment> findPaymentsByAccountName(String name){
        TypedQuery<Account> query = entityManager.createNamedQuery("findAccountByName", Account.class);
        query.setParameter("name",name);
        Account account = query.getSingleResult();
        entityManager.close();
        return account.getPayments();
    }
}
