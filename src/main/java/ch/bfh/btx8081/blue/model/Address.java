package ch.bfh.btx8081.blue.model;

import java.time.LocalDate;

/**
 * @author loosl1
 * <p>
 * created on 19/11/2020
 */
public class Address {
	protected String street;
	protected String streetaffix;
	protected int number;
	protected String city;
	protected int ZIP;
	
	public Address (String street, int number, String city, int ZIP) {
		this.street = street;
		this.number = number;
		this.city = city;
		this.ZIP = ZIP;
	}
	
	public Address (String street, int number, String city, int ZIP, String streetaffix) {
		this.street = street;
		this.number = number;
		this.city = city;
		this.ZIP = ZIP;
		this.streetaffix = streetaffix;
	}
	
	/**
	 * 
	 * @return returns the full address formated
	 */
	//braucht es Streetaffix?
	public String formatAddress() {
		String sNumber = String.valueOf(number);
		String sZIP = String.valueOf(ZIP);
		String line1 = (street + " " + sNumber + " " + streetaffix);
		String line2 = (sZIP + " " + city);
		String address = (line1 + System.lineSeparator() + line2);
		return address;
	};
	
	/**
	 * 
	 * @param newStreet sets new street in address
	 */
	public void setStreet (String newStreet) {
		this.street = newStreet;
	};
	
	/**
	 * 
	 * @return returns street from address
	 */
	public String getStreet() {
		return street;
	};
	
	/**
	 * 
	 * @param newStreetaffix sets new streetaffix in address
	 */
	public void setStreetaffix (String newStreetaffix) {
		this.streetaffix = newStreetaffix;
	};
	
	/**
	 * 
	 * @return returns streetaffix from address
	 */
	public String getStreetaffix() {
		return streetaffix;
	};
	
	/**
	 * 
	 * @param newNumber sets news streetnumber in address
	 */
	public void setNumber (int newNumber) {
		this.number = newNumber;
	};
	
	/**
	 * 
	 * @return returns streetnumber from address
	 */
	public int getNumber() {
		return number;
	};
	
	/**
	 * 
	 * @param newCity sets new city in address
	 */
	public void setcity (String newCity) {
		this.city = newCity;
	};
	
	/**
	 * 
	 * @return gets city from address
	 */
	public String getCity() {
		return city;
	};
	
	/**
	 * 
	 * @param newZIP sets ZIP in address
	 */
	public void setZIP (int newZIP) {
		this.ZIP = newZIP;
	};
	
	/**
	 * 
	 * @return gets ZIP from address
	 */
	public int getZIP() {
		return ZIP;
	};
}
