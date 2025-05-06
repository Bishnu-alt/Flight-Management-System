package bcu.cmp5332.bookingsystem.model;

public class Feedback {
	private static int lastFeedbackID = 0;

    private final int id;           // The unique identifier for the feedback
    private final int bookingID;    // The ID of the booking associated with the feedback
    private final int customerID;   // The ID of the customer providing the feedback
    private final String message; 
    
    public Feedback(int bookingID, int customerID, String message) {
        this.id = ++lastFeedbackID;
        this.bookingID = bookingID;
        this.customerID = customerID;
        this.message = message;
    }
    public int getId() {
        return id;
    }
    public int getBookingID() {
        return bookingID;
    }
    public int getCustomerID() {
        return customerID;
    }
    public String getMessage() {
        return message;
    }

}
