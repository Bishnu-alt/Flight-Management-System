package bcu.cmp5332.bookingsystem.commands;

import java.time.LocalDate;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public class UpdateBooking implements Command {

    private final int bookingID;
    private final String newFlightNumber;
    private final LocalDate newBookDate;
    
    public UpdateBooking(int bookingID, String newFlightNumber, LocalDate newBookDate) {
        this.bookingID = bookingID;
        this.newFlightNumber = newFlightNumber;
        this.newBookDate = newBookDate;
    }
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        Booking booking = flightBookingSystem.getBookingByID(bookingID);
        if (booking == null) {
            throw new FlightBookingSystemException("Booking not found.");
        }

        booking.setFlightNumber(newFlightNumber);
        booking.setBookingDate(newBookDate);
        System.out.println("Updated Successfully!");
    }
}
