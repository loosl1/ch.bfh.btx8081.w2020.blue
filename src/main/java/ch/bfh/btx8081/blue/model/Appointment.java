package ch.bfh.btx8081.blue.model;

import java.time.DateTimeException;
import java.time.LocalDateTime;

/**
 * @author loosl1
 * <p>
 * created on 19/11/2020
 */
public class Appointment {

    protected int appointmentID;

    protected LocalDateTime start, end;

    protected String title, info;

    protected enum type {

        GROUPVISIT,

        VISIT,

        INTERNAL

    }

    public Appointment(int appointmentID, LocalDateTime start, LocalDateTime end, String title, String info) {
        this.appointmentID = appointmentID;
        this.start = start;
        this.end = end;
        this.title = title;
        this.info = info;
    }

    /**
     * @return id of the appointment
     */
    public int getAppointmentID() {
        return appointmentID;
    }

    /**
     * asks if the value given is empty
     *
     * @param appointmentID
     */
    public void setAppointmentID(int appointmentID) {
        try {
            this.appointmentID = appointmentID;
        } catch (Exception e) {
            throw new IllegalArgumentException("AppointmentID is either null or blank: " + e);
        }
    }


    /**
     * @return startdate as LocalDateTime
     */
    public LocalDateTime getStart() {
        return start;

    }


    /**
     * asks if the value given is empty and if the enddate is after the startdate
     *
     * @param start
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
     * @return end date as Local Date Time
     */
    public LocalDateTime getEnd() {
        return end;
    }


    /**
     * asks if the value given is empty and if the startdate is before the enddate
     *
     * @param end
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
     * @return title as String
     */
    public String getTitle() {
        return title;
    }


    /**
     * asks if the value given is empty
     *
     * @param title
     */
    public void setTitle(String title) {

        if (title.isEmpty()) {

            throw new NullPointerException("Title is empty");

        } else {
            this.title = title;
        }
    }

    /**
     * @return info as String
     */
    public String getInfo() {
        return info;
    }

    /**
     * asks if the value given is empty
     *
     * @param info
     */
    public void setInfo(String info) {
        if (info.isEmpty()) {

            throw new NullPointerException("Info is empty");

        } else {
            this.info = info;
        }
    }

}


