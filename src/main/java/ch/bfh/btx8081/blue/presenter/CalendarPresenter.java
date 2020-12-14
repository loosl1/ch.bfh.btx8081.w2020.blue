package ch.bfh.btx8081.blue.presenter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.bfh.btx8081.blue.view.VisitView;
import org.vaadin.stefan.fullcalendar.BusinessHours;
import org.vaadin.stefan.fullcalendar.CalendarViewImpl;
import org.vaadin.stefan.fullcalendar.Entry;
import org.vaadin.stefan.fullcalendar.FullCalendar;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.QueryParameters;

import ch.bfh.btx8081.blue.exceptions.AppointmentNotFoundException;
import ch.bfh.btx8081.blue.model.*;
import ch.bfh.btx8081.blue.model.Appointment.AppointmentType;
import ch.bfh.btx8081.blue.view.CalendarView;

public class CalendarPresenter {
	private final CalendarView viewComponent;
	private Appointment currentAppointment;
	private LocalDate selectedDate;
	private final HealthVisitor currentUser;

	//Configurations
	private static final int WORKHOURS_START_HOUR = 6;
	private static final int WORKHOURS_START_MINUTE = 0;
	private static final int WORKHOURS_END_HOUR = 18;
	private static final int WORKHOURS_END_MINUTE = 0;
	private static final String APPOINTMENT_COLOR_LARGE = "#ff5969";
	private static final String APPOINTMENT_COLOR_SMALL = "#fec630";
	private static final String APPOINTMENT_COLOR_ADMIN = "#52cbbe";
	private static final int APPOINTMENT_IS_LARGE_IN_MIN = 60;
	private static final String DATETIME_PATTERN = "dd.MM.yyyy        HH:mm";


	/**
	 * Constructor 
	 */
	public CalendarPresenter (CalendarView calendarView) {
		this.viewComponent = calendarView;
		this.selectedDate = LocalDate.now();
		// manual created data 
		DataService dataService = new DataService();
		// dataService.generateData();
		currentUser = dataService.getHealthVisitor(2);
	}

	/**
	 * Sets configuration for a FullCalendar Object
	 * @param calendar FullCalendar-Object to be configured.
	 */
	public void setupCalendarConfiguration(FullCalendar calendar) {
		calendar.changeView(CalendarViewImpl.TIME_GRID_DAY);
		calendar.setBusinessHours(new BusinessHours(LocalTime.of(WORKHOURS_START_HOUR, WORKHOURS_START_MINUTE),
													LocalTime.of(WORKHOURS_END_HOUR, WORKHOURS_END_MINUTE),
													BusinessHours.DEFAULT_BUSINESS_WEEK));
		calendar.today();
		calendar.setWeekNumbersVisible(false);
		calendar.setWidth("70%");
	}

	public LocalDate getSelectedDate() {
		return selectedDate;
	}

	public void setSelectedDate(LocalDate newdate) {
		this.selectedDate = newdate;
		this.viewComponent.update();
	}

	/**
	 * Converts the Appointments into applyable Objects of the Type "Entry", since the Calendar Object can only handle these type of objects.
	 * @return List of all Entries to be displayed.
	 */
	public ArrayList<Entry> generateEntriesForCalendar() {
		ArrayList<Entry> entries = new ArrayList<>();
		this.currentUser.getCalendar().getAppointments().forEach(appointment -> {
			Entry entry = new Entry(); //Makes Creating an Entry into Calendar Object possible.
		    entry.setTitle(appointment.getTitle());
		    entry.setStart(appointment.getStart());
		    entry.setEnd(appointment.getEnd());
		    //Color Handling
		    if (appointment.getAppointmentType() != AppointmentType.INTERNAL) {
		    	int hourDiff, minDiff; //Needed to calculate difference of start and endtime
		    	hourDiff = (appointment.getEnd().getHour() - appointment.getStart().getHour());
		    	minDiff = (appointment.getEnd().getMinute() - appointment.getStart().getMinute());
		    	if ((hourDiff * 60) + minDiff >= APPOINTMENT_IS_LARGE_IN_MIN) {
		    		entry.setColor(APPOINTMENT_COLOR_LARGE);
		    	}
		    	else {
		    		entry.setColor(APPOINTMENT_COLOR_SMALL);
		    	}
		    }
		    else {
		    	entry.setColor(APPOINTMENT_COLOR_ADMIN);
		    }
		    
		    entry.setEditable(false);
		    entries.add(entry);
		});
	    return entries;
	}
	

	/**
	 * Gets next Appointment to the currently selected one.
	 * @return Next Appointment from the currently selected Appointment.
	 * @throws AppointmentNotFoundException
	 */
	public Appointment getNextAppointment(){
		Appointment nextAppointment = null; //Is used for try/catch, so it can be made
		try {
			nextAppointment = this.currentUser.getCalendar().getNextAppointment(this.currentAppointment.getAppointmentID());
		} catch (AppointmentNotFoundException e) {
			e.printStackTrace();
		}
		return nextAppointment;
	}

	/**
	 * Gets previous Appointment to the currently selected one.
	 * @return Previous Appointment from the currently selected Appointment.
	 * @throws AppointmentNotFoundException
	 */
	public Appointment getPreviousAppointment(){
		Appointment prevAppointment = null; //Is used for try/catch, so it can be made
		try {
			prevAppointment = this.currentUser.getCalendar().getNextAppointment(this.currentAppointment.getAppointmentID());
		} catch (AppointmentNotFoundException e) {
			e.printStackTrace();
		}
		return prevAppointment;
	}

	/**
	 * Changes the current selected Appointment.
	 * @param entry New entry to be tracked.
	 */
	public void setCurrentAppointment (Entry entry) {
		this.currentUser.getCalendar().getAppointments().forEach(appointment -> {
			if(appointment.getStart().equals(entry.getStart()) && appointment.getEnd().equals(entry.getEnd()) ) {
				this.currentAppointment = appointment;
			}
		});
		this.viewComponent.update();
	}

	/**
	 * Returns the currently Selected Appointment
	 * @return Currently selected Appointment.
	 */
	public Appointment getCurrentAppointment () {
		return this.currentAppointment;
	}
	
	/**
	 * Changes the appearance of the Calendar Object
	 * @param viewtype Type for the Calendar Appearance
	 */
	public void setCalendarType (String viewtype) {
		CalendarViewImpl type = null;
		switch (viewtype) {
			case "Daily":			type = CalendarViewImpl.TIME_GRID_DAY;					
									break;
			case "Weekly":			type = CalendarViewImpl.TIME_GRID_WEEK;
									break;
			case "Monthly":			type = CalendarViewImpl.DAY_GRID_MONTH;
									break;
			case "List-Daily":		type = CalendarViewImpl.LIST_DAY;
									break;
			case "List-Weekly":		type = CalendarViewImpl.LIST_WEEK;
									break;
			case "List-Monthly":	type = CalendarViewImpl.LIST_MONTH;
									break;	
			default:				break;
		}
		this.viewComponent.changeCalendarAppearance(type);
	}
	
	/**
	 * Prepares the Name to be displayed in the Infopanel of the Appointment
	 * @param appointment Current Appointment
	 * @return String to be displayed
	 */
	public String displayNameOfPatient(Appointment appointment) {
		if (this.getCurrentAppointment().getAppointmentType() != AppointmentType.INTERNAL) {
			Visit visit = (Visit) this.getCurrentAppointment(); //Cast to Visit, so access to functions is possible.
			return visit.getTreatedPatients().get(0).getSurname() + " " + visit.getTreatedPatients().get(0).getName();
		}
		else {
			return "";
		}
	}
	
	/**
	 * Prepares the Address to be displayed in the Infopanel of the Appointment
	 * @param appointment Current Appointment
	 * @return String to be displayed
	 */
	public String displayAdressOfPatient (Appointment appointment) {
		if (this.getCurrentAppointment().getAppointmentType() != AppointmentType.INTERNAL) {
			Visit visit = (Visit) this.getCurrentAppointment(); //Cast to Visit, so access to functions is possible.
			return visit.getTreatedPatients().get(0).getAdress().getStreet() + " " + visit.getTreatedPatients().get(0).getAdress().getNumber();
		}
		else {
			return "";
		}
	}
	
	/**
	 * Prepares the Place to be displayed in the Infopanel of the Appointment
	 * @param appointment Current Appointment
	 * @return String to be displayed
	 */
	public String displayPlaceOfPatient (Appointment appointment) {
		if (this.getCurrentAppointment().getAppointmentType() != AppointmentType.INTERNAL) {
			Visit visit = (Visit) this.getCurrentAppointment(); //Cast to Visit, so access to functions is possible.
			return visit.getTreatedPatients().get(0).getAdress().getZIP() + " " + visit.getTreatedPatients().get(0).getAdress().getCity();
		}
		else {
			return "";
		}
	}
	
	/**
	 * Formats dates to a specific pattern
	 * @param date Date to be formatted
	 * @return Formatted Date
	 */
	public String formatedDate(LocalDateTime date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_PATTERN);
		return date.format(formatter) + " Uhr";
	}

	public void createVisit(){
		/*
		Map<String, List<String>> parametersMap = new HashMap<String, List<String>>();
		List<String> sublist = Arrays.asList(currentAppointment.getAppointmentID()+"");
		parametersMap.put("AppointmentID", sublist);
		QueryParameters queryParameters = new QueryParameters(parametersMap);
		*/
		UI.getCurrent().navigate("VisitView/" + currentAppointment.getAppointmentID());
		
	}
}
