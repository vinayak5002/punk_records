package com.infy.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.infy.dao.HealthcareDAO;
import com.infy.dao.HealthcareDAOImpl;
import com.infy.model.*;
import com.infy.service.HealthcareService;
import com.infy.service.HealthcareServiceImpl;
import com.infy.validator.*;


public class HealthcareTest {
	
	private Validator validator;
    private List<Appointment> appointments;
    private HealthcareService service;

    @BeforeEach
    void setUp() {
        validator = new Validator();
        appointments = new ArrayList<>();
        service = new HealthcareServiceImpl();
    }

    @Tag("appointment")
    @Test
    void validateAppointmentTime_WithinRange_ReturnsTrue() {
        assertTrue(validator.validateAppointmentTime("11:00 AM - 12:00 PM", "10:00 AM - 1:00 PM"));
    }

    @Tag("appointment")
    @Test
    void validateAppointmentTime_OutsideRange_ReturnsFalse() {
        assertFalse(validator.validateAppointmentTime("9:00 AM - 10:00 AM", "10:00 AM - 1:00 PM"));
    }

    @Tag("appointment")
    @Test
    void validateAppointmentTime_ExactMatch_ReturnsTrue() {
        assertTrue(validator.validateAppointmentTime("10:00 AM - 1:00 PM", "10:00 AM - 1:00 PM"));
    }

    @Tag("appointment")
    @Test
    void validateAppointmentTime_OverlappingStart_ReturnsTrue() {
        assertTrue(validator.validateAppointmentTime("10:00 AM - 11:00 AM", "10:00 AM - 1:00 PM"));
    }

    @Tag("appointment")
    @Test
    void validateAppointmentTime_OverlappingEnd_ReturnsTrue() {
        assertTrue(validator.validateAppointmentTime("12:00 PM - 1:00 PM", "10:00 AM - 1:00 PM"));
    }

    @Tag("appointment")
    @Test
    void validateAppointment_UniqueAppointment_ReturnsTrue() {
        Appointment app = new Appointment(1, LocalDate.of(2024, 7, 25), "10:00 AM - 11:00 AM");
        assertTrue(validator.validateAppointment(app, appointments));
    }

    @Tag("appointment")
    @Test
    void validateAppointment_DuplicateAppointment_ReturnsFalse() {
        Appointment app1 = new Appointment(1, LocalDate.of(2024, 7, 25), "10:00 AM - 11:00 AM");
        Appointment app2 = new Appointment(1, LocalDate.of(2024, 7, 25), "10:00 AM - 11:00 AM");
        appointments.add(app1);
        assertFalse(validator.validateAppointment(app2, appointments));
    }

    @Tag("appointment")
    @Test
    void validateAppointment_DifferentDoctor_ReturnsTrue() {
        Appointment app1 = new Appointment(1, LocalDate.of(2024, 7, 25), "10:00 AM - 11:00 AM");
        Appointment app2 = new Appointment(2, LocalDate.of(2024, 7, 25), "10:00 AM - 11:00 AM");
        appointments.add(app1);
        assertTrue(validator.validateAppointment(app2, appointments));
    }

    @Tag("appointment")
    @Test
    void validateAppointment_DifferentDate_ReturnsTrue() {
        Appointment app1 = new Appointment(1, LocalDate.of(2024, 7, 25), "10:00 AM - 11:00 AM");
        Appointment app2 = new Appointment(1, LocalDate.of(2024, 7, 26), "10:00 AM - 11:00 AM");
        appointments.add(app1);
        assertTrue(validator.validateAppointment(app2, appointments));
    }
    
    @Tag("appointment")
    @Test
    void validateAppointment_DifferentTime_ReturnsTrue() {
        Appointment app1 = new Appointment(1, LocalDate.of(2024, 7, 25), "10:00 AM - 11:00 AM");
        Appointment app2 = new Appointment(1, LocalDate.of(2024, 7, 25), "11:00 AM - 12:00 PM");
        appointments.add(app1);
        assertTrue(validator.validateAppointment(app2, appointments));
    }
    
    @Tag("Search")
    @Test
    void validateSearchDoctorsBySpecialization() {
    	String specialization = "Cardiology";
    	List<Doctor> doctors = service.searchDoctorsBySpecialization("Cardiology");
    	
        for (Doctor doctor : doctors) {
            assertEquals(specialization, doctor.getSpecialization(), 
                "Doctor " + doctor.getDoctorName() + " should have specialization " + specialization);
        }
    	
    }
}
