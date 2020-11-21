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

    public Visit(int appointmentID, LocalDateTime start, LocalDateTime end, String title, String info) {
        super(appointmentID, start, end, title, info);
    }


    //Constructor everywhere

    private void saveReport(String s) {

        report = s;

    }

    //seq. diagram, loop through patients, comment
    private void updateRecords(Visit visitID, PatientRecord record) {


    }

    //setter mit logik getter für jede variable retourniert,  try catch, constructor als klasse. this. in den methoden
    private ArrayList<Patient> getPatients() {
        
        return treatedPatients;
    
    }



}
