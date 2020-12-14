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
	 * Get a specific Patient by ID
	 * @param ID ID of the Patient
	 * @return Patient Object from the Database
	 */
	public Appointment getAppointment(int appointmentID) {
		//entityManager.getTransaction().begin();
		Appointment appointment = entityManager.find(Appointment.class, appointmentID);
		return appointment;
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
	
	/**
	 * Get a specific HealthVisitor by ID
	 * @param ID ID of the HealthVisitor
	 * @return HealthVisitor Object from the Database
	 */
	public HealthVisitor getHealthVisitor(long ID) {
		HealthVisitor healthVisitor = entityManager.find(HealthVisitor.class, ID);
		return healthVisitor;
	}
	
	
	/*
	* Generate Demo Data - is not been use anymore
	public void generateData() {

		entityManager.getTransaction().begin();
		HealthVisitor DemoHealthVisitor = this.getHealthVisitor(2);
		ArrayList<Patient> firstList = new ArrayList<>();
		Address addresse = new Address("Lyssstrasse", 12, "Urtenen-Schönbühl", 3322);
		Patient patient1 = new Patient("Stanic", "Nikola", "von der Weide", LocalDate.of(1997, 2, 1), addresse);
		entityManager.persist(patient1);
		entityManager.persist(patient1.getCalendar());
		Address addresse2 = new Address("Grubenstrasse", 53, "Belp", 3106);
		Patient patient2 = new Patient("Müller", "Stephanie", "", LocalDate.of(2002, 12, 11), addresse2);
		entityManager.persist(patient2);
		entityManager.persist(patient2.getCalendar());
		firstList.add(patient1);
		ArrayList<Patient> secondList = new ArrayList<>();
		secondList.add(patient2);
		Appointment appointment1 = new Visit(LocalDate.parse("2020-11-26").atTime(10, 0),LocalDate.parse("2020-11-26").atTime(11, 0),"Besuch bei Broenimanns","Und hier die Infos", null , firstList, AppointmentType.GROUPVISIT);
		entityManager.persist(appointment1);
		DemoHealthVisitor.getCalendar().addAppointment(appointment1);
		Appointment appointment2 = new Visit(LocalDate.parse("2020-11-26").atTime(11, 15),LocalDate.parse("2020-11-26").atTime(11, 30),"Besuch bei Broenimanns","Und hier die Infos", null , secondList, AppointmentType.GROUPVISIT);
		entityManager.persist(appointment2);
		DemoHealthVisitor.getCalendar().addAppointment(appointment2);
		Appointment appointment3 = new Appointment(LocalDate.parse("2020-11-27").atTime(8, 0),LocalDate.parse("2020-11-27").atTime(9, 0),"Besuch bei Broenimanns","Und hier die Infos", AppointmentType.VISIT);
		entityManager.persist(appointment3);
		DemoHealthVisitor.getCalendar().addAppointment(appointment3);
		Appointment appointment4 = new Appointment(LocalDate.parse("2020-11-27").atTime(9, 0),LocalDate.parse("2020-11-27").atTime(9, 30),"Besuch bei Broenimanns","Und hier die Infos", AppointmentType.INTERNAL);
		entityManager.persist(appointment4);
		DemoHealthVisitor.getCalendar().addAppointment(appointment4);
		entityManager.getTransaction().commit();
		
	}
	*/
}
