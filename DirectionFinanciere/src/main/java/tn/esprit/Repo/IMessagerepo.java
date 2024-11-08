package tn.esprit.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.Entity.Message;

import java.util.List;

@Repository
public interface IMessagerepo extends JpaRepository<Message, Long> {
    List<Message> findBySenderId(Long senderId);
    List<Message> findByReceiverId(Long receiverId);
    List<Message> findChatBetweenContacts(Long contactId1, Long contactId2);
}
