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
    // test
    private static final String BASEUR_ADD_CONTACT = "http://192.168.182.134:5000/api/auth/register";
    private static final String BASEURI_GET_CONTACT = "http://192.168.182.134:5000/api/auth/contacts/";
    private static final String BASEURI_DELETE_CONTACT = "http://192.168.182.134:5000/api/auth/contacts/";
    public EtudiantDto getEtudiantById(Long id) {
        Etudiant etudiant = etudiantRepository.findById(id).orElse(null);
        return getEtudiantDto(etudiant);
    }

    private EtudiantDto getEtudiantDto (Etudiant etudiant) {
        EtudiantDto etudiantDto = new EtudiantDto();
        ContactDto contactDto = getContactFromContactMicroService(etudiant.getContactId());
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
        etudiant.setContactId(contactDto.getIdContact());
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
        Etudiant etudiant = etudiantRepository.findById(id).orElse(null);
        etudiantRepository.deleteById(id);
        deleteContactFromContactMicroService(etudiant.getContactId());
    }

    public List<Etudiant> getEtudiantsByIdentifiant(String identifiant) {
        return etudiantRepository.findByIdentifiant(identifiant);
    }

    public ContactDto getContactFromContactMicroService(String id) {
        String url = BASEURI_GET_CONTACT + id;
        ResponseEntity<ContactDto> response = restTemplate.getForEntity(url, ContactDto.class);
        return response.getBody();
    }

    void deleteContactFromContactMicroService(String id) {
        String url =  BASEURI_DELETE_CONTACT +  id ;
        restTemplate.delete(url);
    }

    public ContactDto addContactFromContactMicroService(ContactDto contactDto) {
        String url = BASEUR_ADD_CONTACT;
        ResponseEntity<ContactDto> response = restTemplate.postForEntity(url,contactDto, ContactDto.class);
        return response.getBody();
    }

}
