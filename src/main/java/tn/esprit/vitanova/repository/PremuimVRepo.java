package tn.esprit.vitanova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.vitanova.entities.PremuimV;

@Repository
public interface PremuimVRepo extends JpaRepository<PremuimV,Long> {
}
