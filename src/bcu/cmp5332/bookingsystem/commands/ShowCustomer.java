package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public class ShowCustomer implements Command {
   
   private int id;
   
   /**
    * Constructs a ShowCustomer command object with the specified customer ID.
    * 
    * @param id The ID of the customer whose details are to be displayed
    */
   public ShowCustomer(int id) {
       this.id = id;
   }
   
   @Override
   public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
       Customer customer = flightBookingSystem.getCustomerByID(id);
       customer.showDetails();
   }
}
