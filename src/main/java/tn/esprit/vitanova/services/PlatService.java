package tn.esprit.vitanova.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.vitanova.entities.Plat;
import tn.esprit.vitanova.entities.PlatConseils;
import tn.esprit.vitanova.entities.User;
import tn.esprit.vitanova.repository.PlatConseilsRepository;
import tn.esprit.vitanova.repository.PlatRepository;
import tn.esprit.vitanova.repository.UserRepo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class PlatService implements IPlatService{

    PlatRepository PlatRepository;
    PlatConseilsRepository PlatConseilsRepository;
    UserRepo userRepo;

    @Override
    public Plat ajouterPlat(Plat Plats) {
        return PlatRepository.save(Plats);
    }
    @Override
    public PlatConseils ajouterPlatConseils (PlatConseils conseils) {
     return    PlatConseilsRepository.save(conseils);
    }
    @Override
    public void updatePlat(Long idPlats, Plat plat) {
        plat.setIdPlat(idPlats);
        PlatRepository.save(plat);
    }

    @Override
    public Plat getPlatbyId(Long idPlats) {
        return PlatRepository.findById(idPlats).orElse(null);
    }
    @Override
    public List<Plat> chercherTousPlat() {
        return PlatRepository.findAll();
    }

    @Override
    public void supprimerPlat(Long idPlats) {
        PlatRepository.deleteById(idPlats);
    }

    public long countPlatsCreatedByUser(Long creatorId) {
        return PlatRepository.countByCreatorId(creatorId);
    }
    public User findCreatorOfPlat(Long platId) {
        Optional<Plat> optionalPlat = PlatRepository.findById(platId);
        if (optionalPlat.isPresent()) {
            Long creatorId = optionalPlat.get().getCreatorId();
            return userRepo.findById(creatorId).orElse(null);
        }
        return null;
    }
    //    @Scheduled(fixedRate = 3000)
    public Map<Long, Long> countPlatesPerUser() {
        Map<Long, Long> platesPerUser = new HashMap<>();
        for (User user : userRepo.findAll()) {
            Long count = PlatRepository.countByCreatorId(user.getId());
            platesPerUser.put(user.getId(), count);
        }
//        System.out.println("Plate Counts Per User: " + platesPerUser);
        return platesPerUser;
    }

}
