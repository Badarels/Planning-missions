package com.businesscenterservices.businesscenterservices.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedecinDTO {
    private Long id;
    private String nomMedecin;
    private String prenomMedecin;
    private String emailMedecin;
    private String sexeMedecin;
    private Date dateDeNaissanceMedecin;
    private Date dateEcheance;
    private String lieuDeNaissanceMedecin;
    private String numeroSecuriteSocialeMedecin;
    private String telephoneMedecin_1;
    private String telephoneMedecin_2;
    private String statutMedecin;
    private String numeroRpps;
    private String inscription_A_lordre;
    private AdresseDTO adresse;
    private String qualifications;
    private List<SpecialiteDTO> specialites;
    private CentreHospitalierDTO centreHospitalier;
}
