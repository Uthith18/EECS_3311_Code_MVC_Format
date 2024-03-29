package gui.controllers;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import gui.Item;
import gui.views.RemoveItemView;

public class RemoveItemController {
	 private RemoveItemView view;
	    private List<Item> items;
	    

	    public RemoveItemController(RemoveItemView view, List<Item> items) {
	        this.view = view;
	        this.items = items;
	        this.view.addRemoveButtonListener(e -> removeSelectedItem());
	        loadItemsIntoListModel();
	    }

	    // put item into list for user to select 
	    private void loadItemsIntoListModel() {
	        DefaultListModel<Item> model = view.getListModel();
	        for (Item item : items) {
	            model.addElement(item);
	        }
	    }

	    
	    private void removeSelectedItem() {
	        JList<Item> itemList = view.getItemList();
	        int selectedIndex = itemList.getSelectedIndex();
	        if (selectedIndex != -1) {
	            Item selectedItem = itemList.getSelectedValue();
	            int newCopies = selectedItem.getCopiesAvailable() - 1;
	            selectedItem.setCopiesAvailable(newCopies);

	            if (newCopies <= 0) {
	                view.getListModel().removeElementAt(selectedIndex);
	            } else {
	                view.getListModel().setElementAt(selectedItem, selectedIndex);
	            }

	            updateItemsCSV();
	            view.showMessageDialog("One copy of the item removed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
	        } else {
	            view.showMessageDialog("No item selected.", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }

	    // Update item when item is removed, we need to rewrite whole csv 
	    private void updateItemsCSV() {
	    	 
	    	try (PrintWriter out = new PrintWriter(new FileWriter("items.csv"))) {
		            for (Item item : items) {
		                out.println(item.getItemId() + "," + item.getType() + "," +
		                            item.getName() + "," + item.getLocation() + "," +
		                            item.getCopiesAvailable() + "," + item.getAuthor() + "," +
		                            item.getGenre());
		            }
		        } catch (IOException e) {
		            e.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Failed to update items file.", "Error", JOptionPane.ERROR_MESSAGE, null);
		        }
	    }
	    
}
