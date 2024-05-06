package tn.esprit.vitanova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.vitanova.entities.AchievementSlug;
import tn.esprit.vitanova.entities.UserAchievementHistory;

import java.util.Optional;

public interface UserAchievementHistoryRepository extends JpaRepository<UserAchievementHistory, Long> {

    Optional<UserAchievementHistory> findByUser_IdAndAchievementSlug(Long id, AchievementSlug achievementSlug);
}