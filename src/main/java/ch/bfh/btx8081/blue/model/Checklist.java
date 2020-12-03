package ch.bfh.btx8081.blue.model;


import java.util.ArrayList;

import static ch.bfh.btx8081.blue.model.Item.isEmpty;

public class Checklist {

    private ArrayList<Item> items;

    private PatientRecord report; //the report which is created by a HealthVisitor after a visit

    public Checklist(ArrayList<Item> items, PatientRecord report) {
        this.items = items;
        this.report = report;
    }

    public Checklist() {
        this.items = new ArrayList<>();
        this.report = new PatientRecord();
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
     * @return calls the getReport-Method which returns the report of the object
     */
    private PatientRecord loadRecord() {
        return getReport();
    }

    /**
     * Gets all the items from a checklist
     *
     * @return Returns an arraylist with all items
     */
    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * If a new item is added to the checklist, this method is called and adds a new item to the arraylist
     *
     * @param itemDescription The textValue which the user sets on the view
     */
    public void setItems(String itemDescription) {
        try {
            Item item = new Item(itemDescription);
            items.add(item);
        } catch (NullPointerException e) {
            System.out.println("The itemDescription value was null" + e.getMessage());
        }
    }

    /**
     * Gets the report
     *
     * @return Returns the PatientRecord Report
     */
    public PatientRecord getReport() {
        return report;
    }

    /**
     * Sets a report into the local report
     *
     * @param report
     */
    public void setReport(PatientRecord report) {
        this.report = report;
    }
}
