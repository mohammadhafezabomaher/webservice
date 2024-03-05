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
public class Offre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOffre;
    private String titre;
    private String description;
    private Date dateDebut;
    private Date dateFin;
    @Enumerated(EnumType.STRING)
    private TypeOffre typeOffre;
    @ManyToOne
    private Exposant exposant;
}
