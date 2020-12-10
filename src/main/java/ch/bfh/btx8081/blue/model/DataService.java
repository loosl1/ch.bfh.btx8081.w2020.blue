package ch.bfh.btx8081.blue.model;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class DataService {
	public EntityManager entityManager;
	
	/**
	 * Empty Constructor
	 */
	public DataService() {
		entityManager = Persistence.createEntityManagerFactory("CareTaker").createEntityManager();
	}
	
	/**
	 * Destructor
	 */
	protected void finalize () {
		closeEntityManager();
	}
	
	/**
	 * Updates Changes to the Patient object to the database
	 * @param patient Updated Patient Object
	 */
	public void updatePatient(Patient patient) {
		Patient dbPatient = getPatient(patient.getPatientId());
		entityManager.getTransaction().begin();
		dbPatient.setAdress(patient.getAdress());
		dbPatient.setBirthday(patient.getBirthday());
		dbPatient.setContacts((ArrayList<Contact>) patient.getContacts());
		dbPatient.setDailyGoals(patient.getDailyGoals());
		dbPatient.setInfoAdmin(patient.getInfoAdmin());
		dbPatient.setName(patient.getName());
		dbPatient.setNamesuffix(patient.getNamesuffix());
		dbPatient.setPatientId(patient.getPatientId());
		dbPatient.setSurname(patient.getSurname());
		entityManager.getTransaction().commit();
	}
	
	/**
	 * Get a specific Patient by ID
	 * @param ID ID of the Patient
	 * @return Patient Object from the Database
	 */
	public Patient getPatient(int ID) {
		Patient statement = entityManager.find(Patient.class, ID);
		return statement;
	}
	
	/**
	 * Gets all Patients from the database
	 * @return
	 */
	public List<Patient> getAllPatients(){
		TypedQuery<Patient> statement = entityManager.createQuery("SELECT p FROM patients p", Patient.class);
		return statement.getResultList();
	}
	
	/**
	 * Handles the closing of the Entity Manager in this class.
	 */
	public void closeEntityManager() {
		entityManager.close();
	}
}
