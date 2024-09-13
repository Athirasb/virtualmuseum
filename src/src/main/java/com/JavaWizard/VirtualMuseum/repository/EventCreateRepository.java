package com.JavaWizard.VirtualMuseum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.JavaWizard.VirtualMuseum.entity.EventCreate;

public interface EventCreateRepository extends JpaRepository<EventCreate, Long> {
}
