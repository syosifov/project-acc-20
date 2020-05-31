package net.yosifov.filipov.training.accounting.acc20.entities;

import net.yosifov.filipov.training.accounting.acc20.utils.C;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class LedgerRecDetail {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    Account accDeb;

    @OneToOne
    Account accCredit;

    @Column(precision = 19, scale = C.SCALE)
    @NotNull
    BigDecimal amount;

    @ManyToOne
    @NotNull
    LedgerRec ledgerRec;

    public LedgerRecDetail() {
    }

    public LedgerRecDetail(Account accDeb,
                           Account accCredit,
                           @NotNull BigDecimal amount,
                           @NotNull LedgerRec ledgerRec) {
        this.accDeb = accDeb;
        this.accCredit = accCredit;
        this.amount = amount;
        this.ledgerRec = ledgerRec;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccDeb() {
        return accDeb;
    }

    public void setAccDeb(Account accDeb) {
        this.accDeb = accDeb;
    }

    public Account getAccCredit() {
        return accCredit;
    }

    public void setAccCredit(Account accCredit) {
        this.accCredit = accCredit;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LedgerRec getLedgerRec() {
        return ledgerRec;
    }

    public void setLedgerRec(LedgerRec ledgerRec) {
        this.ledgerRec = ledgerRec;
    }

    @Override
    public String toString() {
        return "LedgerRecDetail{" +
                "id=" + id +
                ", accDeb=" + accDeb +
                ", accCredit=" + accCredit +
                ", amount=" + amount +
                ", ledgerRec=" + ledgerRec +
                '}';
    }
}
