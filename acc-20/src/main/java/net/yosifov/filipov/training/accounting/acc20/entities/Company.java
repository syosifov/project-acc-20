package net.yosifov.filipov.training.accounting.acc20.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String address;

    @Column(unique = true)
    private String taxCode;

    @NotNull
    @OneToOne
    private Account currentAccount;

    @NotNull
    private Integer currentFiscalYear;

    public Company() {
        this.currentFiscalYear = LocalDate.now().getYear();
    }

    public Company(String name, String address, String taxCode, Integer currentFiscalYear) {

        this.name = name;
        this.address = address;
        this.taxCode = taxCode;
        this.currentFiscalYear = currentFiscalYear;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public Account getCurrentAccount() {
        return currentAccount;
    }

    public void setCurrentAccount(Account currentAccount) {
        this.currentAccount = currentAccount;
    }


    public Integer getCurrentFiscalYear() {
        return currentFiscalYear;
    }

    public void setCurrentFiscalYear(Integer currentFiscalYear) {
        this.currentFiscalYear = currentFiscalYear;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", taxCode='" + taxCode + '\'' +
                ", accountName=" + currentAccount.getName() +
                '}';
    }
}
