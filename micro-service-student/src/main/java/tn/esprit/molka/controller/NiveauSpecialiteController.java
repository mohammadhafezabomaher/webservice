package tn.esprit.molka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.molka.entities.NiveauSpecialite;
import tn.esprit.molka.services.NiveauSpecialiteService;

import java.util.List;

@RestController
@RequestMapping("/niveauSpecialite")
public class NiveauSpecialiteController {
    @Autowired
    private NiveauSpecialiteService niveauSpecialiteService;

    @GetMapping("find/{id}")
    public ResponseEntity<NiveauSpecialite> getNiveauSpecialiteById(@PathVariable int id) {
        NiveauSpecialite niveauSpecialite = niveauSpecialiteService.getNiveauSpecialiteById(id);
        return ResponseEntity.ok(niveauSpecialite);
    }

    @GetMapping("/findall")
    public ResponseEntity<List<NiveauSpecialite>> getAllNiveauSpecialites() {
        List<NiveauSpecialite> niveauSpecialites = niveauSpecialiteService.getAllNiveauSpecialites();
        return ResponseEntity.ok(niveauSpecialites);
    }

    @PostMapping("/add")
    public ResponseEntity<NiveauSpecialite> createNiveauSpecialite(@RequestBody NiveauSpecialite niveauSpecialite) {
        NiveauSpecialite createdNiveauSpecialite = niveauSpecialiteService.createNiveauSpecialite(niveauSpecialite);
        return ResponseEntity.ok(createdNiveauSpecialite);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<NiveauSpecialite> updateNiveauSpecialite(@PathVariable int id, @RequestBody NiveauSpecialite niveauSpecialite) {
        NiveauSpecialite updatedNiveauSpecialite = niveauSpecialiteService.updateNiveauSpecialite(id, niveauSpecialite);
        return ResponseEntity.ok(updatedNiveauSpecialite);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteNiveauSpecialite(@PathVariable int id) {
        niveauSpecialiteService.deleteNiveauSpecialite(id);
        return ResponseEntity.noContent().build();
    }
}
