package ch.bfh.btx8081.blue.view;

import java.time.LocalDate;

import org.vaadin.stefan.fullcalendar.CalendarViewImpl;
import org.vaadin.stefan.fullcalendar.FullCalendar;
import org.vaadin.stefan.fullcalendar.FullCalendarBuilder;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

import ch.bfh.btx8081.blue.model.Appointment.AppointmentType;
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
	private Grid<VerticalLayout> layoutGrid;
	private VerticalLayout infopanel;

	//UI Objects
	private FullCalendar calendar;
	private Button btnToday;
	private Button btnNextAppointment;
	private Button btnPreviousAppointment;
	private Button btnOpenPatient;
	private Button btnOpenAppointment;
	private ComboBox<String> cmbChangeCalendarType;
	private DatePicker dtpDatePicker;
	private Label lblPatientName;
	private Label lblBeginsAt;
	private Label lblEndsAt;
	private Label lblTitleAddress;
	private Label lblAddressName;
	private Label lblAddress;
	private Label lblAddressPlace;
	private Label lblTitleInfo;
	private TextArea lblInfo;


	/**
	 * Constructor
	 */
	public CalendarView () {
		addClassName("calendar-view");
		loadUIElements();

		this.topBar.setAlignItems(Alignment.END);
		this.topBar.setPadding(true);
		this.topBar.add(
				this.dtpDatePicker,
				this.btnToday,
				this.btnPreviousAppointment,
				this.btnNextAppointment,
				this.cmbChangeCalendarType
		);
		HorizontalLayout buttonDiv = new HorizontalLayout(this.btnOpenAppointment,this.btnOpenPatient); //Used to align Buttons horizontally
		buttonDiv.setAlignItems(Alignment.END);
		buttonDiv.add(this.btnOpenAppointment, this.btnOpenPatient);
		this.infopanel.add(
				this.lblPatientName,
				this.lblBeginsAt,
				this.lblEndsAt,
				this.lblTitleAddress,
				this.lblAddressName,
				this.lblAddress,
				this.lblAddressPlace,
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
		this.infopanel.addClassName("info-panel");

		this.calendar = FullCalendarBuilder.create().build();
		this.presenter.setupCalendarConfiguration(this.calendar);
		this.calendar.addEntryClickedListener(event -> this.presenter.setCurrentAppointment(event.getEntry()));
		this.calendar.setHeight(750);

		this.lblPatientName = new Label();
		this.lblPatientName.setClassName("title-label");
		this.lblTitleInfo = new Label();
		this.lblTitleInfo.setClassName("title-label");
		this.lblTitleAddress = new Label();
		this.lblTitleAddress.setClassName("title-label");
		this.lblBeginsAt = new Label();
		this.lblBeginsAt.setClassName("text-label");
		this.lblEndsAt = new Label();
		this.lblEndsAt.setClassName("text-label");
		this.lblAddress = new Label();
		this.lblAddress.setClassName("text-label");
		this.lblAddressName = new Label();
		this.lblAddressName.setClassName("text-label");
		this.lblAddressPlace = new Label();
		this.lblAddressPlace.setClassName("text-label");
		this.lblInfo = new TextArea();
		this.lblInfo.setClassName("text-label");

		this.btnToday = new Button("Today",
						event -> this.presenter.setSelectedDate(LocalDate.now()));
		this.btnNextAppointment = new Button("Next \nAppointment",
						event -> this.presenter.getNextAppointment());
		this.btnPreviousAppointment = new Button("Previous\nAppointment",
						event -> this.presenter.getPreviousAppointment());
		this.btnOpenAppointment = new Button("Open Appointment",
						event -> this.presenter.createVisit());
		this.btnOpenPatient = new Button("Open Patient",
						event -> showPatient());
		this.dtpDatePicker = new DatePicker("Current Date",
						event -> this.presenter.setSelectedDate(dtpDatePicker.getValue()));
		this.dtpDatePicker.setLabel("Current Date");
		this.dtpDatePicker.setValue(this.presenter.getSelectedDate());

		this.cmbChangeCalendarType = new ComboBox<>();
		this.cmbChangeCalendarType.setClassName("viewtype-picker");
		this.cmbChangeCalendarType.setLabel("View type:");
		this.cmbChangeCalendarType.setItems("Daily", "Weekly", "Monthly", "List-Daily", "List-Weekly", "List-Monthly" );
		this.cmbChangeCalendarType.setClearButtonVisible(false);
		this.cmbChangeCalendarType.setValue("Daily");
		this.cmbChangeCalendarType.addValueChangeListener(event -> this.presenter.setCalendarType(event.getValue()));
	}

	public void update() {
		this.calendar.gotoDate(this.presenter.getSelectedDate());
		this.dtpDatePicker.setValue(this.presenter.getSelectedDate());
		if (this.presenter.getCurrentAppointment() != null && this.presenter.getCurrentAppointment().getAppointmentType() != AppointmentType.INTERNAL) {
			this.lblPatientName.setVisible(true); 
			this.lblTitleInfo.setVisible(true);
			this.lblTitleAddress.setVisible(true);
			this.lblBeginsAt.setVisible(true);
			this.lblEndsAt.setVisible(true);
			this.lblAddressName.setVisible(true);
			this.lblAddress.setVisible(true);
			this.lblAddressPlace.setVisible(true);
			this.lblInfo.setVisible(true);
			
			this.lblPatientName.setText(this.presenter.getCurrentAppointment().getTitle());
			this.lblTitleInfo.setText("Info");
			this.lblTitleAddress.setText("Adresse");
			
			this.lblBeginsAt.setText("Starts: " + this.presenter.formattedDate(this.presenter.getCurrentAppointment().getStart()));
			this.lblEndsAt.setText("Ends:   " + this.presenter.formattedDate(this.presenter.getCurrentAppointment().getEnd()));
			this.lblAddressName.setText(this.presenter.displayNameOfPatient(this.presenter.getCurrentAppointment()));
			this.lblAddress.setText(this.presenter.displayAddressOfPatient(this.presenter.getCurrentAppointment()));
			this.lblAddressPlace.setText(this.presenter.displayPlaceOfPatient(this.presenter.getCurrentAppointment()));
			this.lblInfo.setReadOnly(true);
			this.lblInfo.setValue(this.presenter.getCurrentAppointment().getInfo());
		}
		else {
			this.lblPatientName.setVisible(false);
			this.lblTitleInfo.setVisible(false);
			this.lblTitleAddress.setVisible(false);
			this.lblBeginsAt.setVisible(false);
			this.lblEndsAt.setVisible(false);
			this.lblAddressName.setVisible(false);
			this.lblAddress.setVisible(false);
			this.lblAddressPlace.setVisible(false);
			this.lblInfo.setVisible(false);
		}
	}

	/**
	 * Changes the Appearance of the Calendar when a new Item in the dropdown is selected.
	 * @param type Type of the new Appearance
	 */
	public void changeCalendarAppearance (CalendarViewImpl type) {
		this.calendar.changeView(type);
		/*
		this.getElement().executeJs(
				"var sheet = new CSSStyleSheet; sheet.replaceSync($0); window.location.href.shadowRoot.adoptedStyleSheets = [ sheet ] ",
				".fc-unthemed .fc-list-heading td {background: #292d2e !important;color: #60dde6 !important;}"
		);*/
	}
	


	private PatientView showPatient () {
		Notification.show("Clicked Open Patient");
		return new PatientView();
	}
}
