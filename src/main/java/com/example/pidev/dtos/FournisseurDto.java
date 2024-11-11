package com.example.pidev.dtos;


import com.example.pidev.entities.Produit;
import com.example.pidev.entities.TypeFournisseur;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FournisseurDto {

    private Long idFournisseur;
    @Enumerated(EnumType.STRING)
    private TypeFournisseur typeFournisseur;
    private ContactDto contact;
    private List<Produit> produits;

}
