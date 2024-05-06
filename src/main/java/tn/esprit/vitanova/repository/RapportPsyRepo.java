package tn.esprit.vitanova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.vitanova.entities.RapportPsy;

import java.util.List;

@Repository
public interface RapportPsyRepo extends JpaRepository<RapportPsy,Long> {
    List<RapportPsy> findByPsychiatristId(Long psychiatristId);
}
