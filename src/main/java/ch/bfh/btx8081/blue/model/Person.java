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
	protected Address adress;
	
	public String getFullname() {
		//if suffix insert suffix else 
		String fullname = this.name + " " + this.surname;
		return fullname;
	};
	public int getAge() {
		LocalDate today = LocalDate.now();
		Period diff = Period.between(birthday, today);
		int age = diff.getYears();
		return age;
	};
	
}
