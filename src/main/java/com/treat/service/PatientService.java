package com.treat.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestBody;

import com.treat.entity.Patient;


public interface PatientService {
	public List<Patient>getPatient();
	public Optional<Patient> getPatientById(@RequestBody Map<String,String> requestParams);

}
