package net.yosifov.filipov.training.accounting.acc20.repositories;

import net.yosifov.filipov.training.accounting.acc20.entities.Company;
import net.yosifov.filipov.training.accounting.acc20.entities.LedgerRec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LedgerRecRep extends JpaRepository<LedgerRec, Long> {

    LedgerRec findFirstByFiscalYearAndCompanyOrderByIdDesc(Integer fiscalYear,
                                                           Company company);

}
