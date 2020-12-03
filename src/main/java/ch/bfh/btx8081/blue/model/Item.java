package ch.bfh.btx8081.blue.model;

/**
 * @author loosl1
 *
 * created on 26/11/2020
 */
public class Item {

    private String description;

    /**
     * Constructor which sets a description
     * @param description A description to be set
     */
    public Item(String description) {
        this.description = description;
    }

    /**
     * Empty Constructor
     */
    public Item(){
        this.description="";
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
