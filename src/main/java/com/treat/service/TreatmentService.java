package com.treat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.treat.entity.Medicine;
import com.treat.entity.Patient;
import com.treat.entity.Treatment;
import com.treat.exceptions.ResourceNotFoundException;
import com.treat.repository.MedicineRepository;
import com.treat.repository.PatientRepository;
import com.treat.repository.TreatmentRepository;


// TreatmentService.java
@Service
public class TreatmentService {
    @Autowired
    private TreatmentRepository treatmentRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private PatientRepository patientRepository;

    public Treatment createTreatment(Treatment treatment) {
        // Validate medicine and patient existence
        Medicine medicine = medicineRepository.findById(treatment.getMedicine().getId()).orElse(null);
        Patient patient = patientRepository.findById(treatment.getPatient().getId()).orElse(null);

        if (medicine == null || patient == null) {
            throw new ResourceNotFoundException("Medicine or Patient not found!");
        }

        treatment.setMedicine(medicine);
        treatment.setPatient(patient);

        return treatmentRepository.save(treatment);
    }

    public Treatment getTreatmentById(Long id) {
        return treatmentRepository.findById(id).orElse(null);
    }
}
