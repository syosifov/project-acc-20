package net.yosifov.filipov.training.accounting.acc20.repositories;

import net.yosifov.filipov.training.accounting.acc20.entities.LedgerRecDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin
public interface LedgerRecDetailRep extends JpaRepository<LedgerRecDetail, Long> {

}
