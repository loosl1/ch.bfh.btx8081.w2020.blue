package ch.bfh.btx8081.blue.model;

import java.time.LocalDate;
import java.time.Period;

/**
 * @author loosl1
 * <p>
 * created on 19/11/2020
 */
public class Person {
	protected String name;
	protected String surname;
	protected String namesuffix;
	protected LocalDate birthday;
	protected Address address;
	
	public String getName() {
		return name;
	};
	
	public void setName (String newName) {
		this.name = newName;
	};
	
	public String getSurname() {
		return surname;
	};
	
	public void setSurname (String newSurname) {
		this.surname = newSurname;
	};
	
	public String getNamesuffix() {
		return namesuffix;
	};
	
	public void setNamesuffix (String newNamesuffix) {
		this.namesuffix = newNamesuffix;
	};
	
	public LocalDate getBirthday() {
		return birthday;
	};
	
	public void setBirthday (LocalDate newBirthday) {
		this.birthday = newBirthday;
	};
	
	public Address getAdress() {
		return address;
	};
	
	public void setAdress (Address newAdress) {
		this.address = newAdress;
	};
	
	/**
	 * 
	 * @return the full name of person including suffix, name and surname
	 */
	public String getFullname() {
		
		if (namesuffix != null) {
			String fullname = this.namesuffix + " " + this.name + " " + this.surname;
			return fullname;
		}
		
		else { String fullname = this.name + " " + this.surname; 
			return fullname;
		}
	};
	
	/**
	 * 
	 * @return the age of person in years
	 */
	public int getAge() {
		LocalDate today = LocalDate.now();
		Period diff = Period.between(birthday, today);
		int age = diff.getYears();
		return age;
	};
	
}
