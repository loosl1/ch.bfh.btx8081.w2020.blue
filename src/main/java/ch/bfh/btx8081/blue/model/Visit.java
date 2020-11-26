package ch.bfh.btx8081.blue.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @author loosl1
 * <p>
 * created on 19/11/2020
 */
public class Visit extends Appointment {

    private static int  trackingId=1000;

    Checklist checklist;

    String report;

    ArrayList<Patient> treatedPatients;

    public Visit(LocalDateTime start, LocalDateTime end, String title, String info, Checklist checklist, ArrayList<Patient> treatedPatients) {
        super(start, end, title, info);
        appointmentID = trackingId++;
        this.checklist = checklist;
        this.treatedPatients = treatedPatients;
    }

    /**
     * saves the report given s into the constructed object
     *
     * @param s Is the report given as a String which is being saved locally
     */
    private void saveReport(String s) {

        this.report = s;

    }

    /**
     * loops through the treatedPatients List on which it
     * searches for given ID which was given the constructed Visit
     * If found, it ads the report with the id to the checklist
     *
     * @param visitID the ID of the visit
     */
    private void updateReports(int visitID) {

        for (Patient ignored : treatedPatients) {

            if (this.appointmentID == visitID) {

                checklist.setPatientReport(this.report, visitID);
            }
        }
    }

    /**
     * returns the checklist
     * @return Checklist object
     */
    public Checklist getChecklist() {
        return checklist;
    }

    /**
     * Saves the checklist given
     * @param checklist Is checklist Object
     */
    public void setChecklist(Checklist checklist) {

        this.checklist = checklist;
    }

    /**
     * Returns the report
     * @return report as a String
     */
    public String getReport() {
        return report;
    }

    /**
     * Saves the report
     * @param report Is the String saved in the report variable
     */
    public void setReport(String report) {
        this.report = report;
    }

    /**
     *
     * @return patients as Arraylist
     */
    public ArrayList<Patient> getTreatedPatients() {
        return treatedPatients;
    }

    /**
     * Sets the treatedPatients
     * @param treatedPatients The Arraylist with all patients
     */
    public void setTreatedPatients(ArrayList<Patient> treatedPatients) {
        this.treatedPatients = treatedPatients;
    }
}
