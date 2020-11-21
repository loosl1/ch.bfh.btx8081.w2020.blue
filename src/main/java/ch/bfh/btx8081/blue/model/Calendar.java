package ch.bfh.btx8081.blue.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @author loosl1
 * <p>
 * created on 19/11/2020
 */

public class Calendar {

    private ArrayList<Appointment> appointments = new ArrayList<>();

    public Calendar(ArrayList<Appointment> appointments) {
        this.appointments = appointments;
    }

    /**
     * @param appointmentID
     * @return appointment or null if requirements arent met
     */
    public Appointment getAppointment(int appointmentID) {

        for (Appointment appointment : appointments) {

            if (appointmentID == appointment.appointmentID) {

                return appointment;

            }

        }

        return null;
    }

    //previous and next via appointment

    /**
     * @param appointmentID
     * @return appointment or null if requirements arent met
     */
    public Appointment getNextAppointment(int appointmentID) {

        for (Appointment appointment : appointments) {

            if (appointmentID == appointment.appointmentID) {

                return appointment;

            }

        }

        return null;
    }

    /**
     * @param appointmentId
     * @return appointment or null if requirements arent met
     */
    public Appointment getPreviousAppointment(int appointmentId) {

        for (Appointment appointment : appointments) {

            if (appointmentId == appointment.appointmentID) {

                LocalDateTime start = LocalDateTime.parse(appointment.getStart());

                LocalDateTime end = LocalDateTime.parse(appointment.getEnd());

                return appointment;

            }

        }

        return null;

    }

    //-------------------------Appointments----------------------------

    /**
     *
     * @return appointments
     */
    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }
}