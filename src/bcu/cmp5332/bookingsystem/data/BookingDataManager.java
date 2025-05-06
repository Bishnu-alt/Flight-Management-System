package bcu.cmp5332.bookingsystem.data;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class BookingDataManager implements DataManager {
    
    public final String RESOURCE = "./resources/data/bookings.txt";
    private static final String SEPARATOR = "::";

    @Override
    public void loadData(FlightBookingSystem fbs) throws IOException, FlightBookingSystemException {
        // TODO: implementation here
    	 try (Scanner sc = new Scanner(new File(RESOURCE))){
             int line_idx = 1;
             while(sc.hasNextLine()) {
                 String line = sc.nextLine();
                 String[] properties = line.split(SEPARATOR, -1);
                 try {
                     int id = Integer.parseInt(properties[0]);
                     int customerId = Integer.parseInt(properties[1]);
                     int flightId = Integer.parseInt(properties[2]);
                     
                     Customer customer = fbs.getCustomerByID(customerId);
                     Flight flight = fbs.getFlightByID(flightId);
                     Booking booking = new Booking(customer, flight);
                     fbs.addBooking(booking);
                     flight.addPassenger(customer);
                     customer.addBooking(booking);
                     
                 } catch (NumberFormatException | DateTimeParseException ex) {
                     throw new FlightBookingSystemException("Unable to parse booking on line " + line_idx
                             + "\nError: " + ex);
                 }
                 line_idx++;
             }
         }
    }

    @Override
    public void storeData(FlightBookingSystem fbs) throws IOException {
        // TODO: implementation here
    	try (PrintWriter out = new PrintWriter(new FileWriter(RESOURCE))) {
            for (Booking booking : fbs.getBookings() ) {
                out.print(booking.getId() + SEPARATOR);
                out.print(booking.getCustomer().getId() + SEPARATOR);
                out.print(booking.getOutboundFlight().getId() + SEPARATOR);
                out.print(booking.getBookingDate() + SEPARATOR); 
                out.println();
            }
        }
    }
    
}
