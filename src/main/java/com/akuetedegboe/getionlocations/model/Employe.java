package com.akuetedegboe.getionlocations.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employe")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenoms")
    private String prenoms;

    @Column(name = "login")
    private String login;

    @Column(name = "mdp")
    private String mdp;


    @Column(name = "adresse")
    private String adresse;


    @Column(name = "poste")
    @Enumerated(EnumType.STRING)
    private Poste poste;
}
