package com.example.pidev.controllers;

import com.example.pidev.entities.Contact;
import com.example.pidev.services.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactController {
    @Autowired
    IContactService contactService ;

    @PostMapping("/addContact")
    public Contact addContact(@RequestBody Contact c) {
        return contactService.addContact(c) ;
    }

    @GetMapping("/retrieveContact/{id}")
    public Contact retrieveFournisseur(@PathVariable Long id) {
        return contactService.retrieveContact(id);
    }
    @GetMapping("/retrieveAllContacts")
    public List<Contact> retrieveAllContacts() {
        return contactService.retrieveAllContacts();
    }
    @PutMapping("/modifyContact")
    public Contact modifyContact(@RequestBody Contact contact) {
        return contactService.modifyContact(contact);
    }
    @DeleteMapping("/removeContact/{id}")
    public void removeContact(@PathVariable Long id) {
        contactService.removeContact(id);
    }

}
