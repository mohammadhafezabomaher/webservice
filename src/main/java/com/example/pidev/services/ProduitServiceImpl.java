package com.example.pidev.services;

import com.example.pidev.entities.Produit;
import com.example.pidev.repositories.IProduitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class ProduitServiceImpl implements IProduitService{
    @Autowired
    IProduitRepository IProduitRepository;
    private boolean cleanupInProgress = false;

    @Override
    public List<Produit> retrieveAllProduits() {
        return (List<Produit>) IProduitRepository.findAll();
    }

    @Override
    public Produit retrieveProduit(Long idProduit) {
        return IProduitRepository.findById(idProduit).get();
    }

    @Override
    public Produit addProduit(Produit p) {
        return IProduitRepository.save(p);
    }

    @Override
    public void removeProduit(Long idProduit) {
        IProduitRepository.deleteById(idProduit);
    }

    @Override
    public Produit modifyProduit(Produit produit) {
        return IProduitRepository.save(produit);
    }

    @Override
    public List<Produit> searchProduitsByLibelle(String libelle) {
        return IProduitRepository.findByLibelleContainingIgnoreCase(libelle);
    }
    public Page<Produit> retrieveProduits(Pageable pageable) {
        return IProduitRepository.findAll(pageable);
    }

    @Override
    @Scheduled(cron = "0 */10 * * * *") // Execute every 10 minutes
    public void performDailyCleanup() {
        if (!cleanupInProgress) {
            cleanupInProgress = true;
            try {
                List<Produit> lowStockProducts = IProduitRepository.findByStockLessThan(10);
                // Check if there are low stock products
                if (!lowStockProducts.isEmpty()) {
                    // Process low stock products
                    for (Produit produit : lowStockProducts) {
                        produit.setStock(4); // Example: Setting stock to 0 for demonstration
                        IProduitRepository.save(produit); // Example: Saving updated product
                    }
                }
            } finally {
                cleanupInProgress = false;
            }
        }
    }

}


