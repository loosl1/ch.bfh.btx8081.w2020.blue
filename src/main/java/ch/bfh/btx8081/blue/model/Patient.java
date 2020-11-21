package ch.bfh.btx8081.blue.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author loosl1
 * <p>
 * created on 19/11/2020
 */
public class Patient {
	protected ArrayList<Contact> contacts = new ArrayList<>();
	protected Calendar calendar;
	protected Checklist dailyGoals;
	protected String infoAdmin;

    HashMap<Integer, PatientRecord> myRecords = new HashMap<>();



}
