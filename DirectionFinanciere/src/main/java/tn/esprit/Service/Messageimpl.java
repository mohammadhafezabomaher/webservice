package tn.esprit.Service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tn.esprit.Entity.Contact;
import tn.esprit.Entity.Message;
import tn.esprit.Entity.MessageDTO;
import tn.esprit.Repo.IMessagerepo;

import java.util.ArrayList;
import java.util.Comparator;
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
    public MessageDTO addMessage(MessageDTO message) {
        // Use RestTemplate to verify if the sender and receiver exist in Node.js microservice
        Message m = new Message();
       m.setMessage(message.getMessage());
       m.setDateSent(message.getDateSent());
       m.setSenderId(message.getSenderContact().get_id());
       m.setReceiverId(message.getReceiverContact().get_id());



       Message res= messageRepo.save(m);

        return  message;
    }

    @Override
    public List<MessageDTO> getMessagesBySender(String senderId) {
        List<MessageDTO> res=new ArrayList<>();
        List<Message> preb= messageRepo.findBySenderId(senderId);
        preb.forEach(message -> {
            Contact rec = contactService.getContactById(message.getReceiverId());
            Contact sen = contactService.getContactById(message.getSenderId());
            MessageDTO  prepare  = new MessageDTO();
            prepare.setId(message.getId());
            prepare.setMessage(message.getMessage());
            prepare.setDateSent(message.getDateSent());
            prepare.setReceiverContact(rec);
            prepare.setSenderContact(sen);


            res.add(prepare);

        });
        res.sort(Comparator.comparing(MessageDTO::getDateSent).reversed());

        return res;
    }

    @Override
    public List<MessageDTO> getMessagesByReceiver(String receiverId) {
        List<MessageDTO> res=new ArrayList<>();
        List<Message> preb= messageRepo.findByReceiverId(receiverId);
        preb.forEach(message -> {
            Contact rec = contactService.getContactById(message.getReceiverId());
            Contact sen = contactService.getContactById(message.getSenderId());
            MessageDTO  prepare  = new MessageDTO();
            prepare.setId(message.getId());
            prepare.setMessage(message.getMessage());
            prepare.setDateSent(message.getDateSent());
            prepare.setReceiverContact(rec);
            prepare.setSenderContact(sen);


            res.add(prepare);

        });
        res.sort(Comparator.comparing(MessageDTO::getDateSent).reversed());

        return res;
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
