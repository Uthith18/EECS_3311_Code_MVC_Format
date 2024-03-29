package gui.models;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class AddItemsModel {
	
	public void saveItemToCSV(int itemId, String type, String name, String location, String author, String genre) {
        int copiesAvailable = 20; // each item added, starts with 20 
        try (FileWriter fw = new FileWriter("items.csv", true);
             PrintWriter out = new PrintWriter(fw)) {
            out.println(itemId + "," + type + "," + name + "," + location + "," + copiesAvailable + "," + author + "," + genre);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to save item to CSV.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
