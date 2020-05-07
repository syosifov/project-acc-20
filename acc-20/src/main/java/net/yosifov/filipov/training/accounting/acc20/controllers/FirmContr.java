package net.yosifov.filipov.training.accounting.acc20.controllers;

//import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
//import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

//import javax.ws.rs.core.Response;
import net.yosifov.filipov.training.accounting.acc20.entities.Firm;
import net.yosifov.filipov.training.accounting.acc20.exceptions.NotFoundException;
import net.yosifov.filipov.training.accounting.acc20.repositories.FirmRep;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.hateoas.EntityModel;

import java.util.List;
import java.util.Optional;

@RestController
public class FirmContr {

    @Autowired
    private FirmRep firmRep;

    @GetMapping("/jpa/firms")
    public List<Firm> retrieveAllFirms() {
        return firmRep.findAll();
    }

    @GetMapping("/jpa/firms/{id}")
    public EntityModel<Firm> retrieveFirmById(@PathVariable int id) {
        Optional<Firm> firm = firmRep.findById(id);

        if(firm.isEmpty()) {
            throw new NotFoundException("Company not found");
        }

        EntityModel<Firm> entityModel = new EntityModel<>(firm.get());
//
//        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
//
//        resource.add(linkTo.withRel("all-users"));
//
//        // HATEOAS

        return entityModel;
    }

//    @GetMapping("/jpa/firms/{id}")
//    public Response retrieveFirmById(@PathVariable int id) {
//        Optional<Firm> opFirm = firmRep.findById(id);
//
//        if(!opFirm.isPresent()) {
//            return Response.noContent().build();
//        }
//        Firm firm = opFirm.get();
//
//        return Response.ok(firm).build();
//    }
}
