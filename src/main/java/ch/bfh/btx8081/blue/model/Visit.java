package ch.bfh.btx8081.blue.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author loosl1
 *
 *         created on 19/11/2020
 */
@Entity
@Table(name = "visits")
@PrimaryKeyJoinColumn(name = "visit")
public class Visit extends Appointment {

	@Transient
	private static int trackingId = 1000;

	@OneToOne
	private Checklist checklist;

	private String report; 

	@OneToMany
	private List<Patient> treatedPatients;

	/**
	 * empty Constructor
	 */
	public Visit() {
		super();
	}

	public Visit(LocalDateTime start, LocalDateTime end, String title, String info, Checklist checklist,
			ArrayList<Patient> treatedPatients, AppointmentType type) {
		super(start, end, title, info, type);
		appointmentID = trackingId++;
		this.checklist = checklist;
		this.treatedPatients = treatedPatients;
	}

	/**
	 * saves the report given s into the constructed object
	 *
	 * @param s Is the report given as a String which is being saved locally
	 */
	private void saveReport(String s) {

		this.report = s;

	}


	/**
	 * returns the checklist
	 * 
	 * @return Checklist object
	 */
	public Checklist getChecklist() {
		if (this.checklist == null) {
			this.checklist = new Checklist();
		}
		return checklist;
	}

	/**
	 * Saves the checklist given
	 * 
	 * @param checklist Is checklist Object
	 */
	public void setChecklist(Checklist checklist) {

		this.checklist = checklist;
	}

	/**
	 * Returns the report
	 * 
	 * @return report as a String
	 */
	public String getReport() {
		return report;
	}

	/**
	 * Saves the report
	 * 
	 * @param report Is the String saved in the report variable
	 */
	public void setReport(String report) {
		this.report = report;
	}

	/**
	 *
	 * @return patients as List
	 */
	public List<Patient> getTreatedPatients() {
		return treatedPatients;
	}

	/**
	 * Sets the treatedPatients
	 * 
	 * @param treatedPatients The Arraylist with all patients
	 */
	public void setTreatedPatients(ArrayList<Patient> treatedPatients) {
		this.treatedPatients = treatedPatients;
	}
}
