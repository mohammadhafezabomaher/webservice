package tn.esprit.wala.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.wala.dtos.EnseignantDto;
import tn.esprit.wala.entities.Enseignant;
import tn.esprit.wala.entities.Entretien;
import tn.esprit.wala.services.EnseignantServiceImpl;
import tn.esprit.wala.services.EntretienServiceImpl;

import java.util.List;


@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/entretien")
public class EntretienController {
    @Autowired
    private EntretienServiceImpl entretienService;

    @GetMapping("find/{id}")
    public ResponseEntity<Entretien> getEntretienById(@PathVariable int id) {
        Entretien entretien = entretienService.getEntretienById(id);
        return ResponseEntity.ok(entretien);
    }

    @GetMapping("/findall")
    public ResponseEntity<List<Entretien>> getAllEntretiens() {
        List<Entretien> entretien = entretienService.getAllEntretiens();
        return ResponseEntity.ok(entretien);
    }

    @PostMapping("/add")
    public ResponseEntity<Entretien> createEntretien(@RequestBody Entretien entretien) {
        Entretien createdEntretien = entretienService.createEntretien(entretien);
        return ResponseEntity.ok(createdEntretien);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Entretien> updateEntretien(@PathVariable int id, @RequestBody Entretien entretien) {
        Entretien updatedEntretien = entretienService.updateEntretien(id, entretien);
        return ResponseEntity.ok(updatedEntretien);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteEntretien(@PathVariable int id) {
        entretienService.deleteEntretien(id);
        return ResponseEntity.noContent().build();
    }
}
