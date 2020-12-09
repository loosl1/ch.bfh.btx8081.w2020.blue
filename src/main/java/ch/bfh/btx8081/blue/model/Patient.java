package ch.bfh.btx8081.blue.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author loosl1
 *         <p>
 *         created on 19/11/2020
 */
@Entity
@Table(name = "patients")
public class Patient extends Person {
	@OneToMany
	protected List<Contact> contacts; // Changed to List because of JPA
	@OneToOne
	protected Calendar calendar;
	@OneToOne
	protected Checklist dailyGoals;
	protected String infoAdmin;
	@Transient
	protected static int patientIdCounter = 0;
	protected int patientId;
	@Transient
	HashMap<Integer, PatientRecord> myRecords = new HashMap<>();

	/**
	 * empty Constructor
	 */ 
	public Patient() {
		super();
	}

	/* Constructor */
	public Patient(String name, String surname, String namesuffix, LocalDate birthday, Address address) {
		super(name, surname, namesuffix, birthday);
		super.setAdress(address);
		this.contacts = new ArrayList<>();
		this.calendar = new Calendar();
		this.patientId = patientIdCounter++;
	}

	/* Constructor including infoAdmin */
	public Patient(String name, String surname, String namesuffix, LocalDate birthday, Address address,
			String infoAdmin) {
		super(name, surname, namesuffix, birthday);
		super.setAdress(address);
		this.contacts = new ArrayList<>();
		this.calendar = new Calendar();
		this.patientId = patientIdCounter++;
		this.infoAdmin = infoAdmin;
	}

	/**
	 * TODO Check this method
	 * 
	 * @param appointmentId loads a new record to patient
	 */

	public void loadRecord(int appointmentId) {
		myRecords.put(appointmentId, new PatientRecord());
	};

	/* getters and setters */

	/**
	 * 
	 * @return gets the contacts from a patient
	 */
	public List<Contact> getContacts() {
		return contacts;
	}

	/**
	 * 
	 * @param newContacts sets new contact for patient
	 */
	public void setContacts(ArrayList<Contact> newContacts) {
		this.contacts = newContacts;
	};

	/**
	 * 
	 * @return gets the dailyGoals for a patient
	 */
	public Checklist getDailyGoals() {
		return dailyGoals;
	};

	/**
	 * 
	 * @param newDailyGoals sets new dailyGoals for patient
	 */
	public void setDailyGoals(Checklist newDailyGoals) {
		this.dailyGoals = newDailyGoals;
	};

	/**
	 * 
	 * @return gets infoAdmin for patient
	 */
	public String getInfoAdmin() {
		return infoAdmin;
	};

	/**
	 * 
	 * @param newInfoAdmin sets new infoAdmin for patient
	 */
	public void setInfoAdmin(String newInfoAdmin) {
		this.infoAdmin = newInfoAdmin;
	};

	/**
	 * 
	 * @return gets a patient's ID
	 */
	public int getPatientId() {
		return patientId;
	};

	/**
	 * 
	 * @param newPatientId sets new patient ID
	 */
	public void setPatientId(int newPatientId) {
		this.patientId = newPatientId;
	};
}
