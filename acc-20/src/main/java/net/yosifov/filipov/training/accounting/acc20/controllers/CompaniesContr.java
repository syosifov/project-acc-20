package net.yosifov.filipov.training.accounting.acc20.controllers;

//import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
//import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

//import javax.ws.rs.core.Response;
import net.yosifov.filipov.training.accounting.acc20.entities.Company;
import net.yosifov.filipov.training.accounting.acc20.exceptions.NotFoundException;
import net.yosifov.filipov.training.accounting.acc20.repositories.CompaniesRep;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.hateoas.EntityModel;

import java.util.List;
import java.util.Optional;

@RestController
public class CompaniesContr {

    @Autowired
    private CompaniesRep companiesRep;

    @GetMapping("/jpa/companies")
    public List<Company> retrieveAllCompanies() {
        return companiesRep.findAll();
    }

    @GetMapping("/jpa/companies/{id}")
    public EntityModel<Company> retrieveFirmById(@PathVariable int id) {
        Optional<Company> firm = companiesRep.findById(id);

        if(firm.isEmpty()) {
            throw new NotFoundException("Company not found");
        }

        return new EntityModel<>(firm.get());
    }


}
