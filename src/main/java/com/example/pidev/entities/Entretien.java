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
public class Entretien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEntretien;
    private Date dateEntretien;
    private String lien;
    private String salle;
    @Enumerated(EnumType.STRING)
    private TypeEntretien typeEntretien;
    @OneToOne
    private Candidature candidature;
}
