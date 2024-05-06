package tn.esprit.vitanova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.vitanova.entities.Chat;

public interface ChatRepo extends JpaRepository<Chat,Long> {
}
