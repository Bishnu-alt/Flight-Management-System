package bcu.cmp5332.bookingsystem.model;

import java.time.LocalDate;

public class Booking {
    
    private Customer customer;
    private Flight outboundFlight;
    private Flight returnFlight;
    private double price;
    private LocalDate bookingDate;
    private boolean cancelled;
    

    public Booking(Customer customer, Flight outboundFlight) {
        this.customer = customer;
        this.outboundFlight = outboundFlight;
        this.bookingDate = outboundFlight.getDepartureDate();
        this.cancelled = false; // By default, booking is not cancelled
        this.price = outboundFlight.getPrice();
    }
    
    public Booking(Customer customer, Flight outboundFlight, Flight returnFlight) {
        this.customer = customer;
        this.outboundFlight = outboundFlight;
        this.returnFlight = returnFlight;
        this.bookingDate = outboundFlight.getDepartureDate();
        this.cancelled = false; 
        this.price = outboundFlight.getPrice();
    }
    // TODO: implementation of Getter and Setter methods
    public Customer getCustomer() {
            return customer;
    }
    public Flight getOutboundFlight() {
        return outboundFlight;
    }
    public Flight getReturnFlight() {
        return returnFlight;
    }
    public LocalDate getBookingDate() {
        return bookingDate;
    }
    public boolean isCancelled() {
        return cancelled;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public void setOutboundFlight(Flight outboundFlight) {
        this.outboundFlight = outboundFlight;
    }
    public void setReturnFlight(Flight returnFlight) {
        this.returnFlight = returnFlight;
    }
    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }
    public void cancelBooking() {
        if (!cancelled) {
            this.cancelled = true;
            double flightPrice = this.price;
            double cancellationFee = 0.3 * flightPrice;
            this.setPrice(cancellationFee);
        }
    }
    public void setPrice(double newPrice) {
        this.price = newPrice;
    }
    public int getId() {
        return customer.getId();
    }
    @Override
    public String toString() {
        return "Booking{" +
                "customer=" + customer +
                ", outboundFlight=" + outboundFlight +
                (returnFlight != null ? ", returnFlight=" + returnFlight : "") +
                ", bookingDate=" + bookingDate +
                ", cancelled=" + cancelled +
                '}';
    }
    public void setFlightNumber(String newFlightNumber) {
        this.outboundFlight.setFlightNumber(newFlightNumber);
    }
    public Double getPrice() {
        return price;
    }
    public void applyPromocode(String promocode) {
        if ("nabinOpensFlightCompany20".equals(promocode)) {
            double discountedPrice = this.price * 0.8; // Applying 20% discount
            this.price = discountedPrice;
        }
    }
}
