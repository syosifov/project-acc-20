package net.yosifov.filipov.training.accounting.acc20.controllers;

import net.yosifov.filipov.training.accounting.acc20.entities.Employee;
import net.yosifov.filipov.training.accounting.acc20.repositories.EmployeeRep;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
public class EmployeeContr {

    @Autowired
    private EmployeeRep employeeRep;

    @GetMapping("jpa/employees")
    @Transactional
    // It works with both imports
    public void makeRecords() {
        employeeRep.save(new Employee("Mike", "Sale", 1000));
        employeeRep.save(new Employee("Diane", "Admin", 3000));
        employeeRep.save(new Employee("Mike", "IT", 3200));
    }
}
