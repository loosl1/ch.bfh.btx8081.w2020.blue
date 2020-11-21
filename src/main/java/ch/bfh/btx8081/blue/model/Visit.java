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

    private void saveReport(String s) {

        this.report = s;

    }

    //seq. diagram, loop through patients, comment
    private void updateReports(int visitID) {

        for (Patient patient : treatedPatients) {
            if (this.appointmentID == visitID) {
                setPatientReport(this.report);
            }
        }

    }

    //-------------Checklist----------------------

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
