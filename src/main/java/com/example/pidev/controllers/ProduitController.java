package com.example.pidev.controllers;


import com.example.pidev.entities.Produit;
import com.example.pidev.services.IProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/produit")
public class ProduitController {

    @Autowired
    IProduitService produitService ;

    @PostMapping("/addProduit")
    public Produit addProduit(@RequestBody Produit p) {
       return produitService.addProduit(p) ;
    }

    @GetMapping("/retrieveProduit/{id}")
    public Produit retrieveProduit(@PathVariable Long id) {
        return produitService.retrieveProduit(id);
    }
    @GetMapping("/retrieveAllProduits")
    public List<Produit> retrieveAllProduits() {
        return produitService.retrieveAllProduits();
    }
    @PutMapping("/modifyProduit/{id}")
    public Produit modifyProduit(@PathVariable Long id,@RequestBody Produit produit) {
        produit.setIdProduit(id);
        return produitService.modifyProduit(produit);
    }
    @DeleteMapping("/removeProduit/{id}")
    public void removeProduit(@PathVariable Long id) {
        produitService.removeProduit(id);
    }

    @GetMapping("/search")
    public List<Produit> searchProduitsByLibelle(@RequestParam String libelle) {
        return produitService.searchProduitsByLibelle(libelle);
    }
    @GetMapping("/retrieveProduits")
    public ResponseEntity<List<Produit>> retrieveProduits(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Produit> produitsPage = produitService.retrieveProduits(pageable);
        List<Produit> produits = produitsPage.getContent();
        return ResponseEntity.ok().body(produits);
    }

}
