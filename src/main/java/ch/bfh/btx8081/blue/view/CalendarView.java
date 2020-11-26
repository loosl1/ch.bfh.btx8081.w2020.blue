package ch.bfh.btx8081.blue.view;

import java.time.LocalDate;

import org.vaadin.stefan.fullcalendar.FullCalendar;
import org.vaadin.stefan.fullcalendar.FullCalendarBuilder;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

import ch.bfh.btx8081.blue.presenter.CalendarPresenter;

@SuppressWarnings("serial")
@Route("")
@Theme(value = Lumo.class, variant =Lumo.DARK)
@PWA(name = "CareTaker", shortName = "CareTaker")
@CssImport("styles/lumo-custom-dark-theme.css")
@CssImport("styles/custom-formatting.css")
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
	private Label lblAdressName;
	private Label lblAdress;
	private Label lblAdressPlace;
	private Label lblTitleInfo;
	private TextArea lblInfo;


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
				this.lblAdressName,
				this.lblAdress,
				this.lblAdressPlace,
				this.lblTitleInfo,
				this.lblInfo,
				buttonDiv
		);
		this.infopanel.setWidth("30%");
		this.content.add(this.calendar, this.infopanel);
		//Add UI Components
		add(this.topBar);
	    add(this.content);

		this.calendar.addEntries(this.presenter.generateEntriesForCalendar());
	}

	private void loadUIElements() {
		this.presenter = new CalendarPresenter(this);
		this.topBar = new HorizontalLayout();
		this.content = new HorizontalLayout();
		this.infopanel = new VerticalLayout();

		this.calendar = FullCalendarBuilder.create().build();
		this.presenter.setupCalendarConfiguration(this.calendar);

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
		this.lblAdressName = new Label();
		this.lblAdressName.setClassName("text-label");
		this.lblAdressPlace = new Label();
		this.lblAdressPlace.setClassName("text-label");
		this.lblInfo = new TextArea();
		this.lblInfo.setClassName("text-label");

		this.btnToday = new Button("Today",
						event -> this.presenter.setSelectedDate(LocalDate.now()));
		this.btnNextAppointment = new Button("Next \nAppointment",
						event -> this.presenter.getNextAppointment());
		this.btnPreviousAppointment = new Button("Previous\nAppointment",
						event -> this.presenter.getPreviousAppointment());
		this.btnOpenAppointment = new Button("Open Appointment",
						event -> showVisit());
		this.btnOpenPatient = new Button("Open Patient",
						event -> showPatient());
		this.dtpDatePicker = new DatePicker("Current Date",
						event -> this.presenter.setSelectedDate(dtpDatePicker.getValue()));
		this.dtpDatePicker.setLabel("Current Date");
		this.dtpDatePicker.setValue(this.presenter.getSelectedDate());

	}

	public void update() {
		this.calendar.gotoDate(this.presenter.getSelectedDate());
		this.dtpDatePicker.setValue(this.presenter.getSelectedDate());

		//@ TODO Create Infopanel Logic
		//Is directly implemented, as long as logic for model is not implemented
		this.lblPatientName.setText("Lanzerray, Kurt");
		this.lblTitleInfo.setText("Info");
		this.lblTitleAdress.setText("Adresse");
		this.lblBeginsAt.setText("Beginnt\t26.10.2020\t08:30");
		this.lblEndsAt.setText("Endet\t26.10.2020\t09:30");
		this.lblAdressName.setText("Lanzerray Kurt");
		this.lblAdress.setText("Beispielstrasse 1");
		this.lblAdressPlace.setText("1234 Beispielort");
		this.lblInfo.setReadOnly(true);
		this.lblInfo.setValue("Parkplatz 17 & 18\r\n"
				+ "Hausschlüssel: \r\n"
				+ "Standort 1 - Kasten 1b\r\n");
	}

	/**
	 *
	 * @param appointment
	 */
	private VisitView showVisit() {
		Notification.show("Clicked Open Appointment");
		return new VisitView();
	}

	private PatientView showPatient () {
		Notification.show("Clicked Open Patient");
		return new PatientView();
	}
}
