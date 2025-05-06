package bcu.cmp5332.bookingsystem.model;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import java.time.LocalDate;
import java.util.*;

public class FlightBookingSystem {
    
    private final LocalDate systemDate = LocalDate.now();
    
    private final Map<Integer, Customer> customers = new TreeMap<>();
    private final Map<Integer, Flight> flights = new TreeMap<>();
    private final List<Booking> bookings = new ArrayList<>(); // Collection of bookings in the system
    private final List<Feedback> feedbacks = new ArrayList<>(); 

    public LocalDate getSystemDate() {
        return systemDate;
    }
    public Customer[] getCustomers() {
        return customers.values().toArray(new Customer[0]);
    }
    public void addBooking(Booking booking) {
    	bookings.add(booking);
    }
    
    public List<Customer> getCustomer() {
        List<Customer> out = new ArrayList<>(customers.values());
        return Collections.unmodifiableList(out);
    }


    public List<Flight> getFlights() {
        LocalDate systemDate = LocalDate.now();
        List<Flight> futureFlights = new ArrayList<>();
        for (Flight flight : flights.values()) {
            if (!flight.getDepartureDate().isBefore(systemDate) && !flight.hasDeparted(systemDate)) {
                futureFlights.add(flight);
            }
        }
        return Collections.unmodifiableList(futureFlights);
    }

    public Flight getFlightByID(int id) throws FlightBookingSystemException {
        if (!flights.containsKey(id)) {
            throw new FlightBookingSystemException("There is no flight with that ID.");
        }
        return flights.get(id);
    }

    public Customer getCustomerByID(int id) throws FlightBookingSystemException {
        // TODO: implementation here
        if (!customers.containsKey(id)) {
        	throw new FlightBookingSystemException("There is no customer with that ID: " + id);
        }
        return customers.get(id);
    }
    
    public List<Flight> getFutureFlights(LocalDate systemDate) {
        List<Flight> futureFlights = new ArrayList<>();
        for (Flight flight : flights.values()) {
            if (!flight.getDepartureDate().isBefore(systemDate) && !flight.hasDeparted(systemDate)) {
                futureFlights.add(flight);
            }
        }
        return Collections.unmodifiableList(futureFlights);
    }
    
    public Booking[] getBookings() {
        List<Booking> bookings = new ArrayList<>();
        for (Customer customer : customers.values()) {
            bookings.addAll(customer.getBookings());
        }
        return bookings.toArray(new Booking[0]);
    }
    
    public void cancelBooking(int customerId, int flightId) throws FlightBookingSystemException {
        Customer customer = this.getCustomerByID(customerId);
        Flight flight = this.getFlightByID(flightId);
        flight.removePassenger(customer);
    }
    public void removeFlight(Flight flightToDelete) {
        flightToDelete.deleteFlight();
    }
    
    
    public void issueBooking(Customer customer, Flight flight, LocalDate bookingDate) throws FlightBookingSystemException {
        if (!customers.containsValue(customer)) {
            throw new FlightBookingSystemException("Customer does not exist in the system.");
        }
        if (!flights.containsValue(flight)) {
            throw new FlightBookingSystemException("Flight does not exist in the system.");
        }
        if (flight.getPassengerCount() >= flight.getCapacity()) {
            throw new FlightBookingSystemException("Flight is at full capacity. Cannot issue booking.");
        }
        Booking booking = new Booking(customer, flight);
        customer.addBooking(booking);
        flight.addPassenger(customer); // Ensure the flight tracks its passengers
    }
    
    public Booking getBookingByID(int bookingID) throws FlightBookingSystemException {
        for (Booking booking : bookings) {
            if (booking.getId() == bookingID) {
                return booking;
            }
        }
        throw new FlightBookingSystemException("No booking found with ID: " + bookingID);
    }
    
    public Customer getCustomerByName(String customerName) throws FlightBookingSystemException {
        List<Customer> matchingCustomers = new ArrayList<>();
        for (Customer customer : customers.values()) {
            if (customer.getName().equalsIgnoreCase(customerName)) {
                matchingCustomers.add(customer);
            }
        }
        if (matchingCustomers.isEmpty()) {
            throw new FlightBookingSystemException("No customers found with the name: " + customerName);
        } else if (matchingCustomers.size() > 1) {
            System.out.println("Multiple customers found with the name: " + customerName);
            for (Customer customer : matchingCustomers) {
                System.out.println("Customer ID: " + customer.getId() + ", Name: " + customer.getName() + ", Email: " + customer.getEmail());
            }
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the email to select your customer: ");
            String selectedEmail = scanner.nextLine();
            for (Customer customer : matchingCustomers) {
                if (customer.getEmail().equalsIgnoreCase(selectedEmail)) {
                    return customer;
                }
            }
            throw new FlightBookingSystemException("No customer found with the email: " + selectedEmail);
        } else {
            return matchingCustomers.get(0);
        }
    }
    public List<Feedback> getFeedbacks() {
        return Collections.unmodifiableList(feedbacks);
    }

	public void addFeedback(Feedback feedback) {
	        feedbacks.add(feedback);
		
	}
    
    public void addFeedback(int bookingID, int customerID, String message) {
        Feedback feedback = new Feedback(bookingID, customerID, message);
        feedbacks.add(feedback);
    }

    public void addFlight(Flight flight) throws FlightBookingSystemException {
        if (flights.containsKey(flight.getId())) {
            throw new IllegalArgumentException("Duplicate flight ID.");
        }
        for (Flight existing : flights.values()) {
            if (existing.getFlightNumber().equals(flight.getFlightNumber()) 
                && existing.getDepartureDate().isEqual(flight.getDepartureDate())) {
                throw new FlightBookingSystemException("There is a flight with same "
                        + "number and departure date in the system");
            }
        }
        flights.put(flight.getId(), flight);
    }

    public void addCustomer(Customer customer) throws FlightBookingSystemException {
        if (customers.containsKey(customer.getId())) {
            throw new IllegalArgumentException("Duplicate customer ID.");
        }
        customers.put(customer.getId(), customer);
    }
}
