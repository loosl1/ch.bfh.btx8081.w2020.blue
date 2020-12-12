package ch.bfh.btx8081.blue.model;

import java.sql.Timestamp;
import java.time.DateTimeException;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author loosl1
 * <p>
 * created on 19/11/2020
 */

@Entity
@Table(name = "appointments")
@Inheritance(strategy = InheritanceType.JOINED)
public class Appointment implements Comparable<Appointment> {

	@Id
	protected int appointmentID;

	@Column(name="end_datetime") // Name "end" dont work in JPA
	protected Timestamp end;

	@Column(name="start_datetime")  // Same Name Shema as "end_datetime"
	protected Timestamp start;

	@Transient
	private static int trackingId = 1000;
	private AppointmentType appointmentType;

	protected String title, info;

	public enum AppointmentType {
		GROUPVISIT,
		VISIT,
		INTERNAL 
	}

	/**
	 * empty constructor
	 */
	public Appointment() {
		this.appointmentID = trackingId++;
	}

	public Appointment(LocalDateTime start, LocalDateTime end, String title, String info, AppointmentType type) {
		this.appointmentID = trackingId++;
		this.start = Timestamp.valueOf(start);
		this.end = Timestamp.valueOf(end);
		this.title = title;
		this.info = info;
		this.appointmentType = type;
	}

	/**
	 * returns the of of the appointment
	 *
	 * @return ID of the appointment
	 */
	public int getAppointmentID() {
		return appointmentID;
	}

	/**
	 * asks if the value given is empty
	 *
	 * @param appointmentID the idNo of the appointment
	 */
	public void setAppointmentID(int appointmentID) {
		try {
			this.appointmentID = appointmentID;
		} catch (Exception e) {
			throw new IllegalArgumentException("AppointmentID is either null or blank: " + e);
		}
	}

	/**
	 * returns the start date and time
	 *
	 * @return startdate as LocalDateTime
	 */
	public LocalDateTime getStart() {
		return start.toLocalDateTime();

	}

	/**
	 * asks if the value given is empty and if the enddate is after the startdate
	 *
	 * @param start start date and time
	 */
	public void setStart(LocalDateTime start) {
		if (!end.toString().isEmpty()) {

			if (end.before(this.start)) {

				this.start = Timestamp.valueOf(start);

			} else {

				throw new DateTimeException("End date is before start date");

			}

		} else if (start.toString().isEmpty()) {

			throw new NullPointerException("Start date is empty");

		}
	}

	/**
	 * returns the end date and time
	 *
	 * @return end date and time
	 */
	public LocalDateTime getEnd() {
		return end.toLocalDateTime();
	}

	/**
	 * asks if the value given is empty and if the startdate is before the enddate
	 *
	 * @param end end date and time
	 */
	public void setEnd(LocalDateTime end) {

		if (!start.toString().isEmpty()) {

			if (start.before(this.end)) {

				this.end = Timestamp.valueOf(end);

			} else {

				throw new DateTimeException("Start date is after end date");

			}

		} else if (end.toString().isEmpty()) {

			throw new NullPointerException("End date is empty");

		}

	}

	/**
	 * returns the title
	 *
	 * @return title as String
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Asks if the value given is empty and sets it onto the variable title
	 *
	 * @param title The title of the appointment
	 */
	public void setTitle(String title) {

		if (title.isEmpty()) {

			throw new NullPointerException("Title is empty");

		} else {
			this.title = title;
		}
	}

	/**
	 * Returns the information about the patient
	 *
	 * @return Info as String
	 */
	public String getInfo() {
		return info;
	}

	/**
	 * Sets the written Info-String onto the local info variable
	 *
	 * @param info Is the information written by the Health Visitor about the
	 *             Patient
	 */
	public void setInfo(String info) {
		if (info.isEmpty()) {

			throw new NullPointerException("Info is empty");

		} else {
			this.info = info;
		}
	}

	/**
	 * Returns the Appointment Type
	 * @return Returns the Appointments Type with enum AppointmentType
	 */
	public AppointmentType getAppointmentType() {
		return appointmentType;
	}
	
	/**
	 * Sets the new Appointment Type
	 * @param type One of the enum constants of AppointmentType, which is to be set
	 */
	public void setAppointmentType(AppointmentType type) {
		this.appointmentType = type;
	}
	
	/**
	 * Defines a default sort criteria for sorting Objects via the Comparable class
	 *
	 * @param appointment the appointment in the list which is being compared to the
	 *                    next appointment
	 * @return Returns the compared start date given to the start date
	 */
	@Override
	public int compareTo(Appointment appointment) {
		if (getStart() == null || appointment.getStart() == null) {
			return 0;
		}
		return getStart().compareTo(appointment.getStart());
	}

}
