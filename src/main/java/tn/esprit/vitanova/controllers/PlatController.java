package tn.esprit.vitanova.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.vitanova.Dto.AddedPlat;
import tn.esprit.vitanova.Dto.PlatQuantite;
import tn.esprit.vitanova.Dto.PlatsQauntites;
import tn.esprit.vitanova.entities.Plat;
import tn.esprit.vitanova.entities.PlatConseils;
import tn.esprit.vitanova.services.PlatService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/plat")
@CrossOrigin(origins = "http://localhost:4200")
public class PlatController {
    PlatService platService;

    @PostMapping("/add")
    public Plat ajouterPlat(@RequestBody AddedPlat addedPlatlat) {
        Plat plat = platService.ajouterPlat(addedPlatlat.plat);
        for (PlatConseils conseils : addedPlatlat.conseil) {
            conseils.setPlat(plat);
            PlatConseils conseil = platService.ajouterPlatConseils(conseils) ;
        }
        return plat;
    }

    @PutMapping("/update/{id}")
    public void updatePlat(@PathVariable Long id, @RequestBody Plat plat) {
        platService.updatePlat(id, plat);
    }

    @GetMapping("/getPlatById/{id}")
    public Plat getPlatById(@PathVariable Long id) {
        return platService.getPlatbyId(id);
    }
    @GetMapping("/getAll")
    public List<Plat> getAllPlat() {
        return platService.chercherTousPlat();
    }
    @DeleteMapping("/delete/{id}")
    public  void  supprimerPlat(@PathVariable Long id) {
        platService.supprimerPlat(id);}

    @PostMapping("/CalCalories")
    public float  CalculCalories(@RequestBody PlatsQauntites platsQauntites) {
        float  totalCalories = 0;
        for (PlatQuantite platQuantite : platsQauntites.platsQuantity) {

            Plat plat = platService.getPlatbyId(platQuantite.platId);
            if (plat != null) {
                totalCalories += plat.getNbCalories() * (platQuantite.quantity / plat.getQuantity());
            }
        }
        return totalCalories;
    }

}
