package ch.bfh.btx8081.blue.presenter;

import ch.bfh.btx8081.blue.model.*;
import ch.bfh.btx8081.blue.view.VisitView;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * @author loosl1
 * <p>
 * created on 05/12/2020
 */
public class VisitPresenter {
    private VisitView viewComponent;
    private Patient currentPatient;
    private Calendar calendar;
    private Checklist checklist;
    private Item item;
    private HealthVisitor currentUser;
    private Appointment currentAppointment;
    private DataService dataService;

    /**
     * Constructor
     */
    public VisitPresenter(Appointment appointment) {
        this.viewComponent = new VisitView();

        // manually created data
        this.currentAppointment = appointment;
        this.currentPatient = dataService.getAllPatients().get(currentPatient.getPatientId()); //toDo add the currentuser


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

        for (Item item : visit.getChecklist().getItems()) {
            stringItems.add(item.getDescription());
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
        String patientName = this.currentPatient.getFullname();

        //Casts the date of the appointment to a String in the correct Format
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String dateFormatted = currentAppointment.getStart().format(formatterDate);

        //Is intended for the information if the visit is during the morning or during the afternoon
        OffsetDateTime midday = OffsetDateTime.parse(currentAppointment.getStart().toString()); //is the time 12:00
        String morningAfternoon = OffsetDateTime.parse(currentAppointment.getStart().toString()).isBefore(midday) ?"Morgen":"Nachmittag";

        header = patientName + ", " + dateFormatted + ", " + morningAfternoon;

        return header;
    }


}
