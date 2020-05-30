package net.yosifov.filipov.training.accounting.acc20.security;

import net.yosifov.filipov.training.accounting.acc20.entities.Company;
import net.yosifov.filipov.training.accounting.acc20.repositories.CompaniesRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//// Слагам контролера тук за да може да получаим незваисимост от системата - в по късен момент ще репим къде да ги сложим
//// зашити ако правип сложна апликация контролетие не може да са на едно място стават много 
@RestController
public class SecurityController {
        @GetMapping("/jpa/login")
        public String Login(String userName, String Password) {
            return "OK";
        }
}
