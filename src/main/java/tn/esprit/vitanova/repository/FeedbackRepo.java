package tn.esprit.vitanova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.vitanova.entities.Feedback;

import java.util.List;

public interface FeedbackRepo extends JpaRepository<Feedback,Long> {
    List<Feedback> findByTherapistId(Long therapistId);
    boolean existsByTherapistIdAndUserId(Long therapistId, Long userId);
}
