package com.example.pidev.services;

import com.example.pidev.entities.Fournisseur;

import java.util.List;

public interface IFournisseurService {
    public List<Fournisseur> retrieveAllFournisseurs();
    public Fournisseur retrieveFournisseur(Long idFournisseur);
    public Fournisseur addFournisseur(Fournisseur f);
    public void removeFournisseur(Long idFournisseur);
    public Fournisseur modifyFournisseur(Fournisseur fournisseur);
    public List<Fournisseur> retrieveFournisseurByType(String type);
}
