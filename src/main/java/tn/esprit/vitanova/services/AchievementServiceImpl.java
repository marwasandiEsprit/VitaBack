package tn.esprit.vitanova.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.vitanova.entities.Achievement;
import tn.esprit.vitanova.entities.AchievementSlug;
import tn.esprit.vitanova.entities.User;
import tn.esprit.vitanova.entities.UserAchievementHistory;
import tn.esprit.vitanova.repository.AchievementRepository;
import tn.esprit.vitanova.repository.UserAchievementHistoryRepository;
import tn.esprit.vitanova.repository.UserRepo;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AchievementServiceImpl implements IAchievementService {

    private final AchievementRepository achievementRepository;
    private final UserAchievementHistoryRepository userAchievementHistoryRepository;
    private final UserRepo userRepository;

    @Override
    public Achievement saveAchievement(Achievement achievement) {
        return achievementRepository.save(achievement);
    }

    @Override
    public Achievement getAchievementBySlug(AchievementSlug slug) {
        return achievementRepository.findBySlug(slug);
    }

    public List<Achievement> getAllAchievements() {
        return achievementRepository.findAll();
    }

    @Override
    public void deleteAchievement(Long id) {
        achievementRepository.deleteById(id);
    }

    @Override
    public void addAchievementToUser(Long userId, AchievementSlug slug) {

        User u = userRepository.findById(userId).orElseThrow(NullPointerException::new);
        //if user already have the achievement then exit
        if (u.getAchievements().contains(achievementRepository.findBySlug(slug))) {
            return;
        }

        //check if user already have history with that achievement if yes increment points else create new history
        Optional<UserAchievementHistory> userAchievementHistory = userAchievementHistoryRepository.findByUser_IdAndAchievementSlug(userId, slug);
        if (userAchievementHistory.isPresent()) {
            userAchievementHistory.get().setAchievedPoints(userAchievementHistory.get().getAchievedPoints() + 1);
            userAchievementHistory.ifPresent(userAchievementHistoryRepository::save);
            Achievement achievement = achievementRepository.findBySlug(slug);

            //add achievement to user if met the criteria
            if(achievement.getCriteria()==userAchievementHistory.get().getAchievedPoints()){
                u.getAchievements().add(achievement);
                userRepository.save(u);
            }

        } else {
            UserAchievementHistory newAchievement = new UserAchievementHistory();
            newAchievement.setAchievedPoints(1);
            newAchievement.setUser(u);
            newAchievement.setAchievementSlug(slug);
            userAchievementHistoryRepository.save(newAchievement);
        }
    }
}
