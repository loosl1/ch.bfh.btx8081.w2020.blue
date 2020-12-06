package ch.bfh.btx8081.blue.model;

import javax.persistence.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * @author loosl1
 * <p>
 * created on 19/11/2020
 */

@Entity
@Table(name = "patientrecords")
public class PatientRecord {

    @Transient // toDo dont know if we want to have this in the DB
    protected List<Appointment> calendars;
    @Transient
    protected Calendar calendar;
    @Id
    @GeneratedValue
    long id; // still set automatically
    LocalDate date, localDate;
    @OneToOne
    HealthVisitor healthVisitor;
    String title; // Title consists of the Date of the report, the Patient and the HealthVisitor
    String description; // Description of specific information for the patient

    /**
     * Empty Constructor
     */
    public PatientRecord() {
        this.localDate = LocalDate.now();
        this.date = this.localDate;
        this.title = "";
        this.description = "";

    }

    /**
     * Constructor
     *
     * @param date          Date on when the report was created
     * @param healthVisitor The Healthvisitor who created the report
     * @param title         Title of the report
     * @param description   Description of the report
     */
    public PatientRecord(LocalDate date, HealthVisitor healthVisitor, String title, String description) {
        this.localDate = LocalDate.now();
        this.date = date;
        this.healthVisitor = healthVisitor;
        this.title = title;
        this.description = description;
        calendars = this.calendar.getAppointments();

    }

    /**
     * Constructor
     *
     * @param healthVisitor creates a Healthvisitor
     */
    public PatientRecord(HealthVisitor healthVisitor) {

        this.localDate = LocalDate.now();
        this.date = this.localDate;
        this.healthVisitor = healthVisitor;
        this.title = "";
        this.description = "";
    }

    /**
     * Gets the date
     *
     * @return Returns the date as Date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets a new date
     *
     * @param date Date to be set as Date
     */
    public void setDate(LocalDate date) {

        if (date.isAfter(localDate)) {
            throw new DateTimeException("The date is after the current Date");
        } else if (date.toString().isEmpty()) {
            throw new NullPointerException("The date given was empty");
        } else {
            this.date = date;
        }
    }

    /**
     * Returns the healthvisitor
     *
     * @return Healthvisitor
     */
    public HealthVisitor getHealthVisitor() {
        return healthVisitor;
    }

    /**
     * Sets a new Healthvisitor
     *
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
     *
     * @return Returns the Title as a String
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets a new title
     *
     * @param title The Title which is to be set as a String
     */
    public void setTitle(String title) {
        if (Objects.equals(title, "") || title.isEmpty()) {
            throw new NullPointerException("The Title value was null");
        } else {
            this.title = calendar.getRecentAppointment().toString() +", "+ title + ", "+ healthVisitor.name;
        }
    }

    /**
     * Returns the description for the patient
     *
     * @return Returns the description for the patient as a String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets a new description for a patient
     *
     * @param description Sets a description as a String
     */
    public void setDescription(String description) {
        if (Objects.equals(description, "") || description.isEmpty()) {
            throw new NullPointerException("The Description value was null");
        } else {
            this.description = description;
        }
    }
}
