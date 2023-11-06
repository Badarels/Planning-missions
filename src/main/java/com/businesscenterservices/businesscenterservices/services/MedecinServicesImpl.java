package com.businesscenterservices.businesscenterservices.services;

import com.businesscenterservices.businesscenterservices.entities.Medecin;
import com.businesscenterservices.businesscenterservices.entities.Specialite;
import com.businesscenterservices.businesscenterservices.repositories.MedecinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedecinServicesImpl implements MedecinServices {

    @Autowired
    private MedecinRepository medecinRepository;

    @Override
    public Medecin createMedecin(Medecin medecin) {
        return medecinRepository.save(medecin);
    }

    @Override
    public List<Medecin> getAllMedecins() {
        return medecinRepository.findAll();
    }

    @Override
    public Medecin getMedecinById(Long medecinId) {
        return medecinRepository.findById(medecinId).orElse(null);
    }

    @Override
    public Medecin updateMedecin(Long medecinId, Medecin medecin) {
        if (medecinRepository.existsById(medecinId)) {
            medecin.setId(medecinId);
            return medecinRepository.save(medecin);
        }
        return null;
    }

    @Override
    public void deleteMedecin(Long medecinId) {
        medecinRepository.deleteById(medecinId);
    }

    @Override
    public List<Medecin> getMedecinsByVille(String ville) {
        return medecinRepository.findByAdresseVille(ville);
    }

    @Override
    public List<Specialite> getSpecilaiteByMedecin(Long medecinId) {
        return medecinRepository.getSpecialitesByMedecin(medecinId);

    }
}
