package bcu.cmp5332.bookingsystem.model;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    
    private int id;
    private String name;
    private String phone;
    private String email;
    private final List<Booking> bookings = new ArrayList<>();
    private boolean isDeleted = false;
    private boolean isVIP = false;
	
    
    public Customer (int id, String name, String phone, String email, boolean isDeleted, boolean isVIP) {
    	this.id = id;
    	this.name = name;
    	this.phone = phone;
    	this.email = email;
    	this.isDeleted = isDeleted; 
    	this.isVIP = isVIP;
    }
    
    public int getId() {
    	return id;
    }
    
    public void setId(int id) {
    	this.id = id;
    }
    
    public String getName() {
    	return name;
    }
    
    public void setName(String name) {
    	this.name = name;
    }
    
    public String getPhone() {
    	return phone;
    }
    
    public void setPhone(String phone) {
    	this.phone = phone;
    }
    
    public String getEmail() {
    	return email;
    }
    
    public void setEmail(String email) {
    	this.email = email;
    }
    
    public boolean getDeleted() {
    	return this.isDeleted;
    }
    
    public void setDeleted() {
    	 isDeleted = true;
    }
    
    public boolean isVIP() {
    	return this.isVIP;
    }
    
    public void setVIP() {
    	this.isVIP = true;
    }
    
    public List<Booking> getBookings() {
        return new ArrayList<>(this.bookings);
    }
    
    public void addBooking(Booking booking) {
    	// TODO: implementation here
    	bookings.add(booking);
    }
    
    public String getDetailsShort() {
    	return "Customer #" + this.id + " - " + this.name + " - " + this.phone;
    }
    // TODO: implement constructor here
    
    // TODO: implementation of Getter and Setter methods
    public String getShowDetails() {
        StringBuilder details = new StringBuilder();
        details.append("Customer ID: ").append(id).append("\n");
        details.append("Name: ").append(name).append("\n");
        details.append("Phone Number: ").append(phone).append("\n");
        details.append("\nBookings:\n");
        for (Booking booking : bookings) {
            details.append("  Booking ID: ").append(booking.getId()).append("\n");
            details.append("  Outbound Flight: ").append(booking.getOutboundFlight().getFlightNumber())
                    .append(" From: ").append(booking.getOutboundFlight().getOrigin())
                    .append(" To: ").append(booking.getOutboundFlight().getDestination())
                    .append(" Date: ").append(booking.getOutboundFlight().getDepartureDate())
                    .append(" Price: ").append(booking.getOutboundFlight().getPrice()).append("\n");
            if (booking.getReturnFlight() != null) {
                details.append("  Return Flight: ").append(booking.getReturnFlight().getFlightNumber())
                        .append(" From: ").append(booking.getReturnFlight().getOrigin())
                        .append(" To: ").append(booking.getReturnFlight().getDestination())
                        .append(" Date: ").append(booking.getReturnFlight().getDepartureDate())
                        .append(" Price: ").append(booking.getReturnFlight().getPrice()).append("\n");
            }
        }
        return details.toString();
    }
    public void showDetails() {
        System.out.println("Customer ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Phone Number: " + phone);

        System.out.println("Bookings:");
        for (Booking booking : bookings) {
            System.out.println("  Booking ID: " + booking.getId());
            System.out.println("  Outbound Flight: " + booking.getOutboundFlight().getFlightNumber() +
                    " From: " + booking.getOutboundFlight().getOrigin() +
                    " To: " + booking.getOutboundFlight().getDestination() +
                    " Date: " + booking.getOutboundFlight().getDepartureDate() +
                    " Price: " + booking.getOutboundFlight().getPrice());
            if (booking.getReturnFlight() != null) {
                System.out.println("  Return Flight: " + booking.getReturnFlight().getFlightNumber() +
                        " From: " + booking.getReturnFlight().getOrigin() +
                        " To: " + booking.getReturnFlight().getDestination() +
                        " Date: " + booking.getReturnFlight().getDepartureDate() +
                        " Price: " + booking.getReturnFlight().getPrice());
            }
        }
    }

    
}

