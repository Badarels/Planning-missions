package com.businesscenterservices.businesscenterservices.controllers;

import com.businesscenterservices.businesscenterservices.entities.Medecin;
import com.businesscenterservices.businesscenterservices.services.MedecinServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/medecins")
public class MedecinsController{

    @Autowired
    private MedecinServicesImpl medecinServices;

    // Créer un médecin
    @PostMapping ("/api/Medecins")
    public Medecin createMedecin(@RequestBody Medecin medecin) {
        return medecinServices.createMedecin(medecin);
    }

    // Lire tous les médecins
    @GetMapping("/api/Medecins")
    public List<Medecin> getAllMedecins() {
        return medecinServices.getAllMedecins();
    }

    // Obtenir un médecin par son ID
    @GetMapping("/api/{medecinsId}")
    public Medecin getMedecinById(@PathVariable Long medecinId) {
        return medecinServices.getMedecinById(medecinId);
    }

    // Mettre à jour un médecin
    @PutMapping("/api/{medecinsId}")
    public Medecin updateMedecin(@PathVariable Long medecinId, @RequestBody Medecin medecin) {
        return medecinServices.updateMedecin(medecinId, medecin);
    }

    // Supprimer un médecin
    @DeleteMapping("/api/{medecinsId}")
    public void deleteMedecin(@PathVariable Long medecinId) {
        medecinServices.deleteMedecin(medecinId);
    }

    // Rechercher des médecins par ville
    @GetMapping("/api/getMedecinBy/ville/{ville}")
    public List<Medecin> getMedecinsByVille(@PathVariable String ville) {
        return medecinServices.getMedecinsByVille(ville);
    }


}
