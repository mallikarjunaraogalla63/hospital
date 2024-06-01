package com.treat.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import com.treat.entity.Patient;
import com.treat.repository.PatientRepository;


@Service
@RequestScope
public class PatientServiceImp implements PatientService {

	@Autowired
	PatientRepository patientRepository;
	@Override
	public List<Patient> getPatient() {
		
		return patientRepository.findAll();
	}

	@Override
	public Optional<Patient> getPatientById(Map<String, String> requestParams) {
		
		 return patientRepository.findById(Long.parseLong(requestParams.get("PatientID")));
	}

}
