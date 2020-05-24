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

        Account a1  = addChild(account,null, AT.A,"a1");
        Account a11 = addChild( a1, null, AT.A,"a11");
        Account accLost = addChild(a1, null, AT.A,"Lost");

        Account l1  = addChild(null, account, AT.L,"L1");
        Account l11 = addChild(null, l1, AT.L,"L11");
        Account accProfit = addChild(null, l1, AT.L,"Profit");

        Account accComb  = addChild(accLost, accProfit, AT.AL,"AL");

        assign(a11,l11,BigDecimal.valueOf(1000));
        assign(a1,accComb,BigDecimal.valueOf(5));   // generates profit
        assign(accComb,l1,BigDecimal.valueOf(15));  // generates loss
        assign(a11,accLost,BigDecimal.valueOf(3));
        assign(a11,accLost,BigDecimal.valueOf(3));

    }

    public Account addChild(Account accA,
                            Account accL,
                            AT at,
                            String name) {

        Account acc = new Account();
        acc.setLastModified(LocalDate.now());
        acc.setAt(at);
        switch (at) {
            case A:
                if(null==accA) {
                    throw new RuntimeException("Parent account must not be null");
                }
                acc.setParentAccountA(accA);
                acc.setName(accA.getName()+","+name);
                acc.setCompany(accA.getCompany());
                break;
            case AL:
                if(null==accA) {
                    throw new RuntimeException("Parent account must not be null");
                }
                acc.setParentAccountA(accA);
                if(null==accL) {
                    throw new RuntimeException("Parent account must not be null");
                }
                acc.setParentAccountL(accL);
                acc.setName(name);
                acc.setCompany(accA.getCompany());
                break;
            case L:
                if(null==accL) {
                    throw new RuntimeException("Parent account must not be null");
                }
                acc.setParentAccountL(accL);
                acc.setName(accL.getName()+","+name);
                acc.setCompany(accL.getCompany());
                break;
            default: throw new RuntimeException("Invalid Account Type");
        }

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
