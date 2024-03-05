package com.example.pidev.services;

import com.example.pidev.entities.Contact;
import com.example.pidev.repositories.IContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements IContactService {
    @Autowired
    IContactRepository IContactRepository;
    @Override
    public List<Contact> retrieveAllContacts() {
        return  IContactRepository.findAll() ;
    }

    @Override
    public Contact retrieveContact(Long idContact) {
        return IContactRepository.findById(idContact).get();
    }

    @Override
    public Contact addContact(Contact c) {
        return IContactRepository.save(c);
    }

    @Override
    public void removeContact(Long idContact) {
        IContactRepository.deleteById(idContact);
    }

    @Override
    public Contact modifyContact(Contact contact) {
        return IContactRepository.save(contact);
    }
}
