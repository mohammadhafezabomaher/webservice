package com.example.pidev.controllers;

import com.example.pidev.dtos.FournisseurDto;
import com.example.pidev.entities.Fournisseur;
import com.example.pidev.services.FournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("find")
public class FournisseurController {
    @Autowired
    private FournisseurService fournisseurService;

    @GetMapping("/find/{id}")
    public ResponseEntity<FournisseurDto> getFournisseurById(@PathVariable Long id) {
        FournisseurDto fournisseur = fournisseurService.getFournisseurById(id);
        return ResponseEntity.ok(fournisseur);
    }

    @GetMapping("/findall")
    public ResponseEntity<List<FournisseurDto>> getAllFournisseurs() {
        List<FournisseurDto> fournisseurs = fournisseurService.getAllFournisseurs();
        return ResponseEntity.ok(fournisseurs);
    }

    @PostMapping("/add")
    public ResponseEntity<Fournisseur> createFournisseur(@RequestBody FournisseurDto fournisseurDto) {
        Fournisseur createdFournisseur = fournisseurService.createFournisseur(fournisseurDto);
        return ResponseEntity.ok(createdFournisseur);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Fournisseur> updateFournisseur(@PathVariable Long id, @RequestBody Fournisseur fournisseur) {
        Fournisseur updatedFournisseur = fournisseurService.updateFournisseur(id, fournisseur);
        return ResponseEntity.ok(updatedFournisseur);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFournisseur(@PathVariable Long id) {
        fournisseurService.deleteFournisseur(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/searchByType/{type}")
    public ResponseEntity<List<FournisseurDto>> searchFournisseursByType(@PathVariable String type) {
        List<FournisseurDto> fournisseurs = fournisseurService.getFournisseursByType(type);
        return ResponseEntity.ok(fournisseurs);
    }
}
