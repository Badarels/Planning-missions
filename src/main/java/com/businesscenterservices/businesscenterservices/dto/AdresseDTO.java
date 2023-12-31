package com.businesscenterservices.businesscenterservices.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdresseDTO {

    private Long id;
    private String nomRue;
    private int numeroRue;
    private String complement;
    private String codePostal;
    private String ville;
    private String departement;
    private String region;
}
