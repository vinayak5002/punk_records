package com.infy.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Patient {
	private int patientId;
	private String patientName;
	private String gender;
	private String patientIssues;
	private long contactNumber;
	private String appointmentTime;
	private LocalDate appointmentDate;
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPaitentName(String paitentName) {
		this.patientName = paitentName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPatientIssues() {
		return patientIssues;
	}
	public void setPatientIssues(String patientIssues) {
		this.patientIssues = patientIssues;
	}
	public long getContactNumber() {
		return contactNumber;
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
	public Patient(int patientId, String paitentName, String gender, String patientIssues, long contactNubmber,
			String appointmentTime, LocalDate appointmentDate) {
		super();
		this.patientId = patientId;
		this.patientName = paitentName;
		this.gender = gender;
		this.patientIssues = patientIssues;
		this.contactNumber = contactNubmber;
		this.appointmentTime = appointmentTime;
		this.appointmentDate = appointmentDate;
	}
	
	
}
