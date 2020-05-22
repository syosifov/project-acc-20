package net.yosifov.filipov.training.accounting.acc20.utils;

import net.yosifov.filipov.training.accounting.acc20.entities.Account;
import net.yosifov.filipov.training.accounting.acc20.entities.Company;
import net.yosifov.filipov.training.accounting.acc20.repositories.AccountsRep;
import net.yosifov.filipov.training.accounting.acc20.repositories.CompaniesRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
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
        account.setName("Balance " + LocalDate.now().getYear());
        account.setAt(AT.AL);
        company.setCurrentAccount(account);
        accountsRep.save(account);
        companiesRep.save(company);

        addChild(account, AT.A,"a1");
        addChild(account, AT.L,"l1");
        addChild(account, AT.AL,"al1");

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
        accountsRep.save(acc);

        return null;
    }
}
