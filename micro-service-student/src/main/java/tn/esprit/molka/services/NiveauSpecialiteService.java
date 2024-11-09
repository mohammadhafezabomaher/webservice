package tn.esprit.molka.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.molka.entities.NiveauSpecialite;
import tn.esprit.molka.repository.NiveauSpecialiteRepository;

import java.util.List;

@Service
public class NiveauSpecialiteService {
    @Autowired
    private NiveauSpecialiteRepository niveauSpecialiteRepository;

    public NiveauSpecialite getNiveauSpecialiteById(int id) {
        return niveauSpecialiteRepository.findById(id).orElse(null);
    }

    public List<NiveauSpecialite> getAllNiveauSpecialites() {
        return niveauSpecialiteRepository.findAll();
    }

    public NiveauSpecialite createNiveauSpecialite(NiveauSpecialite niveauSpecialite) {
        return niveauSpecialiteRepository.save(niveauSpecialite);
    }

    public NiveauSpecialite updateNiveauSpecialite(int id, NiveauSpecialite updatedNiveauSpecialite) {
        NiveauSpecialite existingNiveauSpecialite = niveauSpecialiteRepository.findById(id).orElse(null);
        if (existingNiveauSpecialite != null) {

            existingNiveauSpecialite.setLibelle(updatedNiveauSpecialite.getLibelle());
            existingNiveauSpecialite.setEtablissement(updatedNiveauSpecialite.getEtablissement());
            existingNiveauSpecialite.setDepartement(updatedNiveauSpecialite.getDepartement());


            return niveauSpecialiteRepository.save(existingNiveauSpecialite);
        } else {
            return null;
        }
    }

    public void deleteNiveauSpecialite(int id) {
        niveauSpecialiteRepository.deleteById(id);
    }
}
