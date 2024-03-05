package com.example.pidev.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Candidature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCandidature;
    private Date dateCandidature;
    private String titre;
    private String contenue;
    private String cv;
    private String lettreMotivation;
    private String Etat;
    @ManyToOne
    private Etudiant etudiant;
    @ManyToOne
    private Offre offre;
}
