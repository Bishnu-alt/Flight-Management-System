package bcu.cmp5332.bookingsystem.commands;
import java.io.IOException;
import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;


public class AddCustomer implements Command {

    private final String name;
    private final String phone;
    private final String email;

    public AddCustomer(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException, IOException {
    	int maxId = 0;
    	for (Customer customer : flightBookingSystem.getCustomer()) {
    		if (customer.getId() > maxId) {
    			maxId = customer.getId();
    		}
    	}
    	
    	Customer customer = new Customer(++maxId, name, phone, email, false, false);
    	flightBookingSystem.addCustomer(customer);
    	System.out.println("Customer #" + customer.getId() + "added.");

		FlightBookingSystemData.store(flightBookingSystem);
			
			
    }
}
