package tn.esprit.vitanova.services;

import tn.esprit.vitanova.entities.Achievement;
import tn.esprit.vitanova.entities.AchievementSlug;

import java.util.List;

public interface IAchievementService {
    Achievement saveAchievement(Achievement achievement);

    Achievement getAchievementBySlug(AchievementSlug slug);

    List<Achievement> getAllAchievements();

    void deleteAchievement(Long id);

    void addAchievementToUser(Long userId, AchievementSlug slug);
}
