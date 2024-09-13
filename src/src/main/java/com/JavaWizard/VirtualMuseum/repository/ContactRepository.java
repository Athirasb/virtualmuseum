package com.JavaWizard.VirtualMuseum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.JavaWizard.VirtualMuseum.entity.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    // You can define custom query methods here if needed
}
