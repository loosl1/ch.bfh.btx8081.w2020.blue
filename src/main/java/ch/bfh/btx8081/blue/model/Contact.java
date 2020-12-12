package ch.bfh.btx8081.blue.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author loosl1
 *         <p>
 *         created on 19/11/2020
 */
@Entity
@Table(name = "contacts")
public class Contact extends Person {
	protected String relation;
	protected String phone;
	protected String countrycode;
	@Transient
	protected static int contactIdCounter = 0;
	protected int contactId;

	/**
	 * empty Constructor
	 */
	public Contact() {
		super();
		this.contactId = contactIdCounter++;
	}

	/* Constructor */
	public Contact(String relation, String phone, String countrycode) {
		this.relation = relation;
		this.phone = phone;
		this.countrycode = countrycode;
		this.contactId = contactIdCounter++;
	}

	/**
	 *
	 * @return returns phonenumber including countrycode
	 */
	public String generatePhonenumber() {
		String phoneNumber = (countrycode + phone);
		return phoneNumber;
	};

	/* getters and setters */

	/**
	 * 
	 * @return get a contact's relation to patient
	 */
	public String getRelation() {
		return relation;
	};

	/**
	 * 
	 * @param newRelation sets a contact's relation to patient
	 */
	public void setRelation(String newRelation) {
		this.relation = newRelation;
	};

	/**
	 * 
	 * @return gets a contact's phonenumber
	 */
	public String getPhone() {
		return phone;
	};

	/**
	 *
	 * @param newPhone sets phonenumber for contact
	 */
	public void setPhone(String newPhone) {
		this.phone = newPhone;
	};

	/**
	 * 
	 * @return get a contacts countrycode
	 */
	public String getCountrycode() {
		return countrycode;
	};

	/**
	 * 
	 * @param newCountrycode sets a countrycode for contact
	 */
	public void setCountrycode(String newCountrycode) {
		this.countrycode = newCountrycode;
	};

	/**
	 * 
	 * @return gets a contact's ID
	 */
	public int getContactId() {
		return contactId;
	};

	/**
	 * 
	 * @param newContactId set a contact's ID
	 */
	public void setContactId(int newContactId) {
		this.contactId = newContactId;
	};

}
