package tn.esprit.vitanova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.vitanova.entities.Abonnement;

@Repository
public interface AbonnRepo extends JpaRepository<Abonnement,Long> {
}
