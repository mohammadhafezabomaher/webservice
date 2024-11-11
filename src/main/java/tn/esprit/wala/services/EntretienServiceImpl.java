package tn.esprit.wala.services;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.wala.entities.Entretien;
import tn.esprit.wala.repository.EntretienRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EntretienServiceImpl {
    @Autowired
    private EntretienRepository entretienRepository;

    public Entretien getEntretienById(int id) {
        return entretienRepository.findById(id).orElse(null);
    }

    public List<Entretien> getAllEntretiens() {
        return entretienRepository.findAll();
    }

    public Entretien createEntretien(Entretien entretien) {
        return entretienRepository.save(entretien);
    }

    public Entretien updateEntretien(int id, Entretien updatedEntretien) {
        Optional<Entretien> existingEntretienOpt = entretienRepository.findById(id);
        if (existingEntretienOpt.isPresent()) {
            Entretien existingEntretien = existingEntretienOpt.get();
            existingEntretien.setDateEntretien(updatedEntretien.getDateEntretien());
            existingEntretien.setLien(updatedEntretien.getLien());
            existingEntretien.setSalle(updatedEntretien.getSalle());

            // Update the set of Enseignants if needed
            existingEntretien.setBloc(updatedEntretien.getBloc());

            return entretienRepository.save(existingEntretien);
        } else {
            return null; // Handle not found case
        }
    }

    public void deleteEntretien(int id) {
        entretienRepository.deleteById(id);
    }
}
