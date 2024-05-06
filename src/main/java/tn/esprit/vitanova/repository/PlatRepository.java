package tn.esprit.vitanova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.vitanova.entities.Plat;

public interface PlatRepository extends JpaRepository<Plat,Long> {
}
