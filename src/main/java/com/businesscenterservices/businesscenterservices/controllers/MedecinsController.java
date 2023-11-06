package com.businesscenterservices.businesscenterservices.controllers;

import com.businesscenterservices.businesscenterservices.entities.Medecin;
import com.businesscenterservices.businesscenterservices.entities.Specialite;
import com.businesscenterservices.businesscenterservices.services.MedecinServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class MedecinsController{

    @Autowired
    private MedecinServicesImpl medecinServices;

    // Créer un médecin
    @PostMapping ("Medecins")
    public Medecin createMedecin(@RequestBody Medecin medecin) {
        return medecinServices.createMedecin(medecin);
    }

    // Lire tous les médecins
    @GetMapping("Medecins")
    public List<Medecin> getAllMedecins() {
        return medecinServices.getAllMedecins();
    }

    // Obtenir un médecin par son ID

    @GetMapping("Medecin/{medecinId}")
    public Medecin getMedecinById(@PathVariable("medecinId") Long medecinId) {
        return medecinServices.getMedecinById(medecinId);
    }
    // Mettre à jour un médecin
    @PutMapping("{medecinsId}")
    public Medecin updateMedecin(@PathVariable Long medecinId, @RequestBody Medecin medecin) {
        return medecinServices.updateMedecin(medecinId, medecin);
    }

    // Supprimer un médecin
    @DeleteMapping("{medecinsId}")
    public void deleteMedecin(@PathVariable Long medecinId) {
        medecinServices.deleteMedecin(medecinId);
    }

    // Rechercher des médecins par ville
    @GetMapping("getMedecinByVille/{ville}")
    public List<Medecin> getMedecinsByVille(@PathVariable String ville) {
        return medecinServices.getMedecinsByVille(ville);
    }

    @GetMapping("getSpecialiteByMedecin/{medecinId}")
    public List<Specialite> getSpecialiteByMedecin(@PathVariable Long medecinId){
        return  medecinServices.getSpecilaiteByMedecin(medecinId);
    }


}
