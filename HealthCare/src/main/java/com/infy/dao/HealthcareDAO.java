package com.infy.dao;

import java.util.List;

import com.infy.model.*;

public interface HealthcareDAO {
	public List<Doctor> getAllDoctors();
	public void bookAppointment(Appointment appointment);
	public List<Appointment> getAllAppointments();
}
