package ch.bfh.btx8081.blue.model;

import java.time.LocalDateTime;

/**
 * @author loosl1
 * <p>
 * created on 19/11/2020
 */
public class Appointment {

    protected int appointmentID;

    protected LocalDateTime start;

    protected LocalDateTime end;

    protected String title;

    protected String info;

    public void setShortDescription() {
        //toDo
    }

    protected enum type {
        TYPE1,
        TYPE2,
        TYPE3
    }

/* toDo
    public void setColor(enum typeColor){
        if (color==)
    }
*/



}
