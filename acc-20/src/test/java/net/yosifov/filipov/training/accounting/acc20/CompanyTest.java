package net.yosifov.filipov.training.accounting.acc20;

import net.yosifov.filipov.training.accounting.acc20.entities.Company;
import net.yosifov.filipov.training.accounting.acc20.repositories.CompaniesRep;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
public class CompanyTest {
    @Autowired
    private CompaniesRep companiesRep;

    @Test
    @Transactional
    public void listCompanies() {
        List<Company> lstCompanies = companiesRep.findAll();
        for (Company c: lstCompanies) {
            System.out.println(c);
        }
    }
}
