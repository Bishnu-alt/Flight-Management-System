package bcu.cmp5332.bookingsystem.commands;

import java.io.IOException;

import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public class DeleteFlight implements Command {
	private final int flightNumber;
	public DeleteFlight(int flightNumber) {
        this.flightNumber = flightNumber;
    }
	@Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException, IOException {
        Flight flightToDelete = flightBookingSystem.getFlightByID(this.flightNumber);
        if (flightToDelete == null) {
            throw new FlightBookingSystemException("Flight with number " + flightNumber + " not found.");
        }

        flightBookingSystem.removeFlight(flightToDelete);
        System.out.println("Flight #" + flightToDelete.getId() + " deleted.");
        FlightBookingSystemData.store(flightBookingSystem);
    }

}
