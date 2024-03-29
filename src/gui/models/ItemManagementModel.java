package gui.models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gui.Item;
import gui.Main;

public class ItemManagementModel {

	
	 public List<Item> fetchItems() {
	        List<Item> items = new ArrayList<>();
	        String filePath = "items.csv"; 

	        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                String[] itemData = line.split(",");
	                if (itemData.length == 7) {
	                    try {
	                        int itemId = Integer.parseInt(itemData[0].trim());
	                        String type = itemData[1].trim();
	                        String name = itemData[2].trim();
	                        String location = itemData[3].trim();
	                        int copiesAvailable = Integer.parseInt(itemData[4].trim());
	                        String author = itemData[5].trim();
	                        String genre = itemData[6].trim();
	                        items.add(new Item(itemId, type, name, location, copiesAvailable, author, genre));
	                    } catch (NumberFormatException e) {
	                        System.err.println("Error parsing item data from CSV: " + e.getMessage());
	                    }
	                }
	            }
	        } catch (IOException e) {
	            System.err.println("Error reading items from CSV: " + e.getMessage());
	        }

	        return items;
	    }
	 
	 // update item list 
	 public void updateItems(Item updatedItem) {
	        List<Item> items = fetchItems(); 
	        for (int i = 0; i < items.size(); i++) {
	            if (items.get(i).getItemId() == updatedItem.getItemId()) {
	                items.set(i, updatedItem);
	                break;
	            }
	        }

	        saveItemsToCSV(items);
	    }
	 
	 // add item to CSV 
	 private void saveItemsToCSV(List<Item> items) {
	        try (PrintWriter out = new PrintWriter(new FileWriter("items.csv"))) {
	        	  for (Item item : items) {
	                  out.println(item.toCsvString());
	              }
	          } catch (IOException e) {
	              e.printStackTrace();
	          
	        }
	    }

	// when a item is returned, to add one to item 
	 public void incrementItemCopies(int itemId) {
		  List<Item> items = fetchItems();
	        boolean itemFound = false;

	        for (Item item : items) {
	            if (item.getItemId() == itemId) {
	                item.setCopiesAvailable(item.getCopiesAvailable() + 1);
	                itemFound = true;
	                break;
	            }
	        }

	        if (itemFound) {
	            saveItemsToCSV(items); // Save the updated list back to the CSV
	        } else {
	            
	        }
	    }

	 // used for textField in dash 
	public Map<Integer, String> getItemNamesByIds(List<Integer> itemIds) {
    List<Item> items = fetchItems();
    Map<Integer, String> idToNameMap = new HashMap<>();
    for (Item item : items) {
        if (itemIds.contains(item.getItemId())) {
            idToNameMap.put(item.getItemId(), item.getName());
        }
    }
    return idToNameMap;
}
		
	}
	 


