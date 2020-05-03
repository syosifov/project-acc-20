package net.yosifov.filipov.training.accounting.acc20.entities;

import net.yosifov.filipov.training.accounting.acc20.utils.BT;
import net.yosifov.filipov.training.accounting.acc20.utils.C;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Account {

    @Id
    @GeneratedValue
    private Integer id;

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
    private BT bt;

    public Account() {
        this.assets = new BigDecimal("0.00");
        this.liabilities = new BigDecimal("0.00");
        this.balance = new BigDecimal("0.00");
        this.bt = BT.A;
    }

    public Account(BigDecimal assets,
                   BigDecimal liabilities,
                   BigDecimal balance,
                   LocalDate lastModified,
                   BT bt) {
        this.assets = assets;
        this.liabilities = liabilities;
        this.balance = balance;
        this.lastModified = lastModified;
        this.bt = bt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    @Override
    public String toString() {
        return "Balance{" +
                "id=" + id +
                ", assets=" + assets +
                ", liabilities=" + liabilities +
                ", balance=" + balance +
                ", lastModified=" + lastModified +
                '}';
    }
}
