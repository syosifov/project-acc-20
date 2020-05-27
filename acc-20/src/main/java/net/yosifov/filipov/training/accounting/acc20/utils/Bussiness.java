package net.yosifov.filipov.training.accounting.acc20.utils;

import net.yosifov.filipov.training.accounting.acc20.entities.Account;
import net.yosifov.filipov.training.accounting.acc20.entities.Company;
import net.yosifov.filipov.training.accounting.acc20.repositories.AccountsRep;
import net.yosifov.filipov.training.accounting.acc20.repositories.CompaniesRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class Bussiness {
    @Autowired
    private CompaniesRep companiesRep;
    @Autowired
    private AccountsRep accountsRep;

    public Bussiness() {
    }

    @Transactional
    public void install(String name,
                        String address,
                        String taxCode) {
        Company company = new Company(name,
                                      address,
                                      taxCode);
        Account account = new Account();
        account.setCompany(company);
        account.setLastModified(LocalDate.now());
        account.setName("R");
        account.setDescription("Balance " + LocalDate.now().getYear());
        account.setAt(AT.AL);
        company.setCurrentAccount(account);
        accountsRep.save(account);
        companiesRep.save(company);

        //Account al1  = addChild(account, AT.AL,"al1");

        Account a1  = addChildAccount(account, AT.A,"a1");
        Account a11 = addChildAccount( a1, AT.A,"a11");
        Account accLost = addChildAccount(a1, AT.A,"Lost");

        Account l1  = addChildAccount(account, AT.L,"L1");
        Account l11 = addChildAccount(l1, AT.L,"L11");
        Account accProfit = addChildAccount(l1, AT.L,"Profit");

        Account accComb  = addALAccount(accLost, accProfit, "AL");

        assign(a11,l11,BigDecimal.valueOf(1000));
        assign(a1,accComb,BigDecimal.valueOf(5));   // generates profit
        assign(accComb,l1,BigDecimal.valueOf(15));  // generates loss
        assign(a11,accLost,BigDecimal.valueOf(3));
        assign(a11,accLost,BigDecimal.valueOf(3));

    }

    public Account addChildAccount(Account parent,
                                   AT at,
                                   String name) {
        Account acc = new Account();
        acc.setLastModified(LocalDate.now());
        if(at.equals(AT.AL)) {
            throw new RuntimeException("Wrong Account type: "+at);
        }
        if(!at.equals(parent.getAt()) && !AT.AL.equals(parent.getAt())) {
            throw new RuntimeException("Wrong Account type: "+at);
        }
        acc.setAt(at);
        acc.setCompany(parent.getCompany());
        acc.setName(parent.getName()+","+name);
        switch (at) {
            case A:
                acc.setParentAccountA(parent);
                break;
            case L:
                acc.setParentAccountL(parent);
                break;
            default:
                throw new RuntimeException("Wrong Account type: "+at);
        }
        return acc;
    }

    public Account addALAccount(Account accA,
                                Account accL,
                                String name) {
        if(!accA.getAt().equals(AT.A)) {
            throw new RuntimeException("Wrong Account type: "+accA.getAt());
        }
        if(!accL.getAt().equals(AT.L)) {
            throw new RuntimeException("Wrong Account type: "+accL.getAt());
        }
        Account acc = new Account();
        acc.setLastModified(LocalDate.now());
        acc.setAt(AT.AL);
        acc.setName(name);
        acc.setParentAccountA(accA);
        acc.setParentAccountL(accL);
        acc.setCompany(accA.getCompany());
        return acc;
    }

    @Transactional
    public void assign(Account accDebit,
                       Account accCredit,
                       BigDecimal v){
        Account account = accDebit;
        while (null != account){
            account.debit(v);
            accountsRep.save(account);
            account = account.getUpperAccount();
        }

        account = accCredit;
        while (null != account){
            account.credit(v);
            accountsRep.save(account);
            account = account.getUpperAccount();
        }

    }
}
