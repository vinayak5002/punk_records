package com.infy.model;

import java.time.LocalDate;
import lombok.Data;

@Data
public class Appointment {

	private int appointmentId;
	private int doctorId;
	private int patientId;
	private String appointmentTime;
    private LocalDate appointmentDate;
    private Status status;
    
	public Appointment(int appointmentId, int doctorId, int patientId, String appointmentTime,
			LocalDate appointmentDate, Status status) {
		super();
		this.appointmentId = appointmentId;
		this.doctorId = doctorId;
		this.patientId = patientId;
		this.appointmentTime = appointmentTime;
		this.appointmentDate = appointmentDate;
		this.status = status;
	}

	public Appointment(int i, LocalDate of, String time) {
		this.doctorId = i;
		this.appointmentDate = of;
		this.appointmentTime = time;
	}

	public int getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	
}
