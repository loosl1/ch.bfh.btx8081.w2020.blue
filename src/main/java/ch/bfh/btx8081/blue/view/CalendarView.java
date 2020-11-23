package ch.bfh.btx8081.blue.view;

import java.time.LocalDate;
import java.time.LocalTime;

import org.vaadin.stefan.fullcalendar.BusinessHours;
import org.vaadin.stefan.fullcalendar.CalendarViewImpl;
import org.vaadin.stefan.fullcalendar.Entry;
import org.vaadin.stefan.fullcalendar.FullCalendar;
import org.vaadin.stefan.fullcalendar.FullCalendarBuilder;

import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

import ch.bfh.btx8081.blue.model.*;
import ch.bfh.btx8081.blue.presenter.CalendarPresenter;

@SuppressWarnings("serial")
@Route("")
@Theme(value = Lumo.class, variant =Lumo.DARK)
@PWA(name = "CareTaker", shortName = "CareTaker")
@CssImport("styles/lumo-custom-dark-theme.css")
public class CalendarView extends VerticalLayout{
	//Presenter
	private CalendarPresenter presenter; 
	
	//Layout
	private HorizontalLayout topBar;
	private HorizontalLayout content;
	private VerticalLayout infopanel;
	
	//UI Objects
	private FullCalendar calendar;
	private Button btnToday;
	private Button btnNextAppointment;
	private Button btnPreviousAppointment;
	private Button btnOpenPatient;
	private Button btnOpenAppointment;
	private DatePicker dtpDatePicker;
	private Label lblPatientName;
	private Label lblBeginsAt;
	private Label lblEndsAt;
	private Label lblTitleAdress;
	private Label lblAdress;
	private Label lblTitleInfo;
	private Label lblInfo;
	

	/**
	 * Constructor
	 */
	public CalendarView () {
		addClassName("calendar-view");
		loadUIElements();

		this.topBar.setAlignItems(Alignment.END);
		this.topBar.add(
				this.dtpDatePicker,
				btnToday,
				btnPreviousAppointment,
				btnNextAppointment
		);
		HorizontalLayout buttonDiv = new HorizontalLayout(this.btnOpenAppointment,this.btnOpenPatient);
		buttonDiv.setAlignItems(Alignment.END);
		buttonDiv.add(this.btnOpenAppointment, this.btnOpenPatient);
		this.infopanel.add(
				this.lblPatientName,
				this.lblBeginsAt,
				this.lblEndsAt,
				this.lblTitleAdress,
				this.lblAdress,
				this.lblTitleInfo,
				this.lblInfo,
				buttonDiv
		);
		this.infopanel.setWidth("30%");
		this.content.add(this.calendar, this.infopanel);
		//Add UI Components
		add(this.topBar);
	    add(this.content);
	    
	    //Add some Displaydata
	    fillInfopanel();
	    this.calendar.addEntries(this.presenter.getAppointmentsForCalendar());
	}

	private void loadUIElements() {
		this.presenter = new CalendarPresenter(this);
		this.topBar = new HorizontalLayout();
		this.content = new HorizontalLayout();
		this.infopanel = new VerticalLayout();
		
		this.calendar = FullCalendarBuilder.create().build();
		this.presenter.setupCalendarConfiguration(this.calendar);
		
		this.btnToday = new Button("Today",
						event -> this.calendar.today());
		this.btnNextAppointment = new Button("Next \nAppointment",
						event -> this.presenter.getNextAppointment());
		this.btnPreviousAppointment = new Button("Previous\nAppointment",
						event -> this.presenter.getPreviousAppointment());
		this.btnOpenAppointment = new Button("Open Appointment",
						event -> Notification.show("Clicked Open Appointment"));
		this.btnOpenPatient = new Button("Open Patient",
						event -> Notification.show("Clicked Open Patient"));
		this.dtpDatePicker = new DatePicker();
		this.dtpDatePicker.setLabel("Current Date");
		this.dtpDatePicker.setValue(LocalDate.now());
		this.lblPatientName = new Label();
		this.lblPatientName.setClassName("title-label");
		this.lblTitleInfo = new Label();
		this.lblTitleInfo.setClassName("title-label");
		this.lblTitleAdress = new Label();
		this.lblTitleAdress.setClassName("title-label");
		this.lblBeginsAt = new Label();
		this.lblBeginsAt.setClassName("text-label");
		this.lblEndsAt = new Label();
		this.lblEndsAt.setClassName("text-label");
		this.lblAdress = new Label();
		this.lblAdress.setClassName("text-label");
		this.lblInfo = new Label();
		this.lblInfo.setClassName("text-label");
	}
	
	//Only for Visualization
	private void fillInfopanel() {
		
		this.lblPatientName.setText("Lanzerray, Kurt");
		this.lblTitleInfo.setText("Info");
		this.lblTitleAdress.setText("Adresse");
		this.lblBeginsAt.setText("Beginnt\t26.10.2020\t08:30");
		this.lblEndsAt.setText("Endet\t26.10.2020\t09:30");
		this.lblAdress.setText("Lanzerray Kurt" + "<br>"
				+ "Beispielstrasse 1\r\n"
				+ "1234 Beispielort\r\n");
		this.lblInfo.setText("Parkplatz 17 & 18\r\n"
				+ "Hausschlüssel: \r\n"
				+ "Standort 1 - Kasten 1b\r\n");
	}
	
	/**
	 * Sets the selected Day of the Calendar to today's date.
	 */
	private void setToday() {
		this.calendar.today();
	}
	
	
	/**
	 * 
	 * @param appointment
	 */
	private VisitView showVisit(Appointment currentAppointment) {
		//Platzhalter)
		return new VisitView();
	}
	
	private PatientView showPatient (Appointment currentAppointment) {
		//Platzhalter
		return new PatientView();
	}
}

