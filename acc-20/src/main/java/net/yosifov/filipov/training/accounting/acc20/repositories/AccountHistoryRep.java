package net.yosifov.filipov.training.accounting.acc20.repositories;

import net.yosifov.filipov.training.accounting.acc20.entities.AccountHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountHistoryRep  extends JpaRepository<AccountHistory, Long> {
}
