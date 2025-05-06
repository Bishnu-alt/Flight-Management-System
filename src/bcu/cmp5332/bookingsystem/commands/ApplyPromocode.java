package bcu.cmp5332.bookingsystem.commands;

import java.io.IOException;

import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public class ApplyPromocode implements Command {
	private final int bookingID;
    private final String promocode;
    
    public ApplyPromocode(int bookingID, String promocode) {
        this.bookingID = bookingID;
        this.promocode = promocode;
    }
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException, IOException {
        Booking booking = flightBookingSystem.getBookingByID(bookingID);
        if (booking == null) {
            throw new FlightBookingSystemException("Booking with ID " + bookingID + " does not exist.");
        }

        booking.applyPromocode(promocode);
        System.out.println("Promocode '" + promocode + "' applied to booking #" + bookingID);

        FlightBookingSystemData.store(flightBookingSystem);
    }

}
