package tn.esprit.vitanova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.vitanova.entities.Quotes;

public interface QuotesRepo extends JpaRepository<Quotes,Long> {
}
