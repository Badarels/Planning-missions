package com.businesscenterservices.businesscenterservices.services;

import com.businesscenterservices.businesscenterservices.controllers.Exceptions.ResourceNotFoundException;
import com.businesscenterservices.businesscenterservices.dto.AdresseDTO;
import com.businesscenterservices.businesscenterservices.dto.CentreHospitalierDTO;
import com.businesscenterservices.businesscenterservices.dto.MedecinDTO;
import com.businesscenterservices.businesscenterservices.entities.Adresse;
import com.businesscenterservices.businesscenterservices.repositories.AdresseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdresseServicesImpl implements  AdresseServices {
    @Autowired
    private AdresseRepository adresseRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AdresseDTO createAdresse(AdresseDTO adresseDTO) {
        Adresse adresse = modelMapper.map(adresseDTO, Adresse.class);
        Adresse savedAdresse = adresseRepository.save(adresse);
        return modelMapper.map(savedAdresse, AdresseDTO.class);
    }

    @Override
    public AdresseDTO getAdresseById(Long adresseId) {
        Optional<Adresse> adresseOptional = adresseRepository.findById(adresseId);
        return adresseOptional.map(adresse -> modelMapper.map(adresse, AdresseDTO.class)).orElse(null);
    }

    @Override
    public List<AdresseDTO> getAllAdresses() {
        List<Adresse> adresses = adresseRepository.findAll();
        return adresses.stream()
                .map(adresse -> modelMapper.map(adresse, AdresseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public AdresseDTO updateAdresse(Long adresseId, AdresseDTO adresseDTO) {
        if (!adresseRepository.existsById(adresseId)) {
            // Gérer le cas où l'adresse n'existe pas
            return null;
        }

        Adresse adresseToUpdate = modelMapper.map(adresseDTO, Adresse.class);
        adresseToUpdate.setId(adresseId);

        Adresse updatedAdresse = adresseRepository.save(adresseToUpdate);
        return modelMapper.map(updatedAdresse, AdresseDTO.class);
    }

    @Override
    public void deleteAdresse(Long adresseId) {
        adresseRepository.deleteById(adresseId);
    }

    @Override
    public List<CentreHospitalierDTO> getCentreHospitaliersByAdresse(Long adresseId) {
        Optional<Adresse> adresseOptional = adresseRepository.findById(adresseId);
        if (adresseOptional.isPresent()) {
            Adresse adresse = adresseOptional.get();
            return adresse.getCentreHospitalier().stream()
                    .map(centreHospitalier -> modelMapper.map(centreHospitalier, CentreHospitalierDTO.class))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public List<MedecinDTO> getMedecinsByAdresse(Long adresseId) {
        Optional<Adresse> adresseOptional = adresseRepository.findById(adresseId);
        if (adresseOptional.isPresent()) {
            Adresse adresse = adresseOptional.get();
            return adresse.getMedecins().stream()
                    .map(medecin -> modelMapper.map(medecin, MedecinDTO.class))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
