package gui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ItemManager {

	public List<Item> loadItemsFromCSV(String filePath) {
        List<Item> items = new ArrayList<>();
        String line = ""; 
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                String[] itemData = line.split(",");

                if (itemData.length < 7) {
                    System.out.println("Skipping invalid line: " + line);
                    continue;
                }

                try {
                    Item item = new Item(
                        Integer.parseInt(itemData[0].trim()), // Item ID
                        itemData[1].trim(), // Type
                        itemData[2].trim(), // Name
                        itemData[3].trim(), // Location
                        Integer.parseInt(itemData[4].trim()), // Copies Available
                        itemData[5].trim(), // Author
                        itemData[6].trim() // Genre
                    );
                    items.add(item);
                } catch (NumberFormatException e) {
                    System.out.println("Skipping invalid line due to number format issue: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return items;
    }


}
