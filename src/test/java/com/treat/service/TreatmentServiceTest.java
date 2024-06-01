package com.treat.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.treat.entity.Medicine;
import com.treat.entity.Patient;
import com.treat.entity.Treatment;
import com.treat.exceptions.ResourceNotFoundException;
import com.treat.repository.MedicineRepository;
import com.treat.repository.PatientRepository;
import com.treat.repository.TreatmentRepository;

public class TreatmentServiceTest {

    @Mock
    private TreatmentRepository treatmentRepository;

    @Mock
    private MedicineRepository medicineRepository;

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private TreatmentService treatmentService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateTreatment_Success() {
        // Given
        Treatment treatment = new Treatment();
        treatment.setAdvice("Take medicine with water");
        Medicine medicine = new Medicine();
        medicine.setId(1l);
        Patient patient = new Patient();
        patient.setId(1l);
        treatment.setMedicine(medicine);
        treatment.setPatient(patient);
        
        // Mock repository behavior
        when(medicineRepository.findById(anyLong())).thenReturn(Optional.of(new Medicine()));
        when(patientRepository.findById(anyLong())).thenReturn(Optional.of(new Patient()));
        when(treatmentRepository.save(any(Treatment.class))).thenReturn(treatment);

        // When
        Treatment createdTreatment = treatmentService.createTreatment(treatment);

        // Then
        assertNotNull(createdTreatment);
        assertEquals("Take medicine with water", createdTreatment.getAdvice());
        verify(medicineRepository, times(1)).findById(anyLong());
        verify(patientRepository, times(1)).findById(anyLong());
        verify(treatmentRepository, times(1)).save(any(Treatment.class));
    }

    @Test
    public void testCreateTreatment_MedicineNotFound() {
        // Given
        Treatment treatment = new Treatment();
        Medicine medicine = new Medicine();
        medicine.setId(1l);
        Patient patient = new Patient();
        patient.setId(1l);
        treatment.setMedicine(medicine);
        treatment.setPatient(patient);

        // Mock repository behavior
        when(medicineRepository.findById(anyLong())).thenReturn(Optional.empty());

        // When
        assertThrows(ResourceNotFoundException.class, () -> {
            treatmentService.createTreatment(treatment);
        });

        // Then
        verify(treatmentRepository, never()).save(any(Treatment.class));
    }

    @Test
    public void testCreateTreatment_PatientNotFound() {
        // Given
        Treatment treatment = new Treatment();
        Medicine medicine = new Medicine();
        medicine.setId(1l);
        Patient patient = new Patient();
        patient.setId(1l);
        treatment.setMedicine(medicine);
        treatment.setPatient(patient);

        // Mock repository behavior
        when(medicineRepository.findById(anyLong())).thenReturn(Optional.of(new Medicine()));
        when(patientRepository.findById(anyLong())).thenReturn(Optional.empty());

        // When
        assertThrows(ResourceNotFoundException.class, () -> {
            treatmentService.createTreatment(treatment);
        });

        // Then
        verify(treatmentRepository, never()).save(any(Treatment.class));
    }
}
