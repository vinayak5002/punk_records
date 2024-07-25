package com.infy.service;


import java.time.LocalDate;
import java.util.List;

import com.infy.exception.HealthcareException;
import com.infy.model.*;

public interface HealthcareService{
	public List<Doctor> getAllDoctors();
	
	public List<Doctor> searchDoctorsBySpecialization(String specialization);

	public Status bookAppointment(Patient patient, int doctorId, LocalDate date, String time) throws HealthcareException;
}
