package ch.bfh.btx8081.blue.model;

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

    public Appointment getAppointment(int appointmentID) {

        for (Appointment appointment : appointments) {

            if (appointmentID == appointment.appointmentID) {

                return appointment;

            }

        }

        return null;
    }

    //previous and next via appointment

    public Appointment getNextAppointment(int currentAppointmentId){

        for (Appointment appointment : appointments) {

            if (currentAppointmentId == appointment.appointmentID) {

                return appointment;

            }

        }

        return null;
    }

    public Appointment getPreviousAppointment(int currentAppointmentId){

        for (Appointment appointment : appointments) {

            if (currentAppointmentId == appointment.appointmentID) {

                return appointment;

            }

        }

        return null;

    }

}
