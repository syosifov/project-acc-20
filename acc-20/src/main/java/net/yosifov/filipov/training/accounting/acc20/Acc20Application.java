package net.yosifov.filipov.training.accounting.acc20;

import net.yosifov.filipov.training.accounting.acc20.entities.Account;
import net.yosifov.filipov.training.accounting.acc20.entities.Company;
import net.yosifov.filipov.training.accounting.acc20.repositories.AccountsRep;
import net.yosifov.filipov.training.accounting.acc20.repositories.CompaniesRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class Acc20Application implements CommandLineRunner {

	@Autowired
	private CompaniesRep companiesRep;
	@Autowired
	private AccountsRep accountsRep;

	public static void main(String[] args) {
		SpringApplication.run(Acc20Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Company company = companiesRep.save(
				new Company("Company 1",
							"Address 1",
							"1111111111"));
		Account account = new Account();
		account.setCompany(company);
		account.setLastModified(LocalDate.now());
		account.setName(company.getName() + " Balance");
		accountsRep.save(account);
	}
}
