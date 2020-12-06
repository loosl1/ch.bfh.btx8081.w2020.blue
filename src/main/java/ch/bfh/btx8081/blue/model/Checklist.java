package ch.bfh.btx8081.blue.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import static ch.bfh.btx8081.blue.model.Item.isEmpty;

@Entity
@Table(name = "checklists")
public class Checklist {

	@Id
	@GeneratedValue
	long id; // still set automatically

	@OneToMany
	private List<Item> items; // Changed to List because of JPA

	@OneToOne
	private PatientRecord report; // the report which is created by a HealthVisitor after a visit

	/**
	 * empty Constructor
	 */
	public Checklist() {
		this.items = new ArrayList<>();
		this.report = new PatientRecord();
	}

	/**
	 * Constructor
	 * 
	 * @param items  Arraylist
	 * @param report Patientrecord
	 */
	public Checklist(ArrayList<Item> items, PatientRecord report) {
		this.items = items;
		this.report = report;
	}

	/**
	 * Checks if all the values where loaded
	 *
	 * @return Returns a boolean if everything was loaded properly
	 */
	public boolean loadItems() {
		boolean value = false;

		for (Item item : items) {
			value = !isEmpty(item);
		}

		return value;
	}

	/**
	 * Loads a record
	 *
	 * @return Retursn a report as a PatientRecord
	 */
	private PatientRecord loadRecord() {
		return getReport();
	}

	/**
	 * Gets all the items from a checklist
	 *
	 * @return Returns an arraylist with all items
	 */
	public List<Item> getItems() {
		return items;
	}

	/**
	 * If a new item is added to the checklist, this method is called and adds a new
	 * item to the arraylist
	 *
	 * @param itemDescription The textValue which the user sets on the view
	 */
	public void setItems(String itemDescription) {
		if (itemDescription.isEmpty() || itemDescription == "") {
			throw new NullPointerException("The itemDescription value was null");
		} else {
			Item item = new Item(itemDescription);
			items.add(item);
		}
	}

	/**
	 * Gets the report
	 *
	 * @return Returns the PatientRecord Report as a PatientRecord
	 */
	public PatientRecord getReport() {
		return report;
	}

	/**
	 * Sets a report into the local report
	 *
	 * @param report the Report to be set
	 */
	public void setReport(PatientRecord report) {
		if (report.toString().isEmpty()) {
			throw new NullPointerException("The PatientRecord value was null");
		} else {
			this.report = report;
		}
	}
}
