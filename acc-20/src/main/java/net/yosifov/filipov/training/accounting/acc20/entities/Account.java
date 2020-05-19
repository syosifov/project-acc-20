package net.yosifov.filipov.training.accounting.acc20.entities;

import net.yosifov.filipov.training.accounting.acc20.utils.AT;
import net.yosifov.filipov.training.accounting.acc20.utils.C;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    @Column(precision = 19, scale = C.SCALE)
    @NotNull
    private BigDecimal assets;

    @Column(precision = 19, scale = C.SCALE)
    @NotNull
    private BigDecimal liabilities;

    @Column(precision = 19, scale = C.SCALE)
    @NotNull
    private BigDecimal balance;

    @Column
    @NotNull
    private LocalDate lastModified;

    @Column
    @NotNull
    private AT at;

    @Column
    @NotNull
    private String name;

    @NotNull
    @ManyToOne
    private Company company;

    public Account() {
        this.assets = new BigDecimal("0.00");
        this.liabilities = new BigDecimal("0.00");
        this.balance = new BigDecimal("0.00");
        this.at = AT.A;
    }

    public Account(BigDecimal assets,
                   BigDecimal liabilities,
                   BigDecimal balance,
                   LocalDate lastModified,
                   AT at,
                   String name,
                   Company company) {
        this.assets = assets;
        this.liabilities = liabilities;
        this.balance = balance;
        this.lastModified = lastModified;
        this.at = at;
        this.name = name;
        this.company = company;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAssets() {
        return assets;
    }

    public void setAssets(BigDecimal assets) {
        this.assets = assets;
    }

    public BigDecimal getLiabilities() {
        return liabilities;
    }

    public void setLiabilities(BigDecimal liabilities) {
        this.liabilities = liabilities;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public LocalDate getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDate lastModified) {
        this.lastModified = lastModified;
    }

    public AT getAt() {
        return at;
    }

    public void setAt(AT at) {
        this.at = at;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
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
                "id=" + id +
                ", assets=" + assets +
                ", liabilities=" + liabilities +
                ", balance=" + balance +
                ", lastModified=" + lastModified +
                ", at=" + at +
                ", companyName=" + company.getName() +
                '}';
    }
}
