package net.yosifov.filipov.training.accounting.acc20.entities;

import javax.persistence.*;
import java.util.List;

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

    @Column
    @OneToMany(mappedBy = "company")
    private List<Account> accounts;

    public Company() {
    }

    public Company(String name, String address, String taxCode) {
        this.name = name;
        this.address = address;
        this.taxCode = taxCode;
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

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", taxCode='" + taxCode + '\'' +
                ", accounts=" + accounts +
                '}';
    }
}
