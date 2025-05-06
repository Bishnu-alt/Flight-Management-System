package bcu.cmp5332.bookingsystem.commands;

import java.io.IOException;

import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public class AddFeedback implements Command{
	 	private final int bookingID;
	    private final int customerID;
	    private final String message;
	    
	   public AddFeedback(int bookingID, int customerID, String message) {
		   this.bookingID = bookingID;
		   this.customerID = customerID;
		   this.message = message;
	   }
	   
	   @Override
	    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException, IOException {
	        flightBookingSystem.addFeedback(bookingID, customerID, message);
	        System.out.println("Feedback added for booking #" + bookingID + " by customer #" + customerID + ".");

	        FlightBookingSystemData.store(flightBookingSystem);
	    }
}
