  package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import java.util.List;


public class ListCustomer implements Command {

 
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        List<Customer> customers = flightBookingSystem.getCustomer();
        for (Customer customer : customers) {
            if (!customer.getDeleted()) {
                System.out.println(customer.getDetailsShort());
            }
        }
        System.out.println(customers.size() + " customer(s)");
    }
}
