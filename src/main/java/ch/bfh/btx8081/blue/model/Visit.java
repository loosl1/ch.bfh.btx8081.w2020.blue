package ch.bfh.btx8081.blue.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @author loosl1
 * <p>
 * created on 19/11/2020
 */
public class Visit extends Appointment {

    Checklist checklist;

    String report;

    ArrayList<Patient> treatedPatients = new ArrayList<>();

    public Visit(int appointmentID, LocalDateTime start, LocalDateTime end, String title, String info, Checklist checklist, ArrayList<Patient> treatedPatients) {
        super(appointmentID, start, end, title, info);
        this.checklist = checklist;
        this.treatedPatients = treatedPatients;
    }

    /**
     * saves the report given s into the constructed object
     * @param s
     */
    private void saveReport(String s) {

        this.report = s;

    }

    /**
     *  loops through the treatedPatients List on which it searches for given Id
     *  which was given the constructed Visit
     *  If found, it ads the report with the id to the checklist
     *
     *
     * @param visitID
     */
    private void updateReports(int visitID) {

        for (Patient ignored : treatedPatients) {

            if (this.appointmentID == visitID) {

                checklist.setPatientReport(this.report, visitID);
            }
        }

    }

    //-------------Checklist----------------------

    /**
     *
     * @return checklist object
     */
    public Checklist getChecklist() {
        return checklist;
    }

    public void setChecklist(Checklist checklist) {

        this.checklist = checklist;
    }

    //-------------Report----------------------

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    //-------------Patients----------------------

    public ArrayList<Patient> getTreatedPatients() {
        return treatedPatients;
    }

    public void setTreatedPatients(ArrayList<Patient> treatedPatients) {
        this.treatedPatients = treatedPatients;
    }
}
