package com.example.pidev.services;

import com.example.pidev.entities.Contact;
import com.example.pidev.entities.Fournisseur;

import java.util.List;

public interface IContactService {
    public List<Contact> retrieveAllContacts();
    public Contact retrieveContact(Long idContact);
    public Contact addContact(Contact f);
    public void removeContact(Long idContact);
    public Contact modifyContact(Contact Contact);
}
