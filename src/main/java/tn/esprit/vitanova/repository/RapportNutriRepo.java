package tn.esprit.vitanova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.vitanova.entities.RapportNutr;

public interface RapportNutriRepo extends JpaRepository<RapportNutr,Long> {
}
