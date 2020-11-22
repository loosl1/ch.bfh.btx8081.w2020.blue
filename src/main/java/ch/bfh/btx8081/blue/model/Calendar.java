package ch.bfh.btx8081.blue.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
     * @param appointmentID
     * @return appointment or null if requirements arent met
     */
    public Appointment getPreviousAppointment(int appointmentID) {

        //LocalDateTime currentTime = null;

        Appointment previousAppointment = null;

        List<LocalDateTime> appointmentTimes = new ArrayList<>();

        appointments.forEach(value -> appointmentTimes.add(value.getStart()));

        appointmentTimes.sort(Comparator.naturalOrder());



        for (int i = 0; i < appointmentTimes.size(); i++) {



            //if (appointmentID == appointments.get(i).appointmentID) {

                // currentTime = appointments.get(i - 1).getStart();

                // LocalDateTime end = LocalDateTime.parse(appointment.getEnd());

                // previousAppointment = appointmentTimes.get(i - 1);
            //}

        }

        return previousAppointment;

    }


    /**
     * @return appointments
     */
    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }
}