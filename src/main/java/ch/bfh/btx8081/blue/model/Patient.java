package ch.bfh.btx8081.blue.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author loosl1
 * <p>
 * created on 19/11/2020
 */
public class Patient {
	protected ArrayList<Contact> contacts = new ArrayList<>();
	protected Calendar calendar;
	protected Checklist dailyGoals;
	protected String infoAdmin;
	protected int patientId;
	HashMap<Integer, PatientRecord> myRecords = new HashMap<>();
	
	public ArrayList<Contact> getContacts() {
		return contacts;
	};
	
	public void setContacts (ArrayList<Contact> newContacts) {
		this.contacts = newContacts;
	};
	
	public Checklist getChecklist() {
		return dailyGoals;
	};
	
	public void setDailyGoals (Checklist newDailyGoals) {
		this.dailyGoals = newDailyGoals;
	};
	
	public String getInfoAdmin() {
		return infoAdmin;
	};
	
	public void setInfoAdmin (String newInfoAdmin) {
		this.infoAdmin = newInfoAdmin;
	};
	
	public int getPatientId() {
		return patientId;
	};
	
	public void setPatientId (int newPatientId) {
		this.patientId = newPatientId;
	};

    /**
     * 
     * @param appointmentId
     */
    
    public void loadRecord(int appointmentId) {
    	myRecords.put(appointmentId, new PatientRecord());
	};



}
