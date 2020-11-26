package ch.bfh.btx8081.blue.exceptions;

/**
 * @author loosl1
 * <p>
 * created on 25/11/2020
 */
public class AppointmentNotFoundException extends Throwable {

    public AppointmentNotFoundException(String errorMessage) {
        super(errorMessage);
    }

}
