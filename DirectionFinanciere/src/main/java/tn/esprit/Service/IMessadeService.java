package tn.esprit.Service;

import tn.esprit.Entity.Contact;
import tn.esprit.Entity.Message;

import java.util.List;

public interface IMessadeService {

    Message addMessage(Message message);

    List<Message> getMessagesBySender(String senderId);

    List<Message> getMessagesByReceiver(String receiverId);

    List<Contact> findContactsByContactId(String contactId);

   // List<Message> getChatBetweenContacts(String contactId1, String contactId2);
}
