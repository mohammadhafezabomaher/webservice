package com.example.pidev.services;

import com.example.pidev.entities.Produit;
import com.example.pidev.repositories.IProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.io.IOException;
import java.util.List;

@Service
public class ProduitServiceImpl implements IProduitService{
    @Autowired
    IProduitRepository IProduitRepository;

    @Override
    public List<Produit> retrieveAllProduits() {
        return (List<Produit>) IProduitRepository.findAll();
    }

    @Override
    public Produit retrieveProduit(Long idProduit) {
        return IProduitRepository.findById(idProduit).get();
    }

    @Override
    public Produit addProduit(Produit p) {
        return IProduitRepository.save(p);
    }

    @Override
    public void removeProduit(Long idProduit) {
        IProduitRepository.deleteById(idProduit);
    }

    @Override
    public Produit modifyProduit(Produit produit) {
        return IProduitRepository.save(produit);
    }

    @Override
    public List<Produit> searchProduitsByLibelle(String libelle) {
        return IProduitRepository.findByLibelleContainingIgnoreCase(libelle);
    }
    public Page<Produit> retrieveProduits(Pageable pageable) {
        return IProduitRepository.findAll(pageable);
    }




}


