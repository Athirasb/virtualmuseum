package com.JavaWizard.VirtualMuseum.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.JavaWizard.VirtualMuseum.entity.EventRegistration;

public interface EventRegistrationRepository extends JpaRepository<EventRegistration, Long> {
}
