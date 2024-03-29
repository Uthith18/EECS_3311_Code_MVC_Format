package gui.controllers;

import java.util.List;

import javax.swing.JOptionPane;

import gui.Item;
import gui.Main;
import gui.models.ItemManagementModel;
import gui.models.RentModel;
import gui.views.RentItemView;

public class RentItemController {

	 private RentItemView view;
	    private ItemManagementModel itemModel;
	    private RentModel rentalModel; 

	    public RentItemController(RentItemView view, ItemManagementModel itemModel) {
	        this.view = view;
	        this.itemModel = itemModel;
	        this.rentalModel = new RentModel(); 
	        initView();
	    }

	    private void initView() {
	        loadItems();
	        view.addRentButtonListener(e -> rentSelectedItem());
	    }

	    private void loadItems() {
	        List<Item> items = itemModel.fetchItems();
	        view.setItemList(items);
	    }

	    private void rentSelectedItem() {
	        Item selectedItem = view.getSelectedItem();
	        if (selectedItem != null && selectedItem.getCopiesAvailable() > 0) {
	            selectedItem.setCopiesAvailable(selectedItem.getCopiesAvailable() - 1);
	            itemModel.updateItems(selectedItem);
	            rentalModel.recordRental(selectedItem, Main.getCurrentUser().getUserName()); // Record the rental
	            loadItems(); // Refresh the list
	            view.showMessageDialog("Item rented successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
	        } else {
	            view.showMessageDialog("This item is currently not available.", "Unavailable", JOptionPane.WARNING_MESSAGE);
	        }
	    }
	}

