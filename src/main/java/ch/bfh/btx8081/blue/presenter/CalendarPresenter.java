package ch.bfh.btx8081.blue.presenter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.vaadin.stefan.fullcalendar.BusinessHours;
import org.vaadin.stefan.fullcalendar.CalendarViewImpl;
import org.vaadin.stefan.fullcalendar.Entry;
import org.vaadin.stefan.fullcalendar.FullCalendar;

import ch.bfh.btx8081.blue.model.Appointment;
import ch.bfh.btx8081.blue.view.CalendarView;
  
public class CalendarPresenter { 
	private CalendarView viewComponent;
	private ArrayList<Appointment> appointments;
	private Appointment currentAppointment;
	private LocalDate selectedDate;
	
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
	}
	
	/**
	 * Sets configuration for a FullCalendar Object
	 * @param calendarToSetup FullCalendar-Object to be configured.
	 */
	public void setupCalendarConfiguration(FullCalendar calendar) {
		calendar.changeView(CalendarViewImpl.TIME_GRID_DAY);
		/*calendar.setBusinessHours(new BusinessHours(LocalTime.of(WORKHOURS_START_HOUR, WORKHOURS_START_MINUTE), 
													LocalTime.of(WORKHOURS_END_HOUR, WORKHOURS_END_MINUTE),
													BusinessHours.DEFAULT_BUSINESS_WEEK*/
		calendar.today();
		calendar.setWeekNumbersVisible(false);
		calendar.setWidth("70%");
	}
	
	public void updateInfoPanel() {
		
	}
	
	public ArrayList<Entry> getAppointmentsForCalendar() {
		ArrayList<Entry> entries = new ArrayList<Entry>();
		Entry entry1 = new Entry();
		Entry entry2 = new Entry();
		Entry entry3 = new Entry();
	    entry1.setTitle("Frau Brönnimann");
	    entry1.setStart(LocalDate.now().atTime(10, 0));
	    entry1.setEnd(entry1.getStart().plusHours(2));
	    entry1.setColor(APPOINTMENT_COLOR_LARGE);
	    
	    entry2.setTitle("Familie Yu");
	    entry2.setStart(LocalDate.now().atTime(9, 0));
	    entry2.setEnd(entry2.getStart().plusHours(1));
	    entry2.setColor(APPOINTMENT_COLOR_SMALL);
	    
	    entry3.setTitle("Lunch");
	    entry3.setStart(LocalDate.now().atTime(13, 0));
	    entry3.setEnd(entry3.getStart().plusHours(1));
	    entry3.setColor(APPOINTMENT_COLOR_ADMIN);
	    
	    entries.add(entry1);
	    entries.add(entry2);
	    entries.add(entry3);
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
	}
	
	/**
	 * Returns the currently Selected Appointment
	 * @return Currently selected Appointment.
	 */
	public Appointment getCurrentAppointment () {
		return this.currentAppointment;
	}
}
