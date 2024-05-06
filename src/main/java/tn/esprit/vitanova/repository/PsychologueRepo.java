package tn.esprit.vitanova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.vitanova.entities.Psychologue;
@Repository
public interface PsychologueRepo extends JpaRepository<Psychologue,Long> {
}
