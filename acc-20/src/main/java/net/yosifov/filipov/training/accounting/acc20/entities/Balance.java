package net.yosifov.filipov.training.accounting.acc20.entities;

import net.yosifov.filipov.training.accounting.acc20.utils.U;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;

import net.yosifov.filipov.training.accounting.acc20.utils.U;

@Entity
public class Balance {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(precision = 19, scale = 4)
    private BigDecimal assets;

    @Column(precision = 19, scale = 4)
    private BigDecimal liabilities;

    @Column(precision = 19, scale = 4)
    private BigDecimal score;

    @Column
    private LocalDate lastModified;

    public Balance() {
        this.assets = new BigDecimal(0, U.mc4);
        this.liabilities = new BigDecimal(0, U.mc4);
        this.score = new BigDecimal(0, U.mc4);
    }

    public Balance(BigDecimal assets,
                   BigDecimal liabilities,
                   BigDecimal score,
                   LocalDate lastModified) {
        this();
        this.assets.add(assets, U.mc4);
        this.liabilities.add(liabilities, U.mc4);
        this.score.add(score, U.mc4);
        this.lastModified = lastModified;
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

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
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
                ", score=" + score +
                ", lastModified=" + lastModified +
                '}';
    }
}
