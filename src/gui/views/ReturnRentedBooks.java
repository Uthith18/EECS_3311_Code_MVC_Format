package gui.views;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import gui.Item;
import gui.Rental;

public class ReturnRentedBooks {
    private JFrame frame;
    private DefaultListModel<Rental> rentalListModel = new DefaultListModel<>();
    private JList<Rental> rentalList;

    public ReturnRentedBooks() {
        frame = new JFrame("Return Rented Books");
        initializeUI();
        loadRentalsIntoListModel("rentals.csv");
    }

    private void initializeUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        rentalList = new JList<>(rentalListModel);
        rentalList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(rentalList);
        frame.add(scrollPane, BorderLayout.CENTER);

        JButton returnButton = new JButton("Return Selected Book");
        returnButton.addActionListener(e -> returnSelectedBook());
        frame.add(returnButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
    
    /*
     * Code is not perfect, had issues with formatting 
     */
    private void loadRentalsIntoListModel(String filePath) {
        rentalListModel.clear(); // Clear existing data
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    line = line.replace("\uFEFF", "");
                    isFirstLine = false;
                }
                String[] data = line.split(",");
                if (data.length == 4) {
                    try {
                        Rental rental = new Rental(
                            Integer.parseInt(data[0].trim()),
                            data[1].trim(),
                            LocalDate.parse(data[2].trim()),
                            LocalDate.parse(data[3].trim())
                        );
                        rentalListModel.addElement(rental);
                    } catch (NumberFormatException | DateTimeParseException e) {
                        System.err.println("Skipping line due to formatting issue: " + line);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	private void returnSelectedBook() {
	    Rental selectedRental = rentalList.getSelectedValue();
	    if (selectedRental != null) {
	        List<Item> items = loadItemsFromCSV("items.csv");
	        boolean exists = false;
	        for (Item item : items) {
	            if (item.getItemId() == selectedRental.getItemId()) {
	                item.setCopiesAvailable(item.getCopiesAvailable() + 1);
	                exists = true;
	                break;
	            }
	        }

	        if (!exists) {
	            JOptionPane.showMessageDialog(frame, "This book does not exist in the inventory. Please add it manually.");
	            
	        } else {
	            updateItemsCSV(items); // Update items.csv 
	            removeRental(selectedRental); // Remove the rental  rentals.csv
	            
	            rentalListModel.removeElement(selectedRental);
	        }
	    } else {
	        JOptionPane.showMessageDialog(frame, "No book selected.", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}

	private void removeRental(Rental rentalToRemove) {
	    List<Rental> rentals = loadRentalsFromCSV("rentals.csv");
	    rentals.removeIf(rental -> rental.getItemId() == rentalToRemove.getItemId() && rental.getUsername().equals(rentalToRemove.getUsername()));
	    updateRentalsCSV(rentals, "rentals.csv");
	}
	
	
	public List<Item> loadItemsFromCSV(String filePath) {
	    List<Item> items = new ArrayList<>();
	    String line;
	    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
	        while ((line = br.readLine()) != null) {
	            String[] itemData = line.split(",");
	            if (itemData.length == 7) {
	                try {
	                    Item item = new Item(
	                        Integer.parseInt(itemData[0].trim()),
	                        itemData[1].trim(),
	                        itemData[2].trim(),
	                        itemData[3].trim(),
	                        Integer.parseInt(itemData[4].trim()),
	                        itemData[5].trim(),
	                        itemData[6].trim()
	                    );
	                    items.add(item);
	                } catch (NumberFormatException e) {
	                    System.err.println("Skipping line due to formatting issue: " + line);
	                }
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return items;
	}

	private void updateItemsCSV(List<Item> items) {
	    try (PrintWriter out = new PrintWriter(new FileWriter("items.csv"))) {
	        for (Item item : items) {
	            out.println(item.getItemId() + "," + item.getType() + "," +
	                        item.getName() + "," + item.getLocation() + "," +
	                        item.getCopiesAvailable() + "," + item.getAuthor() + "," +
	                        item.getGenre());
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(frame, "Failed to update items file.", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	public List<Rental> loadRentalsFromCSV(String filePath) {
	    List<Rental> rentals = new ArrayList<>();
	    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
	        String line;
	        while ((line = br.readLine()) != null) {
	            String[] data = line.split(",");
	            if (data.length == 4) {
	                try {
	                    Rental rental = new Rental(
	                        Integer.parseInt(data[0].trim()),
	                        data[1].trim(),
	                        LocalDate.parse(data[2].trim()),
	                        LocalDate.parse(data[3].trim())
	                    );
	                    rentals.add(rental);
	                } catch (NumberFormatException | DateTimeParseException e) {
	                    System.err.println("Skipping line due to formatting issue: " + line);
	                }
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return rentals;
	}
	
	private void updateRentalsCSV(List<Rental> rentals, String filePath) {
	    try (PrintWriter out = new PrintWriter(new FileWriter(filePath, false))) { 
	        for (Rental rental : rentals) {
	            out.println(rental.toCSVString());
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(frame, "Failed to update rentals file.", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}

}
