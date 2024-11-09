package tn.esprit.Service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tn.esprit.Entity.Contact;
import tn.esprit.Entity.Message;
import tn.esprit.Repo.IMessagerepo;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class Messageimpl implements IMessadeService {

    private final ContactService contactService;
    private final IMessagerepo messageRepo;
    private final RestTemplate restTemplate;

    // URL of the Node.js microservice (adjust as per your setup)
    private final String NODE_API_URL = "http://localhost:3000/api/contact/";

    @Override
    public Message addMessage(Message message) {
        // Use RestTemplate to verify if the sender and receiver exist in Node.js microservice
        String senderUrl = NODE_API_URL + message.getSenderId();
        String receiverUrl = NODE_API_URL + message.getReceiverId();

        // Make HTTP GET requests to check if contacts exist
        try {
            restTemplate.getForObject(senderUrl, String.class); // Validate sender exists
            restTemplate.getForObject(receiverUrl, String.class); // Validate receiver exists
        } catch (Exception e) {
            // Handle the case when sender or receiver does not exist
            throw new RuntimeException("Sender or receiver not found in Node.js service");
        }

        // If both contacts exist, save the message
        return messageRepo.save(message);
    }

    @Override
    public List<Message> getMessagesBySender(String senderId) {
        return messageRepo.findBySenderId(senderId);
    }

    @Override
    public List<Message> getMessagesByReceiver(String receiverId) {
        return messageRepo.findByReceiverId(receiverId);
    }

    @Override
    public List<Contact> findContactsByContactId(String contactId) {
       List<Message> listM=messageRepo.findBySenderId(contactId);

        List<Contact> res = new ArrayList<>();

        listM.forEach(message -> {
            String receiverId = message.getReceiverId(); // Assuming there's a getReceiverId() method
            Contact contact = contactService.getContactById(receiverId); // Fetch the contact by ID
            if (contact != null) { // Ensure the contact exists
                res.add(contact);
            }
        });

        return res; // Return the populated list of contacts
    }




   // @Override
    //public List<Message> getChatBetweenContacts(String contactId1, String contactId2) {
     //   return messageRepo.findChatBetweenContacts(contactId1, contactId2);
    //}
}
