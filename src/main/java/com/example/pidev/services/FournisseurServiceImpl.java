package com.example.pidev.services;

import com.example.pidev.entities.Fournisseur;
import com.example.pidev.repositories.IFournisseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FournisseurServiceImpl implements IFournisseurService{
    @Autowired
    IFournisseurRepository IFournisseurRepository;
    @Override
    public List<Fournisseur> retrieveAllFournisseurs() {
        return (List<Fournisseur>) IFournisseurRepository.findAll() ;
    }

    @Override
    public Fournisseur retrieveFournisseur(Long idFournisseur) {
        return IFournisseurRepository.findById(idFournisseur).get();
    }

    @Override
    public Fournisseur addFournisseur(Fournisseur f) {
        return IFournisseurRepository.save(f);
    }

    @Override
    public void removeFournisseur(Long idFournisseur) {
        IFournisseurRepository.deleteById(idFournisseur);
    }

    @Override
    public Fournisseur modifyFournisseur(Fournisseur fournisseur) {
        return IFournisseurRepository.save(fournisseur);
    }
}
