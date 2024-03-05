package com.example.pidev.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Forum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idForum;
    private String titre;
    private String description;
    private String image;
    private String organisateur;
    private Date dateForum;
    private float budget;
    private int nbrExposantInvite;
    private int nbrEnseignantInvite;
    private int nbrEtudiantInvite;
    private int nbrAlumniInvite;
    private int nbrExposantPresent;
    private int nbrEnseignantPresent;
    private int nbrEtudiantPresent;
    private int nbrAlumniPresent;
}
