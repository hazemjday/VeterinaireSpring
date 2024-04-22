package com.example.nonAuth.modal;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imageUrl;
    private String race;
    private LocalDate birthdate;
    private LocalDate latestVaccinationDate;
    private Integer weight;
    private String Couleur;
    private String origine;
    private String confinement;
    private String utilisation;
    private String sexe;
    private String espId;

    @ManyToOne
    @JoinColumn(name="proprietaire_id")
    @JsonBackReference
    private Proprietaire proprietaire;
}
