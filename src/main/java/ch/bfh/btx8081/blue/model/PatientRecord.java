package ch.bfh.btx8081.blue.model;

import java.time.DateTimeException;
import java.util.Date;

/**
 * @author loosl1
 * <p>
 * created on 19/11/2020
 */
public class PatientRecord {

    Date date, localDate;
    HealthVisitor healthVisitor;
    String title; //toDo Wie ist der Titel zu verstehen? aus was besteht dieser?
    String description; //Description of specific information for the patient

    /**
     * Constructor
     *
     * @param date          Date on when the report was created
     * @param healthVisitor The Healthvisitor who created the report
     * @param title         Title of the report
     * @param description   Description of the report
     */
    public PatientRecord(Date date, HealthVisitor healthVisitor, String title, String description) {
        this.localDate = java.util.Calendar.getInstance().getTime();
        this.date = date;
        this.healthVisitor = healthVisitor;
        this.title = title;
        this.description = description;

    }

    /**
     * Constructor
     *
     * @param healthVisitor creates a Healthvisitor
     */
    public PatientRecord(HealthVisitor healthVisitor) {

        this.localDate = java.util.Calendar.getInstance().getTime();
        this.date = this.localDate;
        this.healthVisitor = healthVisitor;
        this.title = "";
        this.description = "";
    }

    /**
     * Empty Constructor //toDo discuss if really needed and how to handle the situation without a Healthvisitor
     */
    public PatientRecord() {
        this.localDate = java.util.Calendar.getInstance().getTime();
        this.date = this.localDate;
        this.title = "";
        this.description = "";

    }

    /**
     * Gets the date
     *
     * @return Returns the date as Date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets a new date
     *
     * @param date Date to be set as Date
     */
    public void setDate(Date date) {

        if (date.after(localDate)) {
            throw new DateTimeException("The date is after the current Date");
        } else if (date.toString().isEmpty()) {
            throw new NullPointerException("The date given was empty");
        } else {
            this.date = date;
        }

    }

    /**
     * Returns the healthvisitor
     * @return Returns the Healthvisitor as a Healthvisitor
     */
    public HealthVisitor getHealthVisitor() {
        return healthVisitor;
    }

    /**
     * Sets a new Healthvisitor //toDo DISCUSS IF REALLY NEEDED
     * @param healthVisitor The Healthvisitor as a Healthvisitor
     */
    public void setHealthVisitor(HealthVisitor healthVisitor) {
        if (healthVisitor.toString().isEmpty()) {
            throw new NullPointerException("The HealthVisitor value was null");
        } else {
            this.healthVisitor = healthVisitor;
        }
    }

    /**
     * Returns the Title of the PatientRecord
     * @return Returns the Title as a String
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets a new title
     * @param title The Title which is to be set as a String
     */
    public void setTitle(String title) {
        if (title == "" || title.isEmpty()) {
            throw new NullPointerException("The Title value was null");
        } else {
            this.title = title;
        }
    }

    /**
     * Returns the description for the patient
     * @return Returns the description for the patient as a String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets a new description for a patient
     * @param description Sets a description as a String
     */
    public void setDescription(String description) {
        if (description == "" || description.isEmpty()) {
            throw new NullPointerException("The Description value was null");
        } else {
            this.description = description;
        }
    }
}
