package tn.esprit.Service;

import tn.esprit.Entity.Message;

import java.util.List;

public interface IMessadeService {

    Message addMessage(Message message);

    List<Message> getMessagesBySender(Long senderId);

    List<Message> getMessagesByReceiver(Long receiverId);

    List<Long> findContactsByContactId(Long contactId);

    List<Message> getChatBetweenContacts(Long contactId1, Long contactId2);
}
