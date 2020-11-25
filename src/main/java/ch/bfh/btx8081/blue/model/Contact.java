package ch.bfh.btx8081.blue.model;

/**
 * @author loosl1
 * <p>
 * created on 19/11/2020
 */
public class Contact {
	protected String relation;
	protected String phone;
	protected String countrycode;
	protected int contactId;
	
	public String getRelation() {
		return relation;
	};
	
	public void setRelation (String newRelation) {
		this.relation = newRelation;
	};
	
	public String getPhone() {
		return phone;
	};
	
	public void setPhone (String newPhone) {
		this.phone = newPhone;
	};
	
	public String getCountrycode() {
		return countrycode;
	};
	
	public void setCountrycode (String newCountrycode) {
		this.countrycode = newCountrycode;
	};
	
	public int getContactId() {
		return contactId;
	};
	
	public void setContactId (int newContactId) {
		this.contactId = newContactId;
	};
	
	/**
	 *
	 * @return
	 */
	public String generatePhonenumber() {
		String phoneNumber = (countrycode + phone);
		return phoneNumber;
	};
}
