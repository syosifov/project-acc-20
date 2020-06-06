package net.yosifov.filipov.training.accounting.acc20.entities;

import com.sun.istack.Nullable;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class LedgerRec {

    @Id
    @GeneratedValue
    private Long id;

    private Long recId;

    private BigDecimal amount;

    private LocalDateTime localDateTime;

    private String description;

    private Integer fiscalYear;

    @Nullable
    @OneToOne
    private LedgerRec refLedgerRec;

    @ManyToOne
    private Company company;

    public LedgerRec() {
        amount = BigDecimal.ZERO;
        localDateTime = LocalDateTime.now();
        recId = 0L;
        fiscalYear = localDateTime.getYear();
    }

    public LedgerRec(Long recId,
                     BigDecimal amount,
                     LocalDateTime localDateTime,
                     String description,
                     Company company,
                     Integer fiscalYear,
                     LedgerRec refLedgerRec) {
        this.recId = recId;
        this.amount = amount;
        this.localDateTime = localDateTime;
        this.description = description;
        this.company = company;
        this.fiscalYear = fiscalYear;
        this.refLedgerRec = refLedgerRec;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRecId() {
        return recId;
    }

    public void setRecId(Long recId) {
        this.recId = recId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Integer getFiscalYear() {
        return fiscalYear;
    }

    public void setFiscalYear(Integer fiscalYear) {
        this.fiscalYear = fiscalYear;
    }

    public LedgerRec getRefLedgerRec() {
        return refLedgerRec;
    }

    public void setRefLedgerRec(LedgerRec refLedgerRec) {
        this.refLedgerRec = refLedgerRec;
    }

    @Override
    public String toString() {
        return "LedgerRec{" +
                "id=" + id +
                ", recId=" + recId +
                ", amount=" + amount +
                ", localDateTime=" + localDateTime +
                ", description='" + description + '\'' +
                ", fiscalYear=" + fiscalYear +
                ", company=" + company +
                '}';
    }


//    @Override
//    public String toString() {
//        return "LedgerRec{" +
//                "id=" + id +
//                ", recId=" + recId +
//                ", amount=" + amount +
//                ", localDateTime=" + localDateTime +
//                ", description='" + description + '\'' +
//                ", fiscalYear=" + fiscalYear +
//                ", refLedgerRec=" + refLedgerRec +
//                ", company=" + company +
//                '}';
//    }
}
