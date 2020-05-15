package net.yosifov.filipov.training.accounting.acc20;
// https://www.concretepage.com/spring-boot/spring-boot-commandlinerunner-and-applicationrunner-example

import net.yosifov.filipov.training.accounting.acc20.entities.Employee;
import net.yosifov.filipov.training.accounting.acc20.repositories.EmployeeRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Acc20Application implements CommandLineRunner {

	@Autowired
	private EmployeeRep employeeRep;

	public static void main(String[] args) {
		SpringApplication.run(Acc20Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		employeeRep.save(new Employee("Jim", "Sale", 1000));
		employeeRep.save(new Employee("Bill", "Admin", 3000));
		employeeRep.save(new Employee("Steve", "IT", 3200));
	}
}
