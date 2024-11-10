package tn.esprit.Service;

import tn.esprit.Entity.Contact;
import tn.esprit.Entity.Message;
import tn.esprit.Entity.MessageDTO;

import java.util.List;

public interface IMessadeService {

    MessageDTO addMessage(MessageDTO message);

    List<MessageDTO> getMessagesBySender(String senderId);

    List<MessageDTO> getMessagesByReceiver(String receiverId);

    List<Contact> findContactsByContactId(String contactId);

   // List<Message> getChatBetweenContacts(String contactId1, String contactId2);
}
