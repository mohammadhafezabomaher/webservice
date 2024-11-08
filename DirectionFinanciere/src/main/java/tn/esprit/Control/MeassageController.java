package tn.esprit.Control;

import tn.esprit.Entity.Message;
import tn.esprit.Service.IMessadeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Message")
public class MeassageController {

    private final IMessadeService service;

    @PostMapping("/Send")
    public Message sendMessage(@RequestBody Message message) {
        return service.addMessage(message);
    }

    @GetMapping("/getbysender/{senderId}")
    public List<Message> getMessagesBySender(@PathVariable Long senderId) {
        return service.getMessagesBySender(senderId);
    }

    @GetMapping("/getbyreceiver/{receiverId}")
    public List<Message> getMessagesByReceiver(@PathVariable Long receiverId) {
        return service.getMessagesByReceiver(receiverId);
    }

    @GetMapping("/getcontacts/{contactId}")
    public List<Long> getContacts(@PathVariable Long contactId) {
        return service.findContactsByContactId(contactId);
    }

    @GetMapping("/getchat/{contactId1}/{contactId2}")
    public List<Message> getChat(@PathVariable Long contactId1, @PathVariable Long contactId2) {
        return service.getChatBetweenContacts(contactId1, contactId2);
    }
}
