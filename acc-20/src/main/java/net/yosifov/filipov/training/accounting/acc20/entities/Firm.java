package net.yosifov.filipov.training.accounting.acc20.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Firm {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String address;

    private String taxCode;

    public Firm() {
    }

    public Firm(String name, String address, String taxCode) {
        this.name = name;
        this.address = address;
        this.taxCode = taxCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    @Override
    public String toString() {
        return "Firm {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", taxCode='" + taxCode + '\'' +
                '}';
    }
}
