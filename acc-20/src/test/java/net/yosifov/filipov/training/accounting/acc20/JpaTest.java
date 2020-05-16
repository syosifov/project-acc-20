package net.yosifov.filipov.training.accounting.acc20;

import net.yosifov.filipov.training.accounting.acc20.entities.Employee;
import net.yosifov.filipov.training.accounting.acc20.repositories.EmployeeRep;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class JpaTest {
    @Autowired
    private EmployeeRep employeeRep;

    @Test
    public void t1() {
        employeeRep.save(new Employee("Jim", "Sale", 1000));
        List<Employee> lst = employeeRep.findAll();
        System.out.println(lst);
        System.out.println(lst.size());
    }
}
