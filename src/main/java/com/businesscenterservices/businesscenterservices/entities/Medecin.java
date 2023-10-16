package com.businesscenterservices.businesscenterservices.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medecin{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomMedecin;
    private String prenomMedecin;
    private String emailMedecin;
    private Date dateDeNaissanceMedecin;
    private String lieuDeNaissanceMedecin;
    private String numeroSecuriteSocialeMedecin;
    private String telephoneMedecin_1;
    private String telephoneMedecin_2;
    private String statutMedecin;
    private String numeroRpps;
    private boolean inscription_A_lordre;
    @OneToOne
    private Adresse adresse;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "medecin_specialite",
            joinColumns = @JoinColumn(name = "medecin_id"),
            inverseJoinColumns = @JoinColumn(name = "specialite_id")
    )
    private Set<Specialite> specialites;
    @OneToMany(mappedBy = "medecin", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Set<Qualification> qualifications;
}
