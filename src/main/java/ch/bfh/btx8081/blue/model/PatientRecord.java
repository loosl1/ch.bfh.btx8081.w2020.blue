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
    String title;
    String description;

    /**
     * Constructor
     *
     * @param date          Date on when the report was created
     * @param healthVisitor The Healthvisitor who created the report
     * @param title         Title of the report
     * @param description   Description of the report
     */
    public PatientRecord(Date date, HealthVisitor healthVisitor, String title, String description) {
        this.date = date;
        this.healthVisitor = healthVisitor;
        this.title = title;
        this.description = description;
        this.localDate = java.util.Calendar.getInstance().getTime();
    }

    /**
     * Constructor
     *
     * @param healthVisitor
     */
    public PatientRecord(HealthVisitor healthVisitor) {
        this.date = java.util.Calendar.getInstance().getTime();
        this.localDate = java.util.Calendar.getInstance().getTime();
        this.healthVisitor = healthVisitor;
        this.title = "";
        this.description = "";
    }

    public PatientRecord() {

    }

    /**
     * Gets the date
     *
     * @return Returns the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets a new date
     *
     * @param date Date to be set
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

    public HealthVisitor getHealthVisitor() {
        return healthVisitor;
    }

    public void setHealthVisitor(HealthVisitor healthVisitor) {
        this.healthVisitor = healthVisitor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
