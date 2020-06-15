package net.yosifov.filipov.training.accounting.acc20.utils;

import net.yosifov.filipov.training.accounting.acc20.entities.*;
import net.yosifov.filipov.training.accounting.acc20.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
    @Autowired
    private AccountHistoryRep accountHistoryRep;
    private LedgerRec refLedgerRec;

    public Bussiness() {
    }

    @Transactional
    public void install(String name,
                        String address,
                        String taxCode,
                        Integer fiscalYear) throws Exception {
        Company company = new Company(name,
                                      address,
                                      taxCode,
                                      fiscalYear
                                    );
        Account account = new Account();
        account.setCompany(company);
        account.setLastModified(LocalDate.now());
        account.setName("");
        account.setDescription("Balance " + LocalDate.now().getYear());
        account.setAt(AT.AL);
        company.setCurrentAccount(account);
        accountsRep.save(account);
        companiesRep.save(company);

        createLedgerBg(company, account);

//        Account a1  = addChildAccount(account, AT.A,"a1");
//        Account a11 = addChildAccount( a1, AT.A,"a11");
//        Account accLost = addChildAccount(a1, AT.A,"Lost");
//
//        Account l1  = addChildAccount(account, AT.L,"L1");
//        Account l11 = addChildAccount(l1, AT.L,"L11");
//        Account accProfit = addChildAccount(l1, AT.L,"Profit");
//
//
//        assign(a11,l11,BigDecimal.valueOf(1000),company,"First Record",null);
//        assign(a11,l11,BigDecimal.valueOf(100),company,"Second Record",null);
//        assign(a11,l11,BigDecimal.valueOf(-100),company,"Second Record",refLedgerRec);

    }

    private void createLedgerBg(Company company, Account baseAccount) {
        Path path = Paths.get("src/main/resources/static/ledger_bg.txt");
        List<String> allLines;
        try {
            allLines = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace(); // TODO
            return;
        }
        Account section = null;
        Account subSection = null;
        for(String s: allLines) {
            String[] sa = s.split("\\|");
            if(sa[0].trim().length()==1){
                section = addChildAccount(baseAccount, AT.AL,sa[0].trim(),sa[1].trim());
                continue;
            }
            if(sa[0].trim().length()==2){
                subSection = addChildAccount(section, AT.AL,sa[0].trim(),sa[1].trim());
                continue;
            }
            AT at = null;
            String sAT = sa[2].trim();
            switch (sAT) {
                case "Активна":
                case "Active":
                    at = AT.A;
                    break;
                case "Пасивна":
                case "Passive":
                    at = AT.L;
                    break;
                case "Акт-Пас":
                case "Act-Pass":
                    at = AT.AL;
                    break;
                case "Корекционна":
                case "Corrective":
                    at = AT.C;
            }
            addChildAccount(subSection, at,sa[0].trim(),sa[1].trim());
        }
    }

    private LedgerRec addLedgerRec(BigDecimal amount,
                                   String description,
                                   Company company,
                                   LedgerRec refLedgerRec) {

        Long recId = getRecNumb(company);
        LedgerRec ledgerRec = new LedgerRec(++recId,
                                            amount,
                                            LocalDateTime.now(),
                                            description,
                                            company,
                                            company.getCurrentFiscalYear(),
                                            refLedgerRec
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
                                   String name,
                                   String description) {
        Account acc = new Account();
        acc.setLastModified(LocalDate.now());
        acc.setAt(at);
        acc.setCompany(parent.getCompany());
        acc.setName(name);
        acc.setDescription(description);
        acc.setParentAccount(parent);
        accountsRep.save(acc);
        return acc;
    }


    @Transactional
    public void assign(Account accDebit,
                       Account accCredit,
                       BigDecimal v,
                       LedgerRecDetail ledgerRecDetail){
        Account account = accDebit;
        while (null != account){
            registerOp(account, v, Op.Debit, ledgerRecDetail);
            account = account.getParentAccount();
        }

        account = accCredit;
        while (null != account){
            registerOp(account, v, Op.Credit, ledgerRecDetail);
            account = account.getParentAccount();
        }
    }

    @Transactional
    public void reverseAssign(Account accDebit,
                       Account accCredit,
                       BigDecimal v,
                       LedgerRecDetail ledgerRecDetail){
        Account account = accDebit;
        while (null != account){
            registerOp(account, v, Op.ReverseDebit, ledgerRecDetail);
            account = account.getParentAccount();
        }

        account = accCredit;
        while (null != account){
            registerOp(account, v, Op.ReverseCredit, ledgerRecDetail);
            account = account.getParentAccount();
        }
    }



    private void registerOp(Account account, BigDecimal v, Op op, LedgerRecDetail ledgerRecDetail) {
        AccountHistory accountHistory = new AccountHistory();
        accountHistory.setAccount(account);
        accountHistory.setCompany(account.getCompany());
        accountHistory.setLedgerRecDetail(ledgerRecDetail);

        accountHistory.setInitialBalance(account.getBalance());
        accountHistory.setInitialAssets(account.getAssets());
        accountHistory.setInitialLiabilities(account.getLiabilities());

        switch (op){
            case Debit:
            case ReverseDebit:
                account.debit(v);
                accountHistory.setEndBalance(account.getBalance());
                accountHistory.setEndAssets(account.getAssets());
                accountHistory.setEndLiabilities(account.getLiabilities());
                break;
            case Credit:
            case ReverseCredit:
                account.credit(v);
                accountHistory.setEndBalance(account.getBalance());
                accountHistory.setEndAssets(account.getAssets());
                accountHistory.setEndLiabilities(account.getLiabilities());
                break;
        }

        accountHistory.setOp(op);
        accountsRep.save(account);
        accountHistoryRep.save(accountHistory);
    }

    @Transactional
    public void assign(Account accDebit,
                       Account accCredit,
                       BigDecimal v,
                       Company company,
                       String description,
                       LedgerRec refLedgerRec) throws Exception {
        if(v.compareTo(BigDecimal.ZERO)>0) {
            if(refLedgerRec != null) {
                throw new Exception("Argument refLedgerRec shoud be not null on reverse transactions only");
            }
        }
        else
        if(v.compareTo(BigDecimal.ZERO)< 0) {
            if(refLedgerRec == null) {
                throw new Exception("Argument refLedgerRec shoud be not null on reverse transactions");
            }
        }
        else
        if(v.compareTo(BigDecimal.ZERO)==0){
            throw new Exception("The transaction's ampount must not be Zero");
        }
        this.refLedgerRec = addLedgerRec(v,
                                 description,
                                 company,
                                 refLedgerRec);
        LedgerRecDetail ledgerRecDetail = new LedgerRecDetail(accDebit,
                                                              accCredit,
                                                              v,
                this.refLedgerRec);
        ledgerRecDetailRep.save(ledgerRecDetail);
        assign(accDebit,accCredit,v, ledgerRecDetail);
    }
}
