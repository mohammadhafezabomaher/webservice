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
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEtudiant;
    private String faculte;
    @Enumerated(EnumType.STRING)
    private TypeEtudiant typeEtudiant;
    @OneToOne
    private Contact contact;
    @ManyToOne
    private NiveauSpecialite niveauSpecialite;
}
