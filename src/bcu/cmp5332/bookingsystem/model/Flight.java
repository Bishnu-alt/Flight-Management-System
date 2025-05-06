package bcu.cmp5332.bookingsystem.model;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Flight {
    
    private int id;
    private String flightNumber;
    private String origin;
    private String destination;
    private LocalDate departureDate;
    private int capacity; // The number of seats available on the flight
    private double price; // The price per seat of the flight
    private final Set<Customer> passengers; // The set of passengers booked on the flight
    private boolean isDeleted = false;



    public Flight(int id, String flightNumber, String origin, String destination, LocalDate departureDate, int capacity, double price, boolean isDeleted) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.capacity = capacity;
        this.price = price;
        this.isDeleted = isDeleted;
        passengers = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }
    
    public String getOrigin() {
        return origin;
    }
    
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public List<Customer> getPassengers() {
        return new ArrayList<>(passengers);
    }
    
    public int getPassengerCount() {
        return this.passengers.size();
    }
    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public String getPassengerDetails() {
        StringBuilder details = new StringBuilder();
        for (Customer passenger : passengers) {
            details.append("Name: ").append(passenger.getName()).append(", Phone: ").append(passenger.getPhone()).append("\n");
        }
        return details.toString();
    }
    public String getDetailsShort() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        return "Flight #" + id + " - " + flightNumber + " - " + origin + " to " 
                + destination + " on " + departureDate.format(dtf);
    }
    public void deleteFlight() {
        this.isDeleted = true;
    }
    
    public boolean getDeleteStatusFlight() {
        return this.isDeleted;
    }
    
    public boolean removePassenger(Customer customer) {
        return passengers.remove(customer);
    }
    public double calculatePrice(LocalDate systemDate) {
        // Calculates the number of days left for departure
        long daysLeft = ChronoUnit.DAYS.between(systemDate, departureDate);
        
        // Calculates the ratio of seats left to total capacity
        double seatsLeft = this.getCapacity();
		double capacityRatio = (double) seatsLeft  / capacity;
        
        // Calculates the base price (e.g., based on distance, demand, etc.)
        double basePrice = this.price;
        
        // Adjusts the price based on days left and capacity ratio
        double adjustedPrice = basePrice * daysLeft * capacityRatio;
        
        return adjustedPrice;
    }
    public boolean hasDeparted(LocalDate systemDate) {
        return departureDate.isBefore(systemDate);
    }

    public String getDetailsLong() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        StringBuilder details = new StringBuilder();
        details.append("Flight ID: ").append(id).append("\n");
        details.append("Flight Number: ").append(flightNumber).append("\n");
        details.append("Origin: ").append(origin).append("\n");
        details.append("Destination: ").append(destination).append("\n");
        details.append("Departure Date: ").append(departureDate.format(dtf)).append("\n");
        details.append("Passenger Details:\n");
        for (Customer passenger : passengers) {
            details.append("  - ").append(passenger.getName()).append(", Phone: ").append(passenger.getPhone()).append("\n");
        }
        return details.toString();
    }

    
    public void addPassenger(Customer passenger) throws FlightBookingSystemException {
        if (this.getPassengerCount() >= this.getCapacity()) {
            throw new FlightBookingSystemException("Flight is at full capacity. Cannot issue booking.");
        }
        passengers.add(passenger);
    }
}
