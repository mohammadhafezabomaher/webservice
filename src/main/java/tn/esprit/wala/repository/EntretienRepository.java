package tn.esprit.wala.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.wala.entities.Entretien;

public interface EntretienRepository extends JpaRepository<Entretien, Integer> {

}
