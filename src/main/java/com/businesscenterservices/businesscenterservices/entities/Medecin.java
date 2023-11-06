package com.businesscenterservices.businesscenterservices.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private String sexeMedecin;
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

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "medecin_specialite",
            joinColumns = @JoinColumn(name = "medecin_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "specialite_id", referencedColumnName = "id")
    )
    private List<Specialite> specialites=new ArrayList<>();
    @OneToMany(mappedBy = "medecin", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Qualification> qualifications=new ArrayList<>();
}
