package com.treat.controler;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.treat.entity.Treatment;
import com.treat.service.TreatmentService;

// TreatmentController.java
@RestController
@RequestMapping("/api/treatments")
public class TreatmentController {
    @Autowired
    private TreatmentService treatmentService;

    @PostMapping
    public ResponseEntity<Treatment> createTreatment(@Valid @RequestBody Treatment treatment) {
        Treatment createdTreatment = treatmentService.createTreatment(treatment);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTreatment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Treatment> getTreatment(@PathVariable Long id) {
        Treatment treatment = treatmentService.getTreatmentById(id);
        if (treatment != null) {
            return ResponseEntity.ok(treatment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}