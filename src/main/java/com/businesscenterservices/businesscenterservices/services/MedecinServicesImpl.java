package com.businesscenterservices.businesscenterservices.services;

import com.businesscenterservices.businesscenterservices.dto.MedecinDTO;
import com.businesscenterservices.businesscenterservices.dto.SpecialiteDTO;
import com.businesscenterservices.businesscenterservices.entities.Adresse;
import com.businesscenterservices.businesscenterservices.entities.Medecin;
import com.businesscenterservices.businesscenterservices.entities.Specialite;
import com.businesscenterservices.businesscenterservices.repositories.AdresseRepository;
import com.businesscenterservices.businesscenterservices.repositories.MedecinRepository;
import com.businesscenterservices.businesscenterservices.repositories.SpecialiteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedecinServicesImpl implements MedecinServices {

    @Autowired
    private MedecinRepository medecinRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AdresseRepository adresseRepository;

    @Autowired
    private SpecialiteRepository specialiteRepository;

    @Override
    @Transactional
    public MedecinDTO createMedecin(MedecinDTO medecinDTO) {
        try {
            // Convertir le DTO en entité
            Medecin medecin = modelMapper.map(medecinDTO, Medecin.class);

            // Convertir l'adresse du DTO en entité
            Adresse adresse = modelMapper.map(medecinDTO.getAdresse(), Adresse.class);

            // Convertir les spécialités du DTO en entités
            List<Specialite> specialites = medecinDTO.getSpecialites().stream()
                    .map(specialiteDTO -> modelMapper.map(specialiteDTO, Specialite.class))
                    .collect(Collectors.toList());

            // Associer l'adresse et les spécialités au médecin
            medecin.setAdresse(adresse);
            medecin.setSpecialites(specialites);

            // Enregistrer l'adresse en base de données
            adresseRepository.save(adresse);

            // Enregistrer les spécialités en base de données
            specialiteRepository.saveAll(specialites);

            // Enregistrer le médecin en base de données
            Medecin savedMedecin = medecinRepository.save(medecin);

            // Convertir l'entité en DTO pour la réponse
            MedecinDTO savedMedecinDTO = modelMapper.map(savedMedecin, MedecinDTO.class);

            return savedMedecinDTO;
        } catch (Exception e) {
            // Gérer les exceptions
            throw new RuntimeException("Erreur lors de la création du médecin", e);
        }
    }
    @Override
    public MedecinDTO getMedecinById(Long medecinId) {
        Optional<Medecin> medecinOptional = medecinRepository.findById(medecinId);
        return medecinOptional.map(medecin -> modelMapper.map(medecin, MedecinDTO.class)).orElse(null);
    }

    @Override
    public List<MedecinDTO> getAllMedecins() {
        List<Medecin> medecins = medecinRepository.findAll();
        return medecins.stream()
                .map(medecin -> modelMapper.map(medecin, MedecinDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public MedecinDTO updateMedecin(Long medecinId, MedecinDTO medecinDTO) {
        if (!medecinRepository.existsById(medecinId)) {
            // Gérer le cas où le médecin n'existe pas
            return null;
        }

        Medecin medecinToUpdate = modelMapper.map(medecinDTO, Medecin.class);
        medecinToUpdate.setId(medecinId);

        Medecin updatedMedecin = medecinRepository.save(medecinToUpdate);
        return modelMapper.map(updatedMedecin, MedecinDTO.class);
    }

    @Override
    public void deleteMedecin(Long medecinId) {
        medecinRepository.deleteById(medecinId);
    }

    @Override
    public List<MedecinDTO> getMedecinsByVille(String ville) {
        List<Medecin> medecins = medecinRepository.findByAdresseVille(ville);
        return medecins.stream()
                .map(medecin -> modelMapper.map(medecin, MedecinDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<SpecialiteDTO> getSpecialitesByMedecin(Long medecinId) {
        Optional<Medecin> medecinOptional = medecinRepository.findById(medecinId);
        if (medecinOptional.isPresent()) {
            Medecin medecin = medecinOptional.get();
            return medecin.getSpecialites().stream()
                    .map(specialite -> modelMapper.map(specialite, SpecialiteDTO.class))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
