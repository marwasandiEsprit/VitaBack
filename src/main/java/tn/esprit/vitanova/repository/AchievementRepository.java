package tn.esprit.vitanova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.vitanova.entities.Achievement;
import tn.esprit.vitanova.entities.AchievementSlug;

public interface AchievementRepository extends JpaRepository<Achievement, Long> {
    Achievement findBySlug(AchievementSlug slug);


}