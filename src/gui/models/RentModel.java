package gui.models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import gui.Item;
import gui.Main;
import gui.Rental;

public class RentModel {
	
	
	
	public void recordRental(Item item, String username) {
        String rentalRecord = item.getItemId() + "," + Main.getCurrentUser().getUserName() + "," + LocalDate.now() + "," + LocalDate.now().plusMonths(1); // Assumes a 1-month rental period
        try (PrintWriter out = new PrintWriter(new FileWriter("rentals.csv", true))) {
            out.println(rentalRecord);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	 // Call this method when an item is returned
    public void returnRental(int itemId, String username) {
        List<String> records = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("rentals.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (!(data[0].equals(String.valueOf(itemId)) && data[1].equals(username))) {
                    records.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
           
        }
        
        // Rewrite the csv 
        try (PrintWriter writer = new PrintWriter(new FileWriter("rentals.csv"))) {
            for (String record : records) {
                writer.println(record);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    public List<Rental> getRentalsForCurrentUser(String username) {
        List<Rental> userRentals = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("rentals.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[1].equals(username)) {
                    int itemId = Integer.parseInt(data[0]);
                    LocalDate rentalDate = LocalDate.parse(data[2]);
                    LocalDate dueDate = LocalDate.parse(data[3]);
                    userRentals.add(new Rental(itemId, username, rentalDate, dueDate));
                }
            }
        } catch (IOException | DateTimeParseException e) {
            e.printStackTrace();
           
        }
        return userRentals;
    }
    
 
  

}
