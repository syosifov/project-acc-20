package net.yosifov.filipov.training.accounting.acc20.repositories;

import net.yosifov.filipov.training.accounting.acc20.entities.Firm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FirmRep extends JpaRepository<Firm, Integer> {
}
