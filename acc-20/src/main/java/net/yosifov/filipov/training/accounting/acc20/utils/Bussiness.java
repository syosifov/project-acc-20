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

        Account al1  = addChild(account, AT.AL,"al1");

        Account a1  = addChild(account, AT.A,"a1");
        Account a11 = addChild(a1, AT.A,"a11");
        Account a12 = addChild(a1, AT.A,"a12");

        Account l1  = addChild(account, AT.L,"l1");
        Account l11 = addChild(l1, AT.L,"l11");
        Account l12 = addChild(l1, AT.L,"l12");

        assign(a11,l11,BigDecimal.valueOf(5));
        assign(a12,l12,BigDecimal.valueOf(15));
//        assign(a11,al1,BigDecimal.valueOf(5));

    }

    public Account addChild(Account account,
                            AT at,
                            String name) {

        Account acc = new Account();
        acc.setLastModified(LocalDate.now());
        acc.setAt(at);
        acc.setName(account.getName()+","+name);
        switch (at) {
            case A:
            case AL:
                acc.setParentAccountA(account);
                break;
            case L:
                acc.setParentAccountL(account);
                break;
            default: throw new RuntimeException("Invalid type");
        }
        acc.setCompany(account.getCompany());
        accountsRep.save(acc);

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
