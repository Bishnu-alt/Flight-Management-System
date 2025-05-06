package bcu.cmp5332.bookingsystem.commands;

import java.io.IOException;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public interface Command {

    public static final String HELP_MESSAGE = "Commands:\n"
    +"\n================= Flight Booking System - Help Menu =================\n"
    	    
    + "\taddcustomer                               Add a new customer\n"
    + "\tshowcustomer [customer id]                Show customer details\n"
    + "\tlistcustomers                             Print all customers\n"
    + "\teditbooking [booking id] [flight id]      Update a booking\n"
    + "\tdeletecustomer                            Delete customer\n"
    + "\tallocatevipseat                           Allocate VIP or emergency seat\n"
    + "\tgivefeedback                              Give feedback\n"
    
    + "\taddflight                                 Add a new flight\n"
    + "\tshowflight [flight id]                    Show flight details\n"
    + "\tshowflights                               Show detailed info of all flights\n"
    + "\tlistflights                               Print all flights\n"
    + "\tdeleteflight                              Delete flight\n"
    
    + "\taddbooking [customer id] [flight id]      Add a new booking\n"
    + "\tcancelbooking [customer id] [flight id]   Cancel a booking\n"
    + "\tapplypromocode                            Apply promo code for discount\n"
    
    + "\tloadgui                                   Load the GUI version of the app\n"
    + "\thelp                                      Print this help message\n"
    + "\texit                                      Exit the program\n"
    
    + "======================================================================\n";
    

    
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException, IOException;
    
}
