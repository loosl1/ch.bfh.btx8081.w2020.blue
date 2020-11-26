package ch.bfh.btx8081.blue.model;

import java.time.LocalDate;

public class HealthVisitor extends Person{
	protected String location;
	protected Calendar calendar;
	protected static int	healthVisitorIdCounter = 0;
	protected int	healthVisitorId;
	protected String password;
	protected String username;
	
	/*Constructor*/
public HealthVisitor (String location, String password, String username, 
		String name, String surname, String namesuffix, LocalDate birthday, Address address) {
		super(name, surname, namesuffix, birthday);
		this.location = location;
		this.calendar = new Calendar();
		this.password = password;
		this.username = username;
		this.healthVisitorId = healthVisitorIdCounter++;
	}
	
	/* getters and setters*/

/**
 * 
 * @return get healthVisitor's location
 */
	public String getLocation() {
		return location;
	};
	
	/**
	 * 
	 * @param newLocation set healthvisitor's location
	 */
	public void setLocation (String newLocation) {
		this.location = newLocation;
	};
	
	/**
	 * 
	 * @return get healthVisitor's calendar
	 */
	public Calendar getCalendar() {
		return calendar;
	};
	
	/**
	 * 
	 * @param newCalendar set new calendar for healthVisitor
	 */
	public void setCalendar (Calendar newCalendar) {
		this.calendar = newCalendar;
	};
	
	/**
	 * 
	 * @return gets healthvisitor's ID
	 */
	public int getHealthVisitorId() {
		return healthVisitorId;
	};
	
	/**
	 * 
	 * @param newHealthVisitorId sets a healthvisitor's ID
	 */
	public void setHealthVisitorId (int newHealthVisitorId) {
		this.healthVisitorId = newHealthVisitorId;
	};
	
	/**
	 * 
	 * @return get healthVisitor's password
	 */
	public String getPassword() {
		return password;
	};
	
	/**
	 * 
	 * @param newPassword sets a password for healthVisitor
	 */
	public void setPassword (String newPassword) {
		this.password = newPassword;
	};
	
	/**
	 * 
	 * @return gets a healthVisitor's username
	 */
	public String getUsername() {
		return username;
	};
	
	/**
	 * 
	 * @param newUsername sets a username for healthVisitor
	 */
	public void setUsername (String newUsername) {
		this.username = newUsername;
	};

	/**
	 * 
	 * @return gets HealthVisitor
	 */
    public String getHealthVisitor() {

        return null;
    }
}
