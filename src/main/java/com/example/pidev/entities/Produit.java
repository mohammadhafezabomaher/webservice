package com.example.pidev.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduit;
    private String libelle;
    private String image;
    private int stock;
    private float prix;
    @ManyToOne
    private Fournisseur fournisseur;
}
