package bcu.cmp5332.bookingsystem.commands;

import java.io.IOException;
import java.time.LocalDate;

import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public class VIPSeatAllocation implements Command {
	 private final int customerId;
	    private final int flightId;
	    private final int bookingid;
	    private final LocalDate bookingDate;
	    
	    public VIPSeatAllocation(int customerId, int flightId, LocalDate bookingDate, int bookingid) {
	        this.customerId = customerId;
	        this.flightId = flightId;
	        this.bookingDate = bookingDate;
	        this.bookingid = bookingid;
	    }

	@Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException, IOException {
        Customer customer = flightBookingSystem.getCustomerByID(this.customerId);
        Flight flight = flightBookingSystem.getFlightByID(this.flightId);
        Booking bookingToDisplace = flightBookingSystem.getBookingByID(this.bookingid);

        if (flight.getPassengerCount() >= flight.getCapacity()) {
            // Flight is full, cancel the specified regular booking
            if (bookingToDisplace == null || bookingToDisplace.getCustomer().isVIP()) {
                throw new FlightBookingSystemException("Invalid booking ID provided or booking is not eligible for displacement.");
            }

            // Cancel the regular booking
            Command cancelBookingCommand = new CancelBooking(bookingToDisplace.getCustomer().getId(), flightId);
            cancelBookingCommand.execute(flightBookingSystem);
        }

        // Issue the VIP booking
        flightBookingSystem.issueBooking(customer, flight, bookingDate);
        System.out.println("VIP booking added successfully.");

        FlightBookingSystemData.store(flightBookingSystem);
    }

}
