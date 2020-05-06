package net.yosifov.filipov.training.accounting.acc20.controllers;

import net.yosifov.filipov.training.accounting.acc20.entities.Enterprise;
import net.yosifov.filipov.training.accounting.acc20.repositories.EnterpriseRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EnterpriseContr {

    @Autowired
    private EnterpriseRep enterpriseRep;

    @GetMapping("/jpa/enterprises")
    public List<Enterprise> retrieveAllUsers() {
        return enterpriseRep.findAll();
    }
}
