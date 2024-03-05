package com.example.pidev.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Fournisseur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFournisseur;
    @Enumerated(EnumType.STRING)
    private TypeFournisseur typeFournisseur;
    @OneToOne (cascade = CascadeType.ALL)
    private Contact contact;
    @OneToMany (mappedBy = "fournisseur")
    private List<Produit> produits;

}
