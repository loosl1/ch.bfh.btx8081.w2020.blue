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
	/**
	 * 
	 * @return
	 */
	public String generatePhonenumber() {
		String phoneNumber = (countrycode + phone);
		return phoneNumber;
	};
}
