package ch.bfh.btx8081.blue.presenter;

import ch.bfh.btx8081.blue.model.*;
import ch.bfh.btx8081.blue.view.VisitView;

import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author loosl1
 * <p>
 * created on 05/12/2020
 */
public class VisitPresenter {
    private VisitView viewComponent;
    private List <Patient> currentPatients;
    private Calendar calendar;
    private Checklist checklist;
    private Item item;
    private HealthVisitor currentUser;
    private Appointment currentAppointment;
    private DataService dataService;
    private Set<String> currentItems;

    /**
     * Constructor
     */
    public VisitPresenter(VisitView visitView, String AppointmentId) {
    	this.viewComponent = visitView;
    	DataService dataService = new DataService();
    	this.currentAppointment = dataService.getAppointment(1000);
        // manually created data
        Visit visit = (Visit) this.currentAppointment;
        this.currentPatients = visit.getTreatedPatients(); //toDo add the currentuser
        System.out.println("--- Finish VisitPresenter()");

    }

    /**
     * Gets the current appointment and casts it to a Visit object so that we can access and cast the
     * checklist for the appointment to Strings
     *
     * @return Collection with Strings
     */
    public Collection<String> setupChecklist() {
        Visit visit = ( Visit ) this.currentAppointment;

        ArrayList<String> stringItems = new ArrayList<>();
        if (stringItems.isEmpty()) {
        	stringItems.add("Create a new item.");
        }
        else {
        for (Item item : visit.getChecklist().getItems()) {
            stringItems.add(item.getDescription());
        }}
 
        return stringItems;
    }

    /**
     * Sets the title / header in the view
     *
     * @return Returns a String that consists of the name of the patient, the date of the appointment and if the appointment is in the morning or afternoon
     */
    public String displayHeader() {
        String header;

        //The name of the patient
        String patientName = "";
        for (Patient patient : currentPatients) {
        	patientName = patient.getFullname();
        }

        //Casts the date of the appointment to a String in the correct Format
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String dateFormatted = currentAppointment.getStart().format(formatterDate);

      //Is intended for the information if the visit is during the morning or during the afternoon
        LocalTime midday = LocalTime.of(12, 00);
        String morningAfternoon = (midday).isBefore(LocalTime.from(currentAppointment.getStart())) ? "Morgen" : "Nachmittag";
        header = patientName + ", " + dateFormatted + ", " + morningAfternoon;


        return header;
    }

    /**
     * Adds new items to the MultiSelectListBox checklist
     * @param newItem String of item which should be added
     * @return Set with the provided items and the currentItems
     */
    public Set<String> addChecklistItem(String newItem) {
        if(!newItem.isEmpty()) {
            this.currentItems.add(newItem);
        }

        return currentItems;
    }


    public void concludeVisit(Set<String> selectedItems) {
        this.checklist = new Checklist();
        checklist.setItems(selectedItems);

    }
}

