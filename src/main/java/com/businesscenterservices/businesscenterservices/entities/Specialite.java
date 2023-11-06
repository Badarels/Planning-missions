package com.businesscenterservices.businesscenterservices.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Specialite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomSpecialite;

    @JsonBackReference
    @ManyToMany(mappedBy = "specialites", fetch = FetchType.LAZY)
    private List<Medecin> medecins = new ArrayList<>();

}
