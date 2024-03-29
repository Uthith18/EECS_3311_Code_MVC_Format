package gui.models;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;

import javax.swing.JOptionPane;

public class BookSearchModel 
{
	
	public List<String[]> readAndFilterBooksFromCsv(String searchType, String searchValue) {
        List<String[]> filteredBooks = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("items.csv"))) { 
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] book = line.split(",");
                boolean matches = switch (searchType) {
                    case "Title" -> book[2].toLowerCase().contains(searchValue);
                    case "Author" -> book[5].toLowerCase().contains(searchValue);
                    case "Genre" -> book[6].toLowerCase().contains(searchValue); 
                    default -> false;
                };

                if (matches) {
                    filteredBooks.add(new String[]{book[0], book[1], book[2]}); 
                }
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "CSV file not found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return filteredBooks;
    }
}

