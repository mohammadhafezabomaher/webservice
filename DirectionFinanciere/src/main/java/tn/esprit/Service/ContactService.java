package tn.esprit.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import tn.esprit.Entity.Contact;

@Service
public class ContactService {

    private final RestTemplate restTemplate;

    @Autowired
    public ContactService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Contact getContactById(String id) {
        String url = "http://localhost:5000/api/auth/contacts/" + id;  // Replace with your actual URL

        // Perform a GET request
        ResponseEntity<Contact> response = restTemplate.getForEntity(url, Contact.class);

        return response.getBody(); // Returns the contact data if successful
    }
}
