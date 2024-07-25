package com.infy.validator;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.infy.exception.HealthcareException;
import com.infy.model.Appointment;
import com.infy.model.Patient;
import com.infy.userinterface.HealthcareTester;

import net.sf.saxon.Configuration;

public class Validator {
	
    protected static final Logger logger = LogManager.getLogger(Validator.class);
    
    
    public void validatePatient(Patient patient) throws HealthcareException, ConfigurationException {
    	    	
    	Configurations configurations = new Configurations();
    	PropertiesConfiguration config = configurations.properties("configuration.properties");
    	
        StringBuilder errorMessage = new StringBuilder();

        // Validate patientId
        if (patient.getPatientId() <= 0) {
            logger.error("Invalid patient ID: " + patient.getPatientId());
            errorMessage.append(config.getProperty("Validator.INVALID_PATIENT_ID"));
            throw new HealthcareException(errorMessage.toString());
        }

        // Validate patientName
        if (!validatePatientName(patient.getPatientName())) {
            logger.error("Invalid patient name: " + patient.getPatientName());
            errorMessage.append(config.getProperty("Validator.INVALID_PATIENT_NAME"));
            throw new HealthcareException(errorMessage.toString());
        }

        // Validate gender
        if (!validateGender(patient.getGender())) {
            logger.error("Invalid gender: " + patient.getGender());
            errorMessage.append("Validator.INVALID_PATIENT_GENDER");
            throw new HealthcareException(errorMessage.toString());
        }

        // Validate contactNumber
        if (!validateContactNumber(patient.getContactNumber())) {
            logger.error("Invalid contact number: " + patient.getContactNumber());
            errorMessage.append("Validator.INVALID_CONTACT_NUMBER");
            throw new HealthcareException(errorMessage.toString());
        }

    }
	
	public boolean validatePatientName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        String[] words = name.split("\\s+");
        if (words.length < 1) {
            return false;
        }
        for (String word : words) {
            if (!Pattern.matches("[A-Z][a-z]*", word)) {
                return false;
            }
        }
        return true;
    }

    public boolean validateContactNumber(Long number) {
    	String num = number.toString();
        if (num == null || num.length() != 10) {
            return false;
        }
        if (!num.matches("\\d{10}")) {
            return false;
        }
        // Check if all digits are not the same
        return !num.matches("(\\d)\\1{9}");
    }

    public boolean validateGender(String gender) {
        if (gender == null) {
            return false;
        }
        return gender.equals("Male") || gender.equals("Female") || gender.equals("Others");
    }

    public boolean validatePatientIssue(String patientIssue) {
        return patientIssue != null && !patientIssue.trim().isEmpty();
    }
	
	public boolean validateAppointmentTime(String app, String avail) {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
	    
	    String[] parts1 = app.split(" - ");
	    String[] parts2 = avail.split(" - ");
	    
	    LocalTime start1 = LocalTime.parse(parts1[0], formatter);
	    LocalTime end1 = LocalTime.parse(parts1[1], formatter);
	    LocalTime start2 = LocalTime.parse(parts2[0], formatter);
	    LocalTime end2 = LocalTime.parse(parts2[1], formatter);
	    
	    return !start1.isBefore(start2) && !end1.isAfter(end2);
	}
	
	public boolean validateAppointment(Appointment app, List<Appointment> appointments) {
		boolean valid = true;
		
		for (Appointment e : appointments) {
			if (e.getDoctorId() == app.getDoctorId() && e.getAppointmentDate().equals(app.getAppointmentDate()) && e.getAppointmentTime().equals(app.getAppointmentTime())) {
				return false;
			}
		}
		
		return true;
	}
}
