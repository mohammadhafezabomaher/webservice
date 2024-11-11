package tn.esprit.wala.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import tn.esprit.wala.dtos.EnseignantDto;
import tn.esprit.wala.entities.Enseignant;
import tn.esprit.wala.services.EnseignantServiceImpl;


import java.io.File;
import java.nio.file.Paths;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/enseignant")

public class EnseignantController {
    @Autowired
    private EnseignantServiceImpl enseignantService;

    @GetMapping("find/{id}")
    public ResponseEntity<EnseignantDto> getEnseignantById(@PathVariable Long id) {
        EnseignantDto enseignant = enseignantService.getEnseignantById(id);
        return ResponseEntity.ok(enseignant);
    }



    @GetMapping("/findall")
    public ResponseEntity<List<EnseignantDto>> getAllEtudiants() {
        List<EnseignantDto> enseignant = enseignantService.getAllEnseignants();
        return ResponseEntity.ok(enseignant);
    }

    @PostMapping("/add")
    public ResponseEntity<Enseignant> createEtudiant(@RequestBody EnseignantDto enseignant) {
        Enseignant createdEnseignant = enseignantService.createEnseignant(enseignant);
        return ResponseEntity.ok(createdEnseignant);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Enseignant> updateEnseignant(@PathVariable Long id, @RequestBody Enseignant enseignant) {
        Enseignant updatedEnseignant = enseignantService.updateEnseignant(id, enseignant);
        return ResponseEntity.ok(updatedEnseignant);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteEnseignant(@PathVariable Long id) {
        enseignantService.deleteEnseignant(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/searchByIdentifiant/{identifiant}")
    public ResponseEntity<List<Enseignant>> searchEnseignantsByIdentifiant(@PathVariable String identifiant) {
        List<Enseignant> enseignants = enseignantService.getEnseignantsByIdentifiant(identifiant);
        return ResponseEntity.ok(enseignants);
    }
}
