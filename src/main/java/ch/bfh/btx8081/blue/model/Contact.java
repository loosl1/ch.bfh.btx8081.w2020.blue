package ch.bfh.btx8081.blue.model;

/**
 * @author loosl1
 * <p>
 * created on 19/11/2020
 */
public class Contact {
	protected String relation;
	protected int phone;
	protected int countrycode;
	
	public String generatePhonenumber() {
		int phoneCode = (countrycode + phone);
		String phonenumber = String.valueOf(phoneCode);
		return phonenumber;
	};
}
