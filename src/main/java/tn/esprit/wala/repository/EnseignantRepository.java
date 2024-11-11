package tn.esprit.wala.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.wala.entities.Enseignant;

import java.util.List;

@Repository

public interface EnseignantRepository extends JpaRepository<Enseignant, Long>
{

    List<Enseignant> findByIdentifiant(String identifiant);
}

