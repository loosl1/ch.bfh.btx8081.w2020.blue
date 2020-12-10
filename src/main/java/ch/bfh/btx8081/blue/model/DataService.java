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
		entityManager.merge(patient);
		entityManager.flush();
		
		//Updatet spezifische Attribute in der Instant -> fügt danach zu Context hinzu und Commitet es.
		//entityManager.getTransaction().begin();
		// Updates hier.
		//entityManager.getTransaction().commit();
		
		//Verbindet die Dateien, da ID bereits Verbunden ist durch Entity -> fügt zu Persistence Context hinzu
		//entityManager.merge(patient);		
		//Alles im Persistence Context wird in Datenbank geschoben, unabhängig von commit
		//entityManager.flush();
	}
	
	/**
	 * Get a specific Patient by ID
	 * @param ID ID of the Patient
	 * @return Patient Object from the Database
	 */
	public Patient getPatient(int ID) {
		//entityManager.getTransaction().begin();
		Patient patient = entityManager.find(Patient.class, ID);
		return patient;
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
