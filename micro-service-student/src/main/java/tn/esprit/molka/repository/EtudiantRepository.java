package tn.esprit.molka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.molka.entities.Etudiant;

import java.util.List;

public interface EtudiantRepository extends JpaRepository<Etudiant,Long> {
    List<Etudiant> findByIdentifiant(String identifiant);
}
