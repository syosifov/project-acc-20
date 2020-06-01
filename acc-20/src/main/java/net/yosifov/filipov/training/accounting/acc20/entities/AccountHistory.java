package net.yosifov.filipov.training.accounting.acc20.entities;

import net.yosifov.filipov.training.accounting.acc20.utils.C;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class AccountHistory {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Account account;
    @Column(precision = 19, scale = C.SCALE)
    @NotNull
    private BigDecimal initialAssets;
    @Column(precision = 19, scale = C.SCALE)
    @NotNull
    private BigDecimal initialLiabilities;
    @Column(precision = 19, scale = C.SCALE)
    @NotNull
    private BigDecimal initialBalance;

    @Column(precision = 19, scale = C.SCALE)
    @NotNull
    private BigDecimal endAssets;
    @Column(precision = 19, scale = C.SCALE)
    @NotNull
    private BigDecimal endLiabilities;
    @Column(precision = 19, scale = C.SCALE)
    @NotNull
    private BigDecimal endBalance;

    @OneToOne
    @NotNull
    LedgerRecDetail ledgerRecDetail;

    @NotNull
    @ManyToOne
    private Company company;

    public AccountHistory() {}

    public AccountHistory(Account account,
                          @NotNull BigDecimal initialAssets,
                          @NotNull BigDecimal initialLiabilities,
                          @NotNull BigDecimal initialBalance,
                          @NotNull BigDecimal endAssets,
                          @NotNull BigDecimal endLiabilities,
                          @NotNull BigDecimal endBalance,
                          @NotNull LedgerRecDetail ledgerRecDetail,
                          Company company) {
        this.account = account;
        this.initialAssets = initialAssets;
        this.initialLiabilities = initialLiabilities;
        this.initialBalance = initialBalance;
        this.endAssets = endAssets;
        this.endLiabilities = endLiabilities;
        this.endBalance = endBalance;
        this.ledgerRecDetail = ledgerRecDetail;
        this.company = company;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public BigDecimal getInitialAssets() {
        return initialAssets;
    }

    public void setInitialAssets(BigDecimal initialAssets) {
        this.initialAssets = initialAssets;
    }

    public BigDecimal getInitialLiabilities() {
        return initialLiabilities;
    }

    public void setInitialLiabilities(BigDecimal initialLiabilities) {
        this.initialLiabilities = initialLiabilities;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
    }

    public BigDecimal getEndAssets() {
        return endAssets;
    }

    public void setEndAssets(BigDecimal endAssets) {
        this.endAssets = endAssets;
    }

    public BigDecimal getEndLiabilities() {
        return endLiabilities;
    }

    public void setEndLiabilities(BigDecimal endLiabilities) {
        this.endLiabilities = endLiabilities;
    }

    public BigDecimal getEndBalance() {
        return endBalance;
    }

    public void setEndBalance(BigDecimal endBalance) {
        this.endBalance = endBalance;
    }

    public LedgerRecDetail getLedgerRecDetail() {
        return ledgerRecDetail;
    }

    public void setLedgerRecDetail(LedgerRecDetail ledgerRecDetail) {
        this.ledgerRecDetail = ledgerRecDetail;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "AccountHistory{" +
                "id=" + id +
                ", account=" + account +
                ", initialAssets=" + initialAssets +
                ", initialLiabilities=" + initialLiabilities +
                ", initialBalance=" + initialBalance +
                ", endDebit=" + endAssets +
                ", endLiabilities=" + endLiabilities +
                ", endBalance=" + endBalance +
                ", ledgerRecDetail=" + ledgerRecDetail +
                '}';
    }
}
