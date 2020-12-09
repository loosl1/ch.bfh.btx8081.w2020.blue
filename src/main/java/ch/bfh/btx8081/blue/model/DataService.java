package ch.bfh.btx8081.blue.model;

import java.util.List;


import javax.persistence.EntityManager;
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
