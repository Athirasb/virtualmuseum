package com.JavaWizard.VirtualMuseum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.JavaWizard.VirtualMuseum.entity.Contact;
import com.JavaWizard.VirtualMuseum.repository.ContactRepository;
import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public Contact save(Contact contact) {
        return contactRepository.save(contact);
    }

    public Contact getContactById(Long id) {
        return contactRepository.findById(id).orElse(null);
    }

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }
}
