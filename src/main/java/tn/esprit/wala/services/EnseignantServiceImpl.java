package tn.esprit.wala.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tn.esprit.wala.dtos.ContactDto;
import tn.esprit.wala.dtos.EnseignantDto;
import tn.esprit.wala.entities.Enseignant;
import tn.esprit.wala.repository.EnseignantRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class EnseignantServiceImpl {

    @Autowired
    private EnseignantRepository enseignantRepository;
    @Autowired
    private RestTemplate restTemplate;

    private static final String BASEURI = "http://localhost:9000/";
    public EnseignantDto getEnseignantById(Long id) {
        Enseignant enseignant = enseignantRepository.findById(id).orElse(null);
        return getEnseignantDto(enseignant);
    }


    private EnseignantDto getEnseignantDto(Enseignant enseignant) {
        EnseignantDto enseignantDto = new EnseignantDto();
        ContactDto contactDto = getContactFromContactMicroService(enseignant.getIdEnseignant());
        enseignantDto.setContact(contactDto);
        enseignantDto.setIdEnseignant(enseignant.getIdEnseignant());
        enseignantDto.setEmail(enseignant.getEmail());
        enseignantDto.setName(enseignant.getName());
        enseignantDto.setSpecialty(enseignant.getSpecialty());
        enseignantDto.setMeetingTitle(enseignant.getMeetingTitle());
        enseignantDto.setMeetingDate(enseignant.getMeetingDate());
        return enseignantDto;
    }




    private Enseignant getEnseignant(EnseignantDto enseignantDto) {
        Enseignant enseignant = new Enseignant();
        enseignant.setIdEnseignant(enseignantDto.getIdEnseignant());
        enseignant.setEmail(enseignantDto.getEmail());
        enseignant.setName(enseignantDto.getName());
        enseignant.setSpecialty(enseignantDto.getSpecialty());
        enseignant.setMeetingTitle(enseignantDto.getMeetingTitle());
        enseignant.setMeetingDate(enseignantDto.getMeetingDate());
        return enseignant;
    }

    public List<EnseignantDto> getAllEnseignants() {
        List<Enseignant> enseignants = enseignantRepository.findAll();
        List<EnseignantDto> enseignantDtoList = new ArrayList<>();
        enseignants.forEach(enseignant -> {
            enseignantDtoList.add(getEnseignantDto(enseignant));
        });
        return enseignantDtoList;
    }

    public Enseignant createEnseignant(EnseignantDto enseignantDto) {
        Enseignant enseignant = getEnseignant(enseignantDto);
        ContactDto contactDto = addContactFromContactMicroService(enseignantDto.getContact());
        enseignant.setContactId(contactDto.getIdContact().toString());
        return enseignantRepository.save(enseignant);
    }

    public Enseignant updateEnseignant(Long id, Enseignant updatedEnseignant) {
        Enseignant existingEnseignant = enseignantRepository.findById(id).orElse(null);
        if (existingEnseignant != null) {
            // Update fields as needed
            existingEnseignant.setEmail(updatedEnseignant.getEmail());
            existingEnseignant.setName(updatedEnseignant.getName());
            existingEnseignant.setSpecialty(updatedEnseignant.getSpecialty());
            existingEnseignant.setMeetingTitle(updatedEnseignant.getMeetingTitle());
            existingEnseignant.setMeetingDate(updatedEnseignant.getMeetingDate());

            return enseignantRepository.save(existingEnseignant);
        } else {
            return null; // Handle not found case
        }
    }

    public List<Enseignant> getEnseignantsByIdentifiant(String identifiant) {
        return enseignantRepository.findByIdentifiant(identifiant);
    }

    public void deleteEnseignant(Long id) {
        enseignantRepository.deleteById(id);
        deleteContactFromContactMicroService(id);
    }

    public ContactDto getContactFromContactMicroService(Long id) {
        String url = BASEURI + "entretien/contacts/find/" + id.toString();
        ResponseEntity<ContactDto> response = restTemplate.getForEntity(url, ContactDto.class);
        return response.getBody();
    }

    public void deleteContactFromContactMicroService(Long id) {
        String url = BASEURI + "entretien/contacts/delete/" + id.toString();
        restTemplate.delete(url);
    }

    public ContactDto addContactFromContactMicroService(ContactDto contactDto) {
        String url = BASEURI + "entretien/contacts/add";
        ResponseEntity<ContactDto> response = restTemplate.postForEntity(url, contactDto, ContactDto.class);
        return response.getBody();
    }




}

