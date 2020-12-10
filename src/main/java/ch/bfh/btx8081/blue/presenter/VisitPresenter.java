package ch.bfh.btx8081.blue.presenter;

import ch.bfh.btx8081.blue.model.*;
import ch.bfh.btx8081.blue.view.VisitView;

/**
 * @author loosl1
 *
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
    public VisitPresenter(VisitView viewComponent){
        this.viewComponent = viewComponent;

        // manual created data
        this.currentPatient = dataService.getAllPatients().get(0);







    }







}
