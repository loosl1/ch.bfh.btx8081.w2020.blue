package ch.bfh.btx8081.blue.model;

import ch.bfh.btx8081.blue.exceptions.AppointmentNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author loosl1
 *         <p>
 *         created on 19/11/2020
 */

@Entity
@Table(name = "calendars")
public class Calendar {

	@Id
	@GeneratedValue
	long id; // still set automatically

	@OneToMany
	private List<Appointment> appointments; // Changed to List because of JPA

	/**
	 * empty constructor
	 */
	public Calendar() {
		this.appointments = new ArrayList<Appointment>();
	}

	/**
	 * Constructor with existing appointments
	 * 
	 * @param appointments ArrayList with appointments
	 */
	public Calendar(ArrayList<Appointment> appointments) {
		this.appointments = appointments;
	}

	/**
	 * Loops through all the appointments and returns the current one
	 * 
	 * @param appointmentID the id of the current appointment
	 * @return returns the current appointment or an Exception
	 * @throws AppointmentNotFoundException Is thrown when no id is found
	 */
	public Appointment getAppointment(int appointmentID) throws AppointmentNotFoundException {

		Appointment currentAppointment = null;

		// loops through all the appointments and sets the nextAppointment to the
		// currentAppointment
		for (Appointment appointment : appointments) {

			currentAppointment = appointmentID == appointment.appointmentID ? appointment : null;

		}

		if (currentAppointment == null) {
			throw new AppointmentNotFoundException("Appointment not found");
		}

		return currentAppointment;
	}

	/**
	 * Returns the next appointment
	 *
	 * @param appointmentID the ID of the current appointment
	 * @return nextAppointment returns the next appointment or an Exception
	 * @throws AppointmentNotFoundException Is thrown when no id is found
	 */
	public Appointment getNextAppointment(int appointmentID) throws AppointmentNotFoundException {

		// Returns the id of the current Appointment
		Appointment currentAppointment = getAppointment(appointmentID);

		Appointment nextAppointment = null;

		// Sorts the Appointments according to the Start Time and Date
		Collections.sort(appointments);

		// loops through all the appointments and sets the nextAppointment to the
		// currentAppointment + 1
		int i = appointments.size() - 1;
		while (i >= 0) {

			nextAppointment = appointments.get(i) == currentAppointment ? appointments.get(i + 1) : null;

			i--;
		}

		if (nextAppointment == null) {
			throw new AppointmentNotFoundException("Appointment not found");
		}
		return nextAppointment;
	}

	//

	/**
	 * Returns the previous appointment
	 *
	 * @param appointmentID the ID of the current appointment
	 * @return previousAppointment returns the previous appointment or an Exception
	 * @throws AppointmentNotFoundException Is thrown when no id is found
	 */
	public Appointment getPreviousAppointment(int appointmentID) throws AppointmentNotFoundException {

		// Returns the id of the current Appointment
		Appointment currentAppointment = getAppointment(appointmentID);

		Appointment previousAppointment = null;

		// Sorts the Appointments according to the Start Time and Date
		Collections.sort(appointments);

		// loops through all the appointments and sets the previousAppointment to the
		// currentAppointment -1
		int i = appointments.size() - 1;
		while (i >= 0) {

			previousAppointment = appointments.get(i) == currentAppointment ? appointments.get(i - 1) : null;

			i--;
		}

		if (previousAppointment == null) {
			throw new AppointmentNotFoundException("Appointment not found");
		}
		return previousAppointment;
	}

	/**
	 * Returns all appointments
	 *
	 * @return appointments returns Arraylist appointments
	 */
	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void addAppointment(Appointment appointment) {
		this.appointments.add(appointment);
	}
}
