package net.yosifov.filipov.training.accounting.acc20.repositories;

import net.yosifov.filipov.training.accounting.acc20.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountsRep  extends JpaRepository<Account, Long> {
}
