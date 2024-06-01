package com.treat.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// TreatmentControllerTest.java
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.treat.controler.TreatmentController;
import com.treat.entity.Medicine;
import com.treat.entity.Patient;
import com.treat.entity.Treatment;
import com.treat.service.TreatmentService;

@WebMvcTest(TreatmentController.class)
public class TreatmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TreatmentService treatmentService;

    @Test
    public void testCreateTreatment() throws Exception {
        // Given
        Treatment treatment = new Treatment();
        treatment.setAdvice("Take medicine with water");
        Medicine medicine = new Medicine();
        medicine.setId(1l);
        Patient patient = new Patient();
        patient.setId(1l);
        treatment.setMedicine(medicine);
        treatment.setPatient(patient);
        // Mock service behavior
        when(treatmentService.createTreatment(treatment)).thenReturn(treatment);

        // When
        mockMvc.perform(post("/api/treatments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"advice\":\"Take medicine with water\", \"medicine\": {\"id\":1}, \"patient\": { \"id\":1}}"))
                .andExpect(status().isCreated());

        // Then
        verify(treatmentService, times(1)).createTreatment(any(Treatment.class));
    }

    @Test
    public void testGetTreatment() throws Exception {
        // Given
        Long id = 1L;
        Treatment treatment = new Treatment();
        treatment.setId(id);
        Medicine medicine = new Medicine();
        medicine.setId(1l);
        Patient patient = new Patient();
        patient.setId(1l);
        treatment.setMedicine(medicine);
        treatment.setPatient(patient);
        // Mock service behavior
        when(treatmentService.getTreatmentById(id)).thenReturn(treatment);

        // When
        mockMvc.perform(get("/api/treatments/{id}", id))
                .andExpect(status().isOk());

        // Then
        verify(treatmentService, times(1)).getTreatmentById(id);
    }
}
