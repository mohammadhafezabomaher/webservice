package com.example.pidev.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NiveauSpecialite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idNiveauSpecialite;
    private String libelle;
    @ManyToOne
    private Niveau niveau;
    @ManyToOne
    private Specialite specialite;
}
