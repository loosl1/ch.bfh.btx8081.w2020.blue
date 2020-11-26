package ch.bfh.btx8081.blue.model;

import java.time.DateTimeException;
import java.time.LocalDateTime;

/**
 * @author loosl1
 * <p>
 * created on 19/11/2020
 */
public class Appointment implements Comparable<Appointment> {

    protected int appointmentID;

    protected LocalDateTime start, end;

    private static int  trackingId=1000;

    protected String title, info;

    protected enum type {

        GROUPVISIT,

        VISIT,

        INTERNAL

    }

    public Appointment(LocalDateTime start, LocalDateTime end, String title, String info) {
        this.appointmentID = trackingId++;
        this.start = start;
        this.end = end;
        this.title = title;
        this.info = info;
    }

    /**
     * returns the of of the appointment
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
     * @return startdate as LocalDateTime
     */
    public LocalDateTime getStart() {
        return start;

    }


    /**
     * asks if the value given is empty and if the enddate is after the startdate
     *
     * @param start start date and time
     */
    public void setStart(LocalDateTime start) {
        if (!end.toString().isEmpty()) {

            if (end.isBefore(this.start)) {

                this.start = start;

            } else {

                throw new DateTimeException("End date is before start date");

            }

        } else if (start.toString().isEmpty()) {

            throw new NullPointerException("Start date is empty");

        }
    }

    /**
     * returns the end date and time
     * @return end date and time
     */
    public LocalDateTime getEnd() {
        return end;
    }


    /**
     * asks if the value given is empty and if the startdate is before the enddate
     *
     * @param end end date and time
     */
    public void setEnd(LocalDateTime end) {

        if (!start.toString().isEmpty()) {

            if (start.isBefore(this.end)) {

                this.end = end;

            } else {

                throw new DateTimeException("Start date is after end date");

            }

        } else if (end.toString().isEmpty()) {

            throw new NullPointerException("End date is empty");

        }


    }

    /**
     * returns the title
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
     * @return Info as String
     */
    public String getInfo() {
        return info;
    }

    /**
     * Sets the written Info-String onto the local info variable
     *
     * @param info Is the information written by the Health Visitor about the Patient
     */
    public void setInfo(String info) {
        if (info.isEmpty()) {

            throw new NullPointerException("Info is empty");

        } else {
            this.info = info;
        }
    }

    /**
     * Defines a default sort criteria for sorting Objects via the Comparable class
     * @param appointment the appointment in the list which is being compared to the next appointment
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


