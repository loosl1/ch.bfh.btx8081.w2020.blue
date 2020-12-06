package ch.bfh.btx8081.blue.model;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * @author loosl1
 *         <p>
 *         created on 19/11/2020
 */
@Entity
@Table(name = "persons")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Person {
	@Id
	@GeneratedValue
	long id; // still set automatically
	protected String name;
	protected String surname;
	protected String namesuffix;
	protected LocalDate birthday;
	@Embedded
	protected Address address;

	/**
	 * empty Constructor
	 */
	public Person() {
	}

	/* Constructor */
	// TODO Address Strings
	public Person(String name, String surname, String namesuffix, LocalDate birthday) {
		this.name = name;
		this.surname = surname;
		this.namesuffix = namesuffix;
		this.birthday = birthday;
		this.address = new Address();
	}

	/**
	 * 
	 * @return the full name of person including suffix, name and surname
	 */
	public String getFullname() {

		if (namesuffix != null) {
			String fullname = this.namesuffix + " " + this.name + " " + this.surname;
			return fullname;
		}

		else {
			String fullname = this.name + " " + this.surname;
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

	/* getters and setters */

	/**
	 * 
	 * @return name of person
	 */
	public String getName() {
		return name;
	};

	/**
	 * 
	 * @param newName sets new name for person
	 */
	public void setName(String newName) {
		this.name = newName;
	};

	/**
	 * 
	 * @return get person's surname
	 */
	public String getSurname() {
		return surname;
	};

	/**
	 * 
	 * @param newSurname sets new Surname for person
	 */
	public void setSurname(String newSurname) {
		this.surname = newSurname;
	};

	/**
	 * 
	 * @return get person's namesuffix
	 */
	public String getNamesuffix() {
		return namesuffix;
	};

	/**
	 * 
	 * @param newNamesuffix sets new namesuffix for patient
	 */
	public void setNamesuffix(String newNamesuffix) {
		this.namesuffix = newNamesuffix;
	};

	/**
	 * 
	 * @return gets person's birthday
	 */
	public LocalDate getBirthday() {
		return birthday;
	};

	/**
	 * 
	 * @param newBirthday sets persons birthday
	 */
	public void setBirthday(LocalDate newBirthday) {
		this.birthday = newBirthday;
	};

	/**
	 * 
	 * @return get a person's address
	 */
	public Address getAdress() {
		return address;
	};

	/**
	 * 
	 * @param newAdress set a patient's address
	 */
	public void setAdress(Address newAdress) {
		this.address = newAdress;
	};

}