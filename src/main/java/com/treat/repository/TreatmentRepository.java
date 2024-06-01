package com.treat.repository;
// TreatmentRepository.java
import org.springframework.data.jpa.repository.JpaRepository;

import com.treat.entity.Treatment;


public interface TreatmentRepository extends JpaRepository<Treatment, Long> {
    // You can add custom query methods if needed
}
