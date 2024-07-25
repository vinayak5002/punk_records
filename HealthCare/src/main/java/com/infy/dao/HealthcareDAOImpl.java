package com.infy.dao;

import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.infy.model.Appointment;
import com.infy.model.Doctor;

public class HealthcareDAOImpl implements HealthcareDAO {

	List<Doctor> allDoctors;
	
	List<Appointment> allAppointment;
	
	public HealthcareDAOImpl() {
        Doctor doctor1 = new Doctor(1, "Dr. John Smith", "Cardiology", "10:00 AM - 6:00 PM", LocalDate.of(2024, 7, 25));
        Doctor doctor2 = new Doctor(2, "Dr. Emily Johnson", "Pediatrics", "11:00 AM - 7:00 PM", LocalDate.of(2024, 7, 26));
        Doctor doctor3 = new Doctor(3, "Dr. Michael Lee", "Orthopedics", "10:30 AM - 6:30 PM", LocalDate.of(2024, 7, 27));
        Doctor doctor4 = new Doctor(4, "Dr. Sarah Brown", "Dermatology", "12:00 PM - 8:00 PM", LocalDate.of(2024, 7, 28));
        Doctor doctor5 = new Doctor(5, "Dr. David Wilson", "Neurology", "10:00 AM - 5:00 PM", LocalDate.of(2024, 7, 29));

        allDoctors = Arrays.asList(doctor1, doctor2, doctor3, doctor4, doctor5);
        allAppointment = new ArrayList<>();
	}
	
	public void bookAppointment(Appointment appointment) {
		allAppointment.add(appointment);
	}
	
	@Override
	public List<Doctor> getAllDoctors() {
		return allDoctors;
	}

	public List<Appointment> getAllAppointments() {
		return allAppointment;
	}
	
}
