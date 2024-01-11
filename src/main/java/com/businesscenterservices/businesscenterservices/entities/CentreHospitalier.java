package com.businesscenterservices.businesscenterservices.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CentreHospitalier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email_ch;
    private String nom_ch;
    private String siret;
    private String telephone;
    private boolean archived;

    @OneToOne
    @JsonIgnoreProperties("centreHospitalier")
    private Adresse adresse;

    @OneToMany(mappedBy = "centreHospitalier", cascade = CascadeType.ALL)
    private List<Medecin> medecins;
}
