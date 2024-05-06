package tn.esprit.vitanova.services;

import tn.esprit.vitanova.entities.Plat;
import tn.esprit.vitanova.entities.PlatConseils;

import java.util.List;

public interface IPlatService {
    public Plat ajouterPlat (Plat plat);
    public PlatConseils ajouterPlatConseils (PlatConseils conseils);
    public void updatePlat (Long idPlat , Plat plat);
    public Plat getPlatbyId (Long idPlat);
    public List<Plat> chercherTousPlat();
    public void supprimerPlat (Long idPlat);
}
