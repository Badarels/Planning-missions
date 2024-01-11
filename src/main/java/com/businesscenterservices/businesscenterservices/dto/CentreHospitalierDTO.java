package com.businesscenterservices.businesscenterservices.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CentreHospitalierDTO {
    private Long id;
    private String email_ch;
    private String nom_ch;
    private String siret;
    private String telephone;
    private AdresseDTO adresse;
    private List<MedecinDTO> medecins;
    private boolean archived;
}
