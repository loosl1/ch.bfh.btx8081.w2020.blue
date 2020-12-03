package ch.bfh.btx8081.blue.model;

/**
 * @author loosl1
 *
 * created on 26/11/2020
 */
public class Item {

    private final String description;

    /**
     * Constructor which sets a description
     * @param description A description to be set
     */
    public Item(String description) {
        this.description = description;
    }

    /**
     * Gets the Description
     * @return Returns the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Checks if the value given is Empty or not
     * @param item Item in the checklist
     * @return True if the value is empty, false if it is not
     */
    protected static boolean isEmpty(Item item) {
        return item == null || item.toString().trim().isEmpty();
    }




}
