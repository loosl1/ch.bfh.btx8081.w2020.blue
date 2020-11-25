package ch.bfh.btx8081.blue.model;

public class HealthVisitor extends Person{
	protected String location;
	protected Calendar calendar;
	protected int	healthVisitorId;
	protected String password;
	protected String username;
	
	public String getLocation() {
		return location;
	};
	
	public void setName (String newLocation) {
		this.location = newLocation;
	};
	
	public Calendar getCalendar() {
		return calendar;
	};
	
	public void setCalendar (Calendar newCalendar) {
		this.calendar = newCalendar;
	};
	
	public int getHealthVisitorId() {
		return healthVisitorId;
	};
	
	public void setHealthVisitorId (int newHealthVisitorId) {
		this.healthVisitorId = newHealthVisitorId;
	};
	
	public String getPassword() {
		return password;
	};
	
	public void setPassword (String newPassword) {
		this.password = newPassword;
	};
	
	public String getUsername() {
		return username;
	};
	
	public void setUsername (String newUsername) {
		this.username = newUsername;
	};

    public String getHealthVisitor() {

        return null;
    }
}
