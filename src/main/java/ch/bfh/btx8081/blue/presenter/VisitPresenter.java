package ch.bfh.btx8081.blue.presenter;

import ch.bfh.btx8081.blue.exceptions.AppointmentNotFoundException;
import ch.bfh.btx8081.blue.model.Calendar;
import ch.bfh.btx8081.blue.model.*;
import ch.bfh.btx8081.blue.view.VisitView;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author loosl1
 * <p>
 * created on 05/12/2020
 */
public class VisitPresenter {
    //Configurations
    private static final int WORKHOURS_MIDDAY_HOUR = 12;
    private static final int WORKHOURS_MIDDAY_MINUTE = 0;
    private VisitView viewComponent;
    private List<Patient> currentPatients;
    private Patient currentPatient;
    private Appointment currentAppointment, recentAppointment;
    private Calendar calendar;
    private Checklist checklist;
    private Item item;
    private HealthVisitor currentUser;
    private DataService dataService;
    private Set<String> currentItems;

    /**
     * Constructor
     */
    public VisitPresenter(VisitView visitView, String appointmentId) {
        this.viewComponent = visitView;
        System.out.println("AppointmentId " + appointmentId);
        DataService dataService = new DataService();
        this.currentAppointment = dataService.getAppointment(1000);
        // manually created data
        Visit visit = ( Visit ) this.currentAppointment;
        this.currentItems = new TreeSet<String>();
        this.currentPatients = visit.getTreatedPatients(); //toDo add the currentuser
        System.out.println("--- Finish VisitPresenter()");
       /*
        //toDo for TESTING PURPOSES ONLY!
        this.currentPatient = new Patient("TestName", "testSurname", null, LocalDate.of(1990, 12, 12), new Address("Steinerstrasse", 12345, "Bern", 3000));
        System.out.println(this.currentPatient.getName());
        this.currentPatients.add(this.currentPatient);
        System.out.println(this.currentPatients.toString());
        //toDo for TESTING PURPOSES ONLY!
        //this.currentAppointment = new Appointment(LocalDateTime.now(), LocalDateTime.now().plusHours(2), "TestTitle", "TestInfo", VISIT);
   */
    }

    /**
     * Gets the current appointment and casts it to a Visit object so that we can access and cast the
     * checklist for the appointment to Strings
     *
     * @return Collection with Strings
     */
    public Collection<String> setupChecklist() throws AppointmentNotFoundException {
        Visit currentVisit = ( Visit ) this.currentAppointment;


        ArrayList<String> stringItems = new ArrayList<>(), itemsUnchecked = new ArrayList<>();

        if (stringItems.isEmpty()) {
            stringItems.add("Create a new item.");
        } else {
            for (Item item : currentVisit.getChecklist().getItems()) {
                stringItems.add(item.getDescription());
            }
        }

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
        LocalTime midday = LocalTime.of(WORKHOURS_MIDDAY_HOUR, WORKHOURS_MIDDAY_MINUTE);
        String morningAfternoon = (midday).isBefore(LocalTime.from(currentAppointment.getStart())) ? "Morgen" : "Nachmittag";
        header = patientName + ", " + dateFormatted + ", " + morningAfternoon;

        return header;
    }

    /**
     * Adds new items to the MultiSelectListBox checklist
     *
     * @param newItem String of item which should be added
     * @return Set with the provided items and the currentItems
     */
    public Set<String> addChecklistItem(String newItem) {
        if (!newItem.isEmpty()) {
            this.currentItems.add(newItem);
        }

        return currentItems;
    }

    /**
     * Closes the visit and gives all the open items to the next checklist
     *
     * @param selectedItems a Set of the checklist items
     */
    public void concludeVisit(Set<String> selectedItems) throws AppointmentNotFoundException {
        calendar = new Calendar();
        Visit nextVisit = null;

        if (calendar.getNextAppointment(this.currentAppointment.getAppointmentID()).isEmpty())
            nextVisit = ( Visit ) calendar.getNextAppointment(this.currentAppointment.getAppointmentID());
        nextVisit.setChecklist(( Checklist ) selectedItems);

    }
}

