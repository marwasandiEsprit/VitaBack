package tn.esprit.vitanova.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tn.esprit.vitanova.entities.Abonnement;
import tn.esprit.vitanova.repository.AbonnRepo;


@AllArgsConstructor
public class AbonnementController {

    private AbonnRepo abonnRepo;
    @PostMapping("/add_abonn")
    @ResponseBody

    public Abonnement addAbonnement(Abonnement ab){
        return abonnRepo.save (ab);
    }
}
