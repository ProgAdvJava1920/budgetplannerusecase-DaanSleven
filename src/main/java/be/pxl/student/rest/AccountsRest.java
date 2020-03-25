package be.pxl.student.rest;

import be.pxl.student.dao.AccountDao;
import be.pxl.student.dao.EntityManagerUtil;
import be.pxl.student.entity.Payment;

import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("accounts")
public class AccountsRest {

    @GET
    @Path("{accountName}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Payment> getAccountByName(@PathParam("accountName") String accountName){
        EntityManager entityManager = EntityManagerUtil.createEntityManager();
        AccountDao accountDao = new AccountDao(entityManager);

        List<Payment> payments = accountDao.findPaymentsByAccountName(accountName);

        entityManager.close();
        return payments;
    }
}
