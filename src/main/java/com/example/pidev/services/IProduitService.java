package com.example.pidev.services;

import com.example.pidev.entities.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.io.IOException;

import java.util.List;

public interface IProduitService {
    public List<Produit> retrieveAllProduits();
    public Produit retrieveProduit(Long idProduit);
    public Produit addProduit(Produit p);
    public void removeProduit(Long idProduit);
    public Produit modifyProduit(Produit produit);
    List<Produit> searchProduitsByLibelle(String libelle);
    Page<Produit> retrieveProduits(Pageable pageable);
    public void performDailyCleanup();

}
