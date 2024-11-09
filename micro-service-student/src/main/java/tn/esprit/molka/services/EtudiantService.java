package tn.esprit.molka.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tn.esprit.molka.dtos.ContactDto;
import tn.esprit.molka.entities.Etudiant;
import tn.esprit.molka.dtos.EtudiantDto;
import tn.esprit.molka.repository.EtudiantRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class EtudiantService {
    @Autowired
    private EtudiantRepository etudiantRepository;
    @Autowired
    private RestTemplate restTemplate;

    private static final String BASEURI = "http://localhost:9000/";
    public EtudiantDto getEtudiantById(Long id) {
        Etudiant etudiant = etudiantRepository.findById(id).orElse(null);
        return getEtudiantDto(etudiant);
    }

    private EtudiantDto getEtudiantDto (Etudiant etudiant) {
        EtudiantDto etudiantDto = new EtudiantDto();
        ContactDto contactDto = getContactFromContactMicroService(etudiant.getIdEtudiant());
        etudiantDto.setContact(contactDto);
        etudiantDto.setIdEtudiant(etudiant.getIdEtudiant());
        etudiantDto.setFaculte(etudiant.getFaculte());
        etudiantDto.setNiveauSpecialite(etudiant.getNiveauSpecialite());
        etudiantDto.setIdentifiant(etudiant.getIdentifiant());
        etudiantDto.setTypeEtudiant(etudiant.getTypeEtudiant());
        return etudiantDto;
    }
    private Etudiant getEtudiant (EtudiantDto etudiant) {
        Etudiant etudiantDto = new Etudiant();
        etudiantDto.setIdEtudiant(etudiant.getIdEtudiant());
        etudiantDto.setFaculte(etudiant.getFaculte());
        etudiantDto.setNiveauSpecialite(etudiant.getNiveauSpecialite());
        etudiantDto.setIdentifiant(etudiant.getIdentifiant());
        etudiantDto.setTypeEtudiant(etudiant.getTypeEtudiant());
        return etudiantDto;
    }

    public List<EtudiantDto> getAllEtudiants() {
        List<Etudiant> etudiants = etudiantRepository.findAll();
        List<EtudiantDto> etudiantDtoList = new ArrayList<>();
        etudiants.forEach(etudiant -> {
            etudiantDtoList.add(getEtudiantDto(etudiant));
        });
        return etudiantDtoList;
    }

    public Etudiant createEtudiant(EtudiantDto etudiantDto) {
        Etudiant etudiant = getEtudiant(etudiantDto);
        ContactDto contactDto = addContactFromContactMicroService(etudiantDto.getContact());
        etudiant.setContactId(contactDto.getIdContact().toString());
        return etudiantRepository.save(etudiant);
    }

    public Etudiant updateEtudiant(Long id, Etudiant updatedEtudiant) {
        Etudiant existingEtudiant = etudiantRepository.findById(id).orElse(null);
        if (existingEtudiant != null) {
            // Update fields as needed
            existingEtudiant.setFaculte(updatedEtudiant.getFaculte());
            existingEtudiant.setIdentifiant(updatedEtudiant.getIdentifiant());
            existingEtudiant.setTypeEtudiant(updatedEtudiant.getTypeEtudiant());
            //existingEtudiant.setContact(updatedEtudiant.getContact());
            existingEtudiant.setNiveauSpecialite(updatedEtudiant.getNiveauSpecialite());

            return etudiantRepository.save(existingEtudiant);
        } else {
            return null; // Handle not found case
        }
    }

    public void deleteEtudiant(Long id) {
        etudiantRepository.deleteById(id);
        deleteContactFromContactMicroService(id);
    }

    public List<Etudiant> getEtudiantsByIdentifiant(String identifiant) {
        return etudiantRepository.findByIdentifiant(identifiant);
    }

    public ContactDto getContactFromContactMicroService(Long id) {
        String url = BASEURI + "examen/contacts/find/"+ id.toString();
        ResponseEntity<ContactDto> response = restTemplate.getForEntity(url, ContactDto.class);
        return response.getBody();
    }

    void deleteContactFromContactMicroService(Long id) {
        String url = BASEURI + "examen/contacts/delete/"+ id.toString();
        restTemplate.delete(url);
    }

    public ContactDto addContactFromContactMicroService(ContactDto contactDto) {
        String url = BASEURI + "examen/contacts/add";
        ResponseEntity<ContactDto> response = restTemplate.postForEntity(url,contactDto, ContactDto.class);
        return response.getBody();
    }

}
