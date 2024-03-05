package com.example.pidev.controllers;


import com.example.pidev.entities.Fournisseur;
import com.example.pidev.entities.Produit;
import com.example.pidev.services.IFournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fournisseur")
public class FournisseurController {
    @Autowired
    IFournisseurService fournisseurService ;

    @PostMapping("/addFournisseur")
    public Fournisseur addFournisseur(@RequestBody Fournisseur f) {
        return fournisseurService.addFournisseur(f) ;
    }

    @GetMapping("/retrieveFournisseur/{id}")
    public Fournisseur retrieveFournisseur(@PathVariable Long id) {
        return fournisseurService.retrieveFournisseur(id);
    }
    @GetMapping("/retrieveAllFournisseurs")
    public List<Fournisseur> retrieveAllFournisseurs() {
        return fournisseurService.retrieveAllFournisseurs();
    }
    @PutMapping("/modifyFournisseur")
    public Fournisseur modifyFournisseur(@RequestBody Fournisseur fournisseur) {
        return fournisseurService.modifyFournisseur(fournisseur);
    }
    @DeleteMapping("/removeFournisseur/{id}")
    public void removeFournisseur(@PathVariable Long id) {
        fournisseurService.removeFournisseur(id);
    }

}
