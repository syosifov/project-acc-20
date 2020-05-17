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
        accountsRep.save(account);

    }
}
