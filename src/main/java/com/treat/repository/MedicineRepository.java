package com.treat.repository;
import java.util.List;

// MedicineRepository.java
import org.springframework.data.jpa.repository.JpaRepository;

import com.treat.entity.Medicine;


public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    List<Medicine> findByName(String name);
}
