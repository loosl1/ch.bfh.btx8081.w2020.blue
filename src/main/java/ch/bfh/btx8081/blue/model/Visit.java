package ch.bfh.btx8081.blue.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	 * loops through the treatedPatients List on which it searches for given ID
	 * which was given the constructed Visit If found, it ads the report with the id
	 * to the checklist
	 *
	 * @param visitID the ID of the visit
	 */
	private void updateRecords(int visitID) {

		for (Patient ignored : treatedPatients) {

			if (this.appointmentID == visitID) {

			}
		}
	}

	/**
	 * returns the checklist
	 * 
	 * @return Checklist object
	 */
	public Checklist getChecklist() {
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
