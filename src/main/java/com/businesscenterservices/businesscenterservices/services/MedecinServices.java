package com.businesscenterservices.businesscenterservices.services;

import com.businesscenterservices.businesscenterservices.entities.Medecin;
import com.businesscenterservices.businesscenterservices.entities.Specialite;

import java.util.List;

public interface MedecinServices {


    // Créer un médecin
    Medecin createMedecin(Medecin medecin);
    // Lire tous les médecins
    List<Medecin> getAllMedecins();
    // Obtenir un médecin par son ID
    Medecin getMedecinById(Long medecinId);
    // Mettre à jour un médecin
    Medecin updateMedecin(Long medecinId, Medecin medecin);
    // Supprimer un médecin
    void deleteMedecin(Long medecinId);
    // Rechercher des médecins par ville
    List<Medecin> getMedecinsByVille(String ville);

    //Recherche medecin par Specilaité
    List<Specialite> getSpecilaiteByMedecin(Long medecinId);


}
