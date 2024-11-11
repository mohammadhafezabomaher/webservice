package com.example.pidev.services;
import com.example.pidev.dtos.ContactDto;
import com.example.pidev.entities.Fournisseur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.pidev.repositories.IFournisseurRepository;
import com.example.pidev.dtos.FournisseurDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class FournisseurService {
    @Autowired
    private IFournisseurRepository fournisseurRepository;
    @Autowired
    private RestTemplate restTemplate;

    private static final String BASEURI = "http://localhost:9000/";

    public FournisseurDto getFournisseurById(Long id) {
        Fournisseur fournisseur = fournisseurRepository.findById(id).orElse(null);
        return getFournisseurDto(fournisseur);
    }

    public List<FournisseurDto> getFournisseursByType(String type) {
        List<Fournisseur> fournisseurs = fournisseurRepository.findByTypeFournisseur(type);
        List<FournisseurDto> fournisseurDtoList = new ArrayList<>();
        fournisseurs.forEach(fournisseur -> {
            fournisseurDtoList.add(getFournisseurDto(fournisseur));
        });
        return fournisseurDtoList;
    }

    private FournisseurDto getFournisseurDto(Fournisseur fournisseur) {
        FournisseurDto fournisseurDto = new FournisseurDto();
        ContactDto contactDto = getContactFromContactMicroService(fournisseur.getContactId());
        fournisseurDto.setContact(contactDto);
        fournisseurDto.setIdFournisseur(fournisseur.getIdFournisseur());
        fournisseurDto.setTypeFournisseur(fournisseur.getTypeFournisseur());
        fournisseurDto.setProduits(fournisseur.getProduits());
        return fournisseurDto;
    }

    private Fournisseur getFournisseur(FournisseurDto fournisseurDto) {
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setIdFournisseur(fournisseurDto.getIdFournisseur());
        fournisseur.setTypeFournisseur(fournisseurDto.getTypeFournisseur());
        fournisseur.setContactId(fournisseurDto.getContact().getIdContact().toString());
        fournisseur.setProduits(fournisseurDto.getProduits());
        return fournisseur;
    }

    public List<FournisseurDto> getAllFournisseurs() {
        List<Fournisseur> fournisseurs = fournisseurRepository.findAll();
        List<FournisseurDto> fournisseurDtoList = new ArrayList<>();
        fournisseurs.forEach(fournisseur -> {
            fournisseurDtoList.add(getFournisseurDto(fournisseur));
        });
        return fournisseurDtoList;
    }

    public Fournisseur createFournisseur(FournisseurDto fournisseurDto) {
        Fournisseur fournisseur = getFournisseur(fournisseurDto);

        if (fournisseurDto.getContact() != null) {
            ContactDto contactDto = addContactFromContactMicroService(fournisseurDto.getContact());
            fournisseur.setContactId(contactDto.getIdContact().toString());
        } else {
            fournisseur.setContactId(null);
        }

        return fournisseurRepository.save(fournisseur);
    }


    public Fournisseur updateFournisseur(Long id, Fournisseur updatedFournisseur) {
        Fournisseur existingFournisseur = fournisseurRepository.findById(id).orElse(null);
        if (existingFournisseur != null) {
            existingFournisseur.setTypeFournisseur(updatedFournisseur.getTypeFournisseur());
            existingFournisseur.setProduits(updatedFournisseur.getProduits());
            return fournisseurRepository.save(existingFournisseur);
        } else {
            return null; // Handle not found case
        }
    }

    public void deleteFournisseur(Long id) {
        fournisseurRepository.deleteById(id);
        deleteContactFromContactMicroService(id);
    }

    public ContactDto getContactFromContactMicroService(String contactId) {
        String url = BASEURI + "examen/contacts/find/" + contactId;
        ResponseEntity<ContactDto> response = restTemplate.getForEntity(url, ContactDto.class);
        return response.getBody();
    }

    void deleteContactFromContactMicroService(Long id) {
        String url = BASEURI + "examen/contacts/delete/" + id.toString();
        restTemplate.delete(url);
    }

    public ContactDto addContactFromContactMicroService(ContactDto contactDto) {
        String url = BASEURI + "examen/contacts/add";
        ResponseEntity<ContactDto> response = restTemplate.postForEntity(url, contactDto, ContactDto.class);
        return response.getBody();
    }
}
