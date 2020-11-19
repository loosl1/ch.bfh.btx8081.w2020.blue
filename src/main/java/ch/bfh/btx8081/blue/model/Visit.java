package ch.bfh.btx8081.blue.model;

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

    private void saveReport(String s) {

        report = s;

    }

    private void updateReports(Visit visitID, PatientRecord record) {

    }

    private ArrayList<Patient> getPatient() {
        
        return treatedPatients;
    
    }

    public void setShortDescription(PatientReport report) {
    
        saveReport(report.toString());
    
    }

    private String displayAdminInfo() {
        
            HealthVisitor healthVisitor = null;
        
        return healthVisitor.getHealthVisitor();
    }

    private String displayAddress(){

        //toDo

        return null;
    }

}
