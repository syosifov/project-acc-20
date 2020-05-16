package net.yosifov.filipov.training.accounting.acc20;

import net.yosifov.filipov.training.accounting.acc20.entities.Company;
import net.yosifov.filipov.training.accounting.acc20.repositories.CompaniesRep;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.transaction.Transactional;
import java.util.List;

@DataJpaTest
public class JpaTest {

    @Autowired
    private CompaniesRep companiesRep;

    @Test
    @Transactional
    public void companyTest() {

        //companiesRep.deleteAll();
        companiesRep.save(new Company("Company 1", "Address 1", "1111111112"));
        companiesRep.save(new Company("Company 1", "Address 1", "1111111113"));


        List<Company> lst = companiesRep.findAll();
        System.out.println(lst);
    }
}
