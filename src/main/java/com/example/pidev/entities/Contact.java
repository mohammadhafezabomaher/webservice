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
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContact;
    private String nom;
    private String prenom;
    private Long cin;
    private String email;
    private String password;
    private String image;
    private Date dateNaissance;
    private Long telephone;
    private String adresse;
    private TypeRole role;
    @OneToOne
    private Fournisseur fournisseur ;
}
