package net.yosifov.filipov.training.accounting.acc20;

import net.yosifov.filipov.training.accounting.acc20.utils.Bussiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Acc20Application implements CommandLineRunner {

	@Autowired
	private Bussiness bussiness;

	public static void main(String[] args) {
		SpringApplication.run(Acc20Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		bussiness.install("Company 1", "Address 1", "1111111111",2020);
	}
}
