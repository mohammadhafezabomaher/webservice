package com.example.pidev.services;

import com.example.pidev.dtos.OffreDto;
import com.example.pidev.entities.Offre;
import com.example.pidev.repositories.IOffreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OffreServiceImpl{

    @Autowired
    IOffreRepository iOffreRepository;

    // Convertir l'entité Offre en DTO OffreDto
    private OffreDto getOffreDto(Offre offre) {
        OffreDto offreDto = new OffreDto();
        offreDto.setIdOffre(offre.getIdOffre());
        offreDto.setTitre(offre.getTitre());
        offreDto.setDescription(offre.getDescription());
        offreDto.setDateDebut(offre.getDateDebut());
        offreDto.setDateFin(offre.getDateFin());
        return offreDto;
    }

    // Convertir un DTO OffreDto en entité Offre
    private Offre getOffre(OffreDto offreDto) {
        Offre offre = new Offre();
        offre.setIdOffre(offreDto.getIdOffre());
        offre.setTitre(offreDto.getTitre());
        offre.setDescription(offreDto.getDescription());
        offre.setDateDebut(offreDto.getDateDebut());
        offre.setDateFin(offreDto.getDateFin());
        return offre;
    }




    public List<OffreDto> findByTitreContainingIgnoreCase(String titre) {
        List<Offre> offres = iOffreRepository.findByTitreContainingIgnoreCase(titre);
        List<OffreDto> offreDtos = new ArrayList<>();
        for (Offre offre : offres) {
            offreDtos.add(getOffreDto(offre));
        }
        return offreDtos;
    }


    public List<Offre> searchByTitre(String titre) {
        return iOffreRepository.findByTitreContainingIgnoreCase(titre);
    }


    public OffreDto findById(Long id) {
        Offre offre = iOffreRepository.findById(id).orElse(null);
        return offre != null ? getOffreDto(offre) : null;
    }


    public List<OffreDto> retrieveAllOffres() {
        List<Offre> offres = iOffreRepository.findAll();
        List<OffreDto> offreDtos = new ArrayList<>();
        for (Offre offre : offres) {
            offreDtos.add(getOffreDto(offre));
        }
        return offreDtos;
    }


    public OffreDto retrieveOffre(Long idOffre) {
        Offre offre = iOffreRepository.findById(idOffre).orElse(null);
        return offre != null ? getOffreDto(offre) : null;
    }


    public Offre addOffre(OffreDto offreDto) {
        Offre offre = getOffre(offreDto);
        return iOffreRepository.save(offre);
    }


    public void removeOffre(Long idOffre) {
        iOffreRepository.deleteById(idOffre);
    }


    public OffreDto ModifyOffre(OffreDto offreDto) {
        Offre offre = getOffre(offreDto);
        Offre modifiedOffre = iOffreRepository.save(offre);
        return getOffreDto(modifiedOffre);
    }
}
