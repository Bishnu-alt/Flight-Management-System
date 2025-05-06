package bcu.cmp5332.bookingsystem.commands;

import java.io.IOException;
import java.time.LocalDate;

import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public class AddBooking implements Command{
	 	private final int customerId;
	    private final int outboundFlightId;
	    private final LocalDate bookingDate;
	    
	    public AddBooking(int customerId, int outboundFlightId, LocalDate bookingDate) {
	        this.customerId = customerId;
	        this.outboundFlightId = outboundFlightId;
	        this.bookingDate = bookingDate;
	    }
	    @Override
	    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException, IOException {
	        Customer customer = flightBookingSystem.getCustomerByID(this.customerId);
	        Flight outboundFlight = flightBookingSystem.getFlightByID(this.outboundFlightId);
	        LocalDate bookingDate = this.bookingDate;

	        if (outboundFlight.getPassengerCount() >= outboundFlight.getCapacity()) {
	            throw new FlightBookingSystemException("Flight is at full capacity. Cannot issue booking.");
	        }

	        if (bookingDate.isBefore(LocalDate.now())) {
	            throw new FlightBookingSystemException("Booking date must be in the future.");
	        }

	        flightBookingSystem.issueBooking(customer, outboundFlight, bookingDate);
	        System.out.println("Booking added successfully.");

	        FlightBookingSystemData.store(flightBookingSystem);
	    }
}
