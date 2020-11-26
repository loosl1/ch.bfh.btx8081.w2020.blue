package ch.bfh.btx8081.blue.presenter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.vaadin.stefan.fullcalendar.BusinessHours;
import org.vaadin.stefan.fullcalendar.CalendarViewImpl;
import org.vaadin.stefan.fullcalendar.Entry;
import org.vaadin.stefan.fullcalendar.FullCalendar;

import ch.bfh.btx8081.blue.model.*;
import ch.bfh.btx8081.blue.view.CalendarView;
  
public class CalendarPresenter { 
	private CalendarView viewComponent;
	private Appointment currentAppointment;
	private LocalDate selectedDate;
	private HealthVisitor currentUser;
	
	//Configurations
	private static final int WORKHOURS_START_HOUR = 6;
	private static final int WORKHOURS_START_MINUTE = 0;
	private static final int WORKHOURS_END_HOUR = 18;
	private static final int WORKHOURS_END_MINUTE = 0;
	private static final String APPOINTMENT_COLOR_LARGE = "#ff5969";
	private static final String APPOINTMENT_COLOR_SMALL = "#fec630";
	private static final String APPOINTMENT_COLOR_ADMIN = "#52cbbe";
	
	
	/**
	 * Constructor
	 */
	public CalendarPresenter (CalendarView viewComponent) {
		
		this.viewComponent = viewComponent;
		selectedDate = LocalDate.now();
		// manual created data
		currentUser = new HealthVisitor ("Bern", "password", "naj", "Jung", "Natalie", "", LocalDate.parse("1990-05-23") , null);
		currentUser.setCalendar(new Calendar ());
		Appointment appointment1 = new Appointment(LocalDate.parse("2020-11-26").atTime(10, 0),LocalDate.parse("2020-11-26").atTime(11, 0),"Besuch bei Broenimanns","Und hier die Infos");
		currentUser.getCalendar().addAppointment(appointment1);
		Appointment appointment2 = new Appointment(LocalDate.parse("2020-11-26").atTime(11, 15),LocalDate.parse("2020-11-26").atTime(11, 30),"Besuch bei Broenimanns","Und hier die Infos");
		currentUser.getCalendar().addAppointment(appointment2);
		Appointment appointment3 = new Appointment(LocalDate.parse("2020-11-27").atTime(8, 0),LocalDate.parse("2020-11-27").atTime(9, 0),"Besuch bei Broenimanns","Und hier die Infos");
		currentUser.getCalendar().addAppointment(appointment3);
		Appointment appointment4 = new Appointment(LocalDate.parse("2020-11-27").atTime(9, 0),LocalDate.parse("2020-11-27").atTime(9, 30),"Besuch bei Broenimanns","Und hier die Infos");
		currentUser.getCalendar().addAppointment(appointment4);
	}
	
	/**
	 * Sets configuration for a FullCalendar Object
	 * @param calendarToSetup FullCalendar-Object to be configured.
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
	
	public void setSelectedDate(LocalDate selectedDate) {
		this.selectedDate = selectedDate;
		this.viewComponent.update();
	}
	
	public ArrayList<Entry> generateEntriesForCalendar() {
		ArrayList<Entry> entries = new ArrayList<Entry>();
		this.currentUser.getCalendar().getAppointments().forEach(appointment -> {
			Entry entry = new Entry();
		    entry.setTitle(appointment.getTitle());
		    entry.setStart(appointment.getStart());
		    entry.setEnd(appointment.getEnd());
		    entry.setColor(APPOINTMENT_COLOR_LARGE);
		    entries.add(entry);
		});
	    return entries;
	}

	/**
	 * Gets next Appointment to the currently selected one.
	 * @return Next Appointment from the currently selected Appointment.
	 */
	public Appointment getNextAppointment() {
		return this.currentAppointment;
	}
	
	/**
	 * Gets previous Appointment to the currently selected one.
	 * @return Previous Appointment from the currently selected Appointment.
	 */
	public Appointment getPreviousAppointment() {
		return this.currentAppointment;
	}
	
	/**
	 * Changes the current selected Appointment.
	 * @param appointment New Appointment to be tracked.
	 */
	public void setCurrentAppointment (Appointment appointment) {
		this.currentAppointment = appointment;
		this.viewComponent.update();
	}
	
	/**
	 * Returns the currently Selected Appointment
	 * @return Currently selected Appointment.
	 */
	public Appointment getCurrentAppointment () {
		return this.currentAppointment;
	}
}
