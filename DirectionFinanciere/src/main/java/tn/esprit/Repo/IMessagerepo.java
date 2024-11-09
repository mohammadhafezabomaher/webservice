package tn.esprit.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.Entity.Message;

import java.util.List;

@Repository
public interface IMessagerepo extends JpaRepository<Message, Long> {
    List<Message> findBySenderId(String senderId);
    List<Message> findByReceiverId(String receiverId);
   // List<Message> findChatBetweenContacts(String contactId1, String contactId2);
}
