package com.infy.service;

import java.time.LocalDate;

import java.util.List;
import java.util.stream.Collectors;

import com.infy.dao.HealthcareDAOImpl;
import com.infy.exception.HealthcareException;
import com.infy.model.Appointment;
import com.infy.model.Doctor;
import com.infy.model.Patient;
import com.infy.model.Status;
import com.infy.validator.Validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class HealthcareServiceImpl implements HealthcareService {
	
    protected static final Logger logger = LogManager.getLogger(HealthcareServiceImpl.class);
	
	HealthcareDAOImpl dao = new HealthcareDAOImpl();
	Validator validator = new Validator();
	
	static int appNum = 1001;
	
	@Override
	public List<Doctor> getAllDoctors() {
		List<Doctor> doctors = dao.getAllDoctors();
		
		return doctors;
	}

	@Override
	public List<Doctor> searchDoctorsBySpecialization(String specialization) {
		List<Doctor> doctors = dao.getAllDoctors();
		
		doctors = doctors.stream()
				.filter(e -> e.getSpecialization().equals(specialization))
				.collect(Collectors.toList());
		
		return doctors;
	}

	@Override
	public Status bookAppointment(Patient patient, int doctorId, LocalDate date, String time) throws HealthcareException {
		List<Doctor> doctors = dao.getAllDoctors();
		
		if (LocalDate.now().isAfter(date) || LocalDate.now().equals(date)) {
			logger.error("Invalid date: Entered date is of past");
			throw new HealthcareException("Can only book an appointment for a future date");
		}
		
		doctors = doctors.stream()
				.filter(e -> e.getDoctorId() == doctorId)
				.collect(Collectors.toList());
		
		if (doctors.isEmpty()) {
			logger.error("Invalid doctor Id: Tried to book appointment of invalid doctor");
			throw new HealthcareException("Doctor with doctorId " + doctorId + " not found");
		}
		
		Doctor doctor = doctors.get(0);
	    
	    if (!validator.validateAppointmentTime(time, doctor.getAvailableTime())) {
	    	logger.error("Invalid appointment time: Appointment slot of this time if out of working hours");
	    	throw new HealthcareException("Please enter a valid appointment time");
	    }
	    	    
	    Appointment app = new Appointment(appNum++, doctor.getDoctorId(), patient.getPatientId(), time, date, Status.CONFIRMED);
	    
	    if (!validator.validateAppointment(app, dao.getAllAppointments())) {
	    	logger.error("Invalid appointment time: Appointment slot of this time is not available");
	    	throw new HealthcareException("Doctor not free at that appointment");
	    }
	    
	    dao.bookAppointment(app);
	    
	    return Status.CONFIRMED;
		
	}
	
}
