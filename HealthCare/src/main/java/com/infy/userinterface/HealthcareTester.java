package com.infy.userinterface;

import com.infy.service.*;
import com.infy.validator.Validator;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.infy.exception.HealthcareException;
import com.infy.model.*;

public class HealthcareTester {
	
    protected static final Logger logger = LogManager.getLogger(HealthcareTester.class);
	
	static HealthcareService service = new HealthcareServiceImpl();

	static Validator validator = new Validator();
	
	public static void main(String[] args) throws ConfigurationException {
		
		viewAllDoctors();
		
		searcDoctorBySpecialization("Cardiology");
		
		Patient patient = new Patient(5002, "Vinayak", "Male", "Nothing", 9832749284L, "11:00 AM - 12:00 PM", LocalDate.of(2024, 10, 29));
		
		try {
			bookAppointment(patient, 1, patient.getAppointmentDate(), patient.getAppointmentTime());
		}
		catch(HealthcareException e) {
			logger.info("Appointment of " + patient.getPatientId() + " failed: " + e.getMessage());
		}
		
		try {
			bookAppointment(patient, 1, patient.getAppointmentDate(), patient.getAppointmentTime());
		}
		catch(HealthcareException e) {
			logger.error("Appointment of " + patient.getPatientId() + " failed: " + e.getMessage());
		}
		
		Patient patient2 = new Patient(5003, "Vinayak", "M", "Nothing", 9832749284L, "11:00 AM - 12:00 PM", LocalDate.of(2024, 10, 29));
		Patient patient3 = new Patient(5004, "Vinayak", "Male", "Nothing", 98327492842L, "11:00 AM - 12:00 PM", LocalDate.of(2024, 10, 29));
		
		try {
			bookAppointment(patient2, 1, patient2.getAppointmentDate(), patient2.getAppointmentTime());
		}
		catch(HealthcareException e) {
			logger.error("Appointment of " + patient2.getPatientId() + " failed: " + e.getMessage());
		}
		
		try {
			bookAppointment(patient3, 1, patient3.getAppointmentDate(), patient3.getAppointmentTime());
		}
		catch(HealthcareException e) {
			logger.error("Appointment of " + patient3.getPatientId() + " failed: " + e.getMessage());
		}
		
	}
	
	private static void bookAppointment(Patient patient, int doctorId, LocalDate date, String time) throws HealthcareException, ConfigurationException  {

		validator.validatePatient(patient);
	
		Status status = service.bookAppointment(patient, doctorId, date, time);
		
		if(status.equals(Status.CONFIRMED)) {
			System.out.println("Appointment of patient id " + patient.getPatientId() + " booked sucessfully");
			logger.debug("Appointment of patient id " + patient.getPatientId( ) + " booked sucessfully");
		}
	}
	
	private static void searcDoctorBySpecialization(String specialization) {
		
		List<Doctor> doctors = service.searchDoctorsBySpecialization(specialization);
		
		System.out.println("Doctors of " + specialization);
		System.out.println("=================================");
		System.out.println("ID \t Dr.Name \t\t Specialization \t Available Date \t Time");
		System.out.println("=================================");
		doctors.stream().forEach(obj->{
			System.out.println(obj.getDoctorId()+"\t"+obj.getDoctorName()+"\t\t"+obj.getSpecialization()+"\t"+obj.getAvailableDate()+"\t" + obj.getAvailableTime());
		});
		System.out.println("=================================");
	}

	public static void viewAllDoctors() {
		
		List<Doctor> doctors = service.getAllDoctors();
		
		System.out.println("All doctors");
		System.out.println("=================================");
		System.out.println("ID \t Dr.Name \t\t Specialization \t Available Date \t Time");
		System.out.println("=================================");
		doctors.stream().forEach(obj->{
			System.out.println(obj.getDoctorId()+"\t"+obj.getDoctorName()+"\t\t"+obj.getSpecialization()+"\t"+obj.getAvailableDate()+"\t" + obj.getAvailableTime());
		});
		System.out.println("=================================");
	}
}
