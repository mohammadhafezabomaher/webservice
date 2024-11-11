package com.example.admin.services;

import com.example.admin.dto.AdminDTO;
import com.example.admin.dto.ContactDTO;
import com.example.admin.entities.Admin;
import com.example.admin.repositories.IAdminRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AdminService implements IAdminService {
    private final IAdminRepository adminRepository;
    private final RestTemplate restTemplate;
    private final String baseUri="http://192.168.1.89:5000/";
    public AdminService(IAdminRepository adminRepository, RestTemplate restTemplate) {
        this.adminRepository = adminRepository;
        this.restTemplate = restTemplate;
    }


    @Override
    public Admin addAdmin(AdminDTO a) {
        try {
            Admin admin = new Admin();
            admin.setDateDebut(a.getDateDebut());

            String contactDTO = addContactFromContactMicroService(a.getContact());

            if (contactDTO != null) {
                admin.setIdContact(contactDTO);
            } else {
                throw new IllegalArgumentException("Invalid Contact data provided");
            }

            return adminRepository.save(admin);
        } catch (RestClientException e) {
            log.error("Error while adding admin: ", e);
            throw new RuntimeException("Failed to add admin due to contact service error", e);
        }
    }

    @Override
    public AdminDTO getAdminById(Long id) {
        Admin a = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found with id: " + id));
        return getAdminDto(a);
    }

    private AdminDTO getAdminDto(Admin a) {
        try {
            AdminDTO adminDTO = new AdminDTO();
            ContactDTO contactDto = getContactFromContactMicroService(a.getIdContact());
            adminDTO.setIdAdmin(a.getIdAdmin());
            adminDTO.setContact(contactDto);
            adminDTO.setDateDebut(a.getDateDebut());
            return adminDTO;
        } catch (RestClientException e) {
            log.error("Error while getting contact details for admin {}: ", a.getIdAdmin(), e);
            throw new RuntimeException("Failed to get contact details from contact service", e);
        }
    }

    private Admin getAdmin(AdminDTO a) {
        Admin adminDTO = new Admin();
        adminDTO.setIdAdmin(a.getIdAdmin());
        adminDTO.setDateDebut(a.getDateDebut());
        return adminDTO;
    }

    @Override
    public List<AdminDTO> getAllAdmin() {
        List<Admin> admins = adminRepository.findAll();
        List<AdminDTO> adminDTOS = new ArrayList<>();
        admins.forEach(a -> {
            try {
                adminDTOS.add(getAdminDto(a));
            } catch (Exception e) {
                log.error("Error while getting admin details for id {}: ", a.getIdAdmin(), e);
            }
        });
        return adminDTOS;
    }

    void deleteContactFromContactMicroService(String id) {
        try {
            String url = baseUri + "api/auth/contacts/" + id;
            restTemplate.delete(url);
        } catch (RestClientException e) {
            log.error("Error while deleting contact {}: ", id, e);
            throw new RuntimeException("Failed to delete contact from contact service", e);
        }
    }

    @Override
    public Admin updateAdmin(Admin a) {
        return adminRepository.save(a);
    }

    @Override
    public void deleteAdmin(Long id) {
        try {
            Admin a = adminRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Admin not found with id: " + id));
            deleteContactFromContactMicroService(a.getIdContact());
            adminRepository.deleteById(id);
        } catch (RestClientException e) {
            log.error("Error while deleting admin {}: ", id, e);
            throw new RuntimeException("Failed to delete admin due to contact service error", e);
        }
    }

    public String addContactFromContactMicroService(ContactDTO contactDto) {
        try {
            String url = baseUri + "api/auth/register";
            ResponseEntity<ContactDTO> response = restTemplate.postForEntity(url, contactDto, ContactDTO.class);
            if (response.getBody() == null) {
                throw new RuntimeException("No response received from contact service");
            }
            return response.getBody().getIdContact();
        } catch (RestClientException e) {
            log.error("Error while adding contact: ", e);
            throw new RuntimeException("Failed to add contact to contact service", e);
        }
    }

    public ContactDTO getContactFromContactMicroService(String id) {
        try {
            String url = baseUri + "api/auth/contacts/" + id;
            ResponseEntity<ContactDTO> response = restTemplate.getForEntity(url, ContactDTO.class);
            if (response.getBody() == null) {
                throw new RuntimeException("No contact found with id: " + id);
            }
            return response.getBody();
        } catch (RestClientException e) {
            log.error("Error while getting contact {}: ", id, e);
            throw new RuntimeException("Failed to get contact from contact service", e);
        }
    }
}