package ch.bfh.btx8081.blue.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author loosl1
 *
 *         created on 26/11/2020
 */

@Entity
@Table(name = "items")
public class Item {

	@Id
	@GeneratedValue
	long id; // set automatically

	private String description;

	/**
	 * empty Constructor
	 */
	public Item() {
	}

	/**
	 * Constructor which sets a description
	 * 
	 * @param description A description to be set as a String
	 */
	public Item(String description) {
		this.description = description;
	}

	/**
	 * Gets the Description
	 * 
	 * @return Returns the description as a String
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Checks if the value given is Empty or not
	 * 
	 * @param item Item in the checklist
	 * @return True if the value is empty, false if it is not as a Boolean
	 */
	protected static boolean isEmpty(Item item) {
		return item == null || item.toString().trim().isEmpty();
	}

}
