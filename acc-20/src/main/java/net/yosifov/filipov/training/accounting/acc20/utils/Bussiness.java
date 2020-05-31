package net.yosifov.filipov.training.accounting.acc20.utils;

import net.yosifov.filipov.training.accounting.acc20.entities.Account;
import net.yosifov.filipov.training.accounting.acc20.entities.Company;
import net.yosifov.filipov.training.accounting.acc20.entities.LedgerRec;
import net.yosifov.filipov.training.accounting.acc20.entities.LedgerRecDetail;
import net.yosifov.filipov.training.accounting.acc20.repositories.AccountsRep;
import net.yosifov.filipov.training.accounting.acc20.repositories.CompaniesRep;
import net.yosifov.filipov.training.accounting.acc20.repositories.LedgerRecDetailRep;
import net.yosifov.filipov.training.accounting.acc20.repositories.LedgerRecRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class Bussiness {
    @Autowired
    private CompaniesRep companiesRep;
    @Autowired
    private AccountsRep accountsRep;
    @Autowired
    private LedgerRecRep ledgerRecRep;
    @Autowired
    private LedgerRecDetailRep ledgerRecDetailRep;

    public Bussiness() {
    }

    @Transactional
    public void install(String name,
                        String address,
                        String taxCode,
                        Integer fiscalYear) {
        Company company = new Company(name,
                                      address,
                                      taxCode,
                                      fiscalYear
                                    );
        Account account = new Account();
        account.setCompany(company);
        account.setLastModified(LocalDate.now());
        account.setName("R");
        account.setDescription("Balance " + LocalDate.now().getYear());
        account.setAt(AT.AL);
        company.setCurrentAccount(account);
        accountsRep.save(account);
        companiesRep.save(company);

        Account a1  = addChildAccount(account, AT.A,"a1");
        Account a11 = addChildAccount( a1, AT.A,"a11");
        Account accLost = addChildAccount(a1, AT.A,"Lost");

        Account l1  = addChildAccount(account, AT.L,"L1");
        Account l11 = addChildAccount(l1, AT.L,"L11");
        Account accProfit = addChildAccount(l1, AT.L,"Profit");

        Account accComb  = addALAccount(accLost, accProfit, "AL");

//        assign(a11,l11,BigDecimal.valueOf(1000));
//        assign(a1,accComb,BigDecimal.valueOf(5));   // generates profit
//        assign(accComb,l1,BigDecimal.valueOf(15));  // generates loss
//        assign(a11,accLost,BigDecimal.valueOf(3));
//        assign(a11,accLost,BigDecimal.valueOf(3));

        assign(a11,l11,BigDecimal.valueOf(100),company,"First Record");
//        long currRecId = getRecNumb(company);
//        addLedgerRec(
//                new BigDecimal("100.00"),
//                "First Record",
//                company
//        );
//        currRecId = getRecNumb(company, 2020);
//        addLedgerRec(++currRecId,
//                new BigDecimal("200.00"),
//                "Second Record",
//                company,
//                2020);
    }

    private LedgerRec addLedgerRec(BigDecimal amount,
                                   String description,
                                   Company company) {

        Long recId = getRecNumb(company);
        LedgerRec ledgerRec = new LedgerRec(++recId,
                                            amount,
                                            LocalDateTime.now(),
                                            description,
                                            company,
                                            company.getCurrentFiscalYear()
                                           );

        ledgerRecRep.save(ledgerRec);
        return ledgerRec;
    }

    private Long getRecNumb(Company company) {
        LedgerRec ledgerRec =
                ledgerRecRep
                    .findFirstByFiscalYearAndCompanyOrderByIdDesc(
                            company.getCurrentFiscalYear(),
                            company);
        if(null==ledgerRec) {
            return 0L;
        }
        return ledgerRec.getRecId();
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
        accountsRep.save(acc);
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

    @Transactional
    public void assign(Account accDebit,
                       Account accCredit,
                       BigDecimal v,
                       Company company,
                       String description) {
        LedgerRec ledgerRec = addLedgerRec(v,
                                           description,
                                           company);
        LedgerRecDetail ledgerRecDetail = new LedgerRecDetail(accDebit,
                                                              accCredit,
                                                              v,
                                                              ledgerRec);
        ledgerRecDetailRep.save(ledgerRecDetail);
        assign(accDebit,accCredit,v);
    }
}
