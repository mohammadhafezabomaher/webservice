package tn.esprit.Control;

import tn.esprit.Entity.Contact;
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
    public List<Message> getMessagesBySender(@PathVariable String senderId) {
        return service.getMessagesBySender(senderId);
    }

    @GetMapping("/getbyreceiver/{receiverId}")
    public List<Message> getMessagesByReceiver(@PathVariable String receiverId) {
        return service.getMessagesByReceiver(receiverId);
    }

    @GetMapping("/getcontacts/{contactId}")
    public List<Contact> getContacts(@PathVariable String contactId) {
        return service.findContactsByContactId(contactId);
    }


    //@GetMapping("/getchat/{contactId1}/{contactId2}")
    //public List<Message> getChat(@PathVariable String contactId1, @PathVariable String contactId2) {
      //  return service.getChatBetweenContacts(contactId1, contactId2);
   // }
}
