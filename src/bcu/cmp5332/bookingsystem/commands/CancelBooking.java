package bcu.cmp5332.bookingsystem.commands;

import java.io.IOException;

import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public class CancelBooking implements Command {
	 	private final int customerId;
	    private final int flightId;
	    
	    public CancelBooking(int customerId, int flightId) {
	        this.customerId = customerId;
	        this.flightId = flightId;
	    }
	    @Override
	    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException, IOException {
	        flightBookingSystem.cancelBooking(customerId, flightId);
	        System.out.println("Booking has been cancelled successfully.");
	        FlightBookingSystemData.store(flightBookingSystem);
	    }

}
