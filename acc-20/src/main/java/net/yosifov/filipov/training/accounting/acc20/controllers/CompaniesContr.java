package net.yosifov.filipov.training.accounting.acc20.controllers;

//import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
//import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

//import javax.ws.rs.core.Response;
import net.yosifov.filipov.training.accounting.acc20.entities.Company;
import net.yosifov.filipov.training.accounting.acc20.exceptions.NotFoundException;
import net.yosifov.filipov.training.accounting.acc20.repositories.CompaniesRep;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;

//import org.springframework.hateoas.EntityModel;

import java.util.List;
import java.util.Optional;

@RestController
//@EnableTransactionManagement
// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#transactions
public class CompaniesContr {

    @Autowired
    private CompaniesRep companiesRep;

    @GetMapping("/jpa/companies")
    public List<Company> retrieveAllCompanies() {
        return companiesRep.findAll();
    }

    @GetMapping("/jpa/companies/{id}")
    public Company retrieveFirmById(@PathVariable long id) {
        Optional<Company> company = companiesRep.findById(id);

        if(company.isEmpty()) {
            throw new NotFoundException("Company not found");
        }

        return company.get();
    }

    @PostMapping("/jpa/companies")
    public Company createCompany(@RequestBody Company company) {
        return companiesRep.save(company);
    }

    @PutMapping("/jpa/companies")
    public Company updateCompany(@RequestBody Company company) {
        return companiesRep.save(company);
    }

    @DeleteMapping("/jpa/companies/{id}")
    public void deleteUser(@PathVariable long id) {
        companiesRep.deleteById(id);
    }


}
