package tn.esprit.vitanova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.vitanova.entities.Client;

public interface ClientRepo extends JpaRepository<Client,Long> {
}
