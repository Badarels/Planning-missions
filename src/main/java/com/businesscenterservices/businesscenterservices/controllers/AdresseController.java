package com.businesscenterservices.businesscenterservices.controllers;

import com.businesscenterservices.businesscenterservices.dto.AdresseDTO;
import com.businesscenterservices.businesscenterservices.dto.CentreHospitalierDTO;
import com.businesscenterservices.businesscenterservices.dto.MedecinDTO;
import com.businesscenterservices.businesscenterservices.services.AdresseServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class AdresseController {
    @Autowired
    private AdresseServicesImpl adresseService;

    @GetMapping("Adresses/{adresseId}")
    public ResponseEntity<AdresseDTO> getAdresseById(@PathVariable Long adresseId) {
        AdresseDTO adresseDTO = adresseService.getAdresseById(adresseId);
        return ResponseEntity.ok(adresseDTO);
    }

    @GetMapping("Adresses")
    public ResponseEntity<List<AdresseDTO>> getAllAdresses() {
        List<AdresseDTO> adresseDTOs = adresseService.getAllAdresses();
        return ResponseEntity.ok(adresseDTOs);
    }

    @PostMapping("Adresses")
    public ResponseEntity<AdresseDTO> createAdresse(@RequestBody AdresseDTO adresseDTO) {
        AdresseDTO createdAdresse = adresseService.createAdresse(adresseDTO);
        return new ResponseEntity<>(createdAdresse, HttpStatus.CREATED);
    }

    @PutMapping("Adresses/{adresseId}")
    public ResponseEntity<AdresseDTO> updateAdresse(@PathVariable Long adresseId, @RequestBody AdresseDTO adresseDTO) {
        AdresseDTO updatedAdresse = adresseService.updateAdresse(adresseId, adresseDTO);
        if (updatedAdresse != null) {
            return ResponseEntity.ok(updatedAdresse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("Adresses/{adresseId}")
    public ResponseEntity<Void> deleteAdresse(@PathVariable Long adresseId) {
        adresseService.deleteAdresse(adresseId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("Adresses/{adresseId}/centreHospitaliers")
    public ResponseEntity<List<CentreHospitalierDTO>> getCentreHospitaliersByAdresse(@PathVariable Long adresseId) {
        List<CentreHospitalierDTO> centreHospitalierDTOs = adresseService.getCentreHospitaliersByAdresse(adresseId);
        return ResponseEntity.ok(centreHospitalierDTOs);
    }

    @GetMapping("/{adresseId}/medecins")
    public ResponseEntity<List<MedecinDTO>> getMedecinsByAdresse(@PathVariable Long adresseId) {
        List<MedecinDTO> medecinDTOs = adresseService.getMedecinsByAdresse(adresseId);
        return ResponseEntity.ok(medecinDTOs);
    }
}
