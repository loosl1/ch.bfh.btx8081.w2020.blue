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

    public Appointment(int appointmentID, LocalDateTime start, LocalDateTime end, String title, String info) {
        this.appointmentID = appointmentID;
        this.start = start;
        this.end = end;
        this.title = title;
        this.info = info;
    }

    //----------------Appointment------------------
    public int getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(int appointmentID) {
        try {
            this.appointmentID = appointmentID;
        } catch (Exception e) {
            throw new IllegalArgumentException("AppointmentID is either null or blank: " + e);
        }
    }

    //----------------Start------------------
    public String getStart() {
        return start.toString();
    }

    public void setStart(String start) {
        if (!end.toString().isEmpty()) {

            if (end.isBefore(this.start)) {

                this.start = LocalDateTime.parse(start);

            } else {

                throw new DateTimeException("End date is before start date");

            }

        } else {

            throw new NullPointerException("Start date is empty");

        }


    }

    //----------------End------------------
    public String getEnd() {
        return end.toString();
    }

    public void setEnd(String end) {

        if (!start.toString().isEmpty()) {

            if (start.isBefore(this.end)) {

                this.end = LocalDateTime.parse(end);

            } else {

                throw new DateTimeException("Start date is after end date");

            }

        } else {

            throw new NullPointerException("End date is empty");

        }


    }

    //----------------Title------------------
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    //----------------Info------------------
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    protected enum type {

        GROUPVISIT,

        VISIT,

        INTERNAL

    }


}
