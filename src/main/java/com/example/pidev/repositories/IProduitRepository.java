package com.example.pidev.repositories;

import com.example.pidev.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProduitRepository extends JpaRepository<Produit , Long> {
    List<Produit> findByLibelleContainingIgnoreCase(String libelle);
    List<Produit> findByStockLessThan(int threshold);
}
