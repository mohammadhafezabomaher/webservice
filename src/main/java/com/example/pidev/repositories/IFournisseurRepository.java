package com.example.pidev.repositories;

import com.example.pidev.entities.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFournisseurRepository extends JpaRepository <Fournisseur, Long> {
}
