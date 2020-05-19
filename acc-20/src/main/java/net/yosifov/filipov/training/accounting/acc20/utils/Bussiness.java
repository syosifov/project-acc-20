package net.yosifov.filipov.training.accounting.acc20.utils;

import net.yosifov.filipov.training.accounting.acc20.entities.Account;
import net.yosifov.filipov.training.accounting.acc20.entities.Company;
import net.yosifov.filipov.training.accounting.acc20.repositories.AccountsRep;
import net.yosifov.filipov.training.accounting.acc20.repositories.CompaniesRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class Bussiness {
    @Autowired
    private CompaniesRep companiesRep;
    @Autowired
    private AccountsRep accountsRep;

    public Bussiness() {
    }

    public void install(String name,
                        String address,
                        String taxCode) {
        Company company = companiesRep.save(
                new Company(name,
                            address,
                            taxCode));
        Account account = new Account();
        account.setCompany(company);
        account.setLastModified(LocalDate.now());
        account.setName(company.getName() + " Balance");
        account.setAt(AT.AL);
        accountsRep.save(account);

        Account a1 = new Account();
        a1.setLastModified(LocalDate.now());
        a1.setAt(AT.A);
        a1.setName("a1");
        a1.setParentAccount(account);
        accountsRep.save(a1);

        Account l1 = new Account();
        l1.setLastModified(LocalDate.now());
        l1.setAt(AT.L);
        l1.setName("l1");
        l1.setParentAccount(account);
        accountsRep.save(l1);

        Account al1 = new Account();
        al1.setLastModified(LocalDate.now());
        al1.setAt(AT.AL);
        al1.setName("al1");
        al1.setParentAccount(account);
        accountsRep.save(al1);
    }
}
