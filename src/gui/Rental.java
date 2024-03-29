package gui;

import java.time.LocalDate;

	// needed for rental database 
public class Rental {
    private int itemId;
    private String username;
    private LocalDate rentalDate;
    private LocalDate dueDate;

    public Rental(int itemId, String username, LocalDate rentalDate, LocalDate dueDate) {
        this.itemId = itemId;
        this.username = username;
        this.rentalDate = rentalDate;
        this.dueDate = dueDate;
    }

    // Getters
    public int getItemId() { return itemId; }
    public String getUsername() { return username; }
    public LocalDate getRentalDate() { return rentalDate; }
    public LocalDate getDueDate() { return dueDate; }

    @Override
    public String toString() {
        return "Rental: Item ID " + itemId + ", Username: " + username;
    }

	public String toCSVString() {
		  return itemId + "," + username + "," + rentalDate + "," + dueDate;
	}
}