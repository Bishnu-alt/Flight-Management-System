package bcu.cmp5332.bookingsystem.commands;

import java.io.IOException;

import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public class DeleteCustomer  implements Command{
	private final int customerID;
	public DeleteCustomer(int customerID) {
        this.customerID = customerID;
    }
	 @Override
	    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException, IOException {
	        Customer customerToDelete = flightBookingSystem.getCustomerByID(this.customerID);
	        if (customerToDelete == null) {
	            throw new FlightBookingSystemException("Customer with ID " + customerID + " not found.");
	        }

	        customerToDelete.setDeleted();  // Soft delete by setting the flag
	        System.out.println("Customer #" + customerToDelete.getId() + " marked as deleted.");
	        FlightBookingSystemData.store(flightBookingSystem);
	    }

}
