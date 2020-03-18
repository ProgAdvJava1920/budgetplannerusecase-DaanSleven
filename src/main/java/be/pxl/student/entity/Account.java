package be.pxl.student.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String IBAN;
    private String name;
    @OneToMany(mappedBy = "account")
    private List<Payment> payments = new ArrayList<>();

    public Account(){

    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Account{" +
                "IBAN='" + IBAN + '\'' +
                ", name='" + name + '\'' +
                ", outgoing payments=[" + payments.stream().map(Payment::toString).collect(Collectors.joining(",")) + "]}";
    }

    public List<Payment> getPayments() {
        return payments;
    }
}
