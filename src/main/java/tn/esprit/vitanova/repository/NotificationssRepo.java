package tn.esprit.vitanova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.vitanova.entities.Notifications;

public interface NotificationssRepo extends JpaRepository<Notifications,Long> {
}
