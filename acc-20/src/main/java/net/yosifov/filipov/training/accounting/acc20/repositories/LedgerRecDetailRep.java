package net.yosifov.filipov.training.accounting.acc20.repositories;

import net.yosifov.filipov.training.accounting.acc20.entities.LedgerRecDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LedgerRecDetailRep extends JpaRepository<LedgerRecDetail, Long> {

}
