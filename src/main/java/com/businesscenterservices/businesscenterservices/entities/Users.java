package com.businesscenterservices.businesscenterservices.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomUser;
    private String prenomUser;
    private String adresseuser;
    private String telephoneUser;
    private String sexeUser;
    private Date date_naissanceUser;
    private String numero_piece_identiteUser;
    private String passwordUser;
    private String emailUser;

    @Column(columnDefinition = "boolean default true")
    private boolean status;
    @Column(columnDefinition = "boolean default false")
    private boolean passwordChanged;
    @Column(columnDefinition = "boolean default false")
    private boolean archive;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "roles",referencedColumnName="id")
    private Roles roles;
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Appel> appelList = new ArrayList<>();


}
