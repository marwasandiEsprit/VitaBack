package tn.esprit.vitanova.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.vitanova.entities.Plat;
import tn.esprit.vitanova.entities.PlatConseils;
import tn.esprit.vitanova.repository.PlatConseilsRepository;
import tn.esprit.vitanova.repository.PlatRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class PlatService implements IPlatService{

    PlatRepository PlatRepository;
    PlatConseilsRepository PlatConseilsRepository;
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


}
