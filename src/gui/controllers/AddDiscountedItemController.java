package gui.controllers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.models.AddDiscountedItemsModel;
import gui.views.AddDiscountedItemsView;

public class AddDiscountedItemController {
	
	 private AddDiscountedItemsModel model;
	    private AddDiscountedItemsView view;

	    public AddDiscountedItemController(AddDiscountedItemsModel model, AddDiscountedItemsView view) {
	        this.model = model;
	        this.view = view;
	        initView();
	    }

	    private void initView() {
	        view.addAddButtonListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                addDiscountedItem();
	            }
	        });
	        view.setVisible(true);
	    }

	    private void addDiscountedItem() {
	        String type = view.getType();
	        String name = view.getName();
	        String price = view.getPrice();
	        
	        model.addDiscountedItem(type, name, price);
	        view.displayMessage("Item Added Successfully!");
	        view.clearFields();
	    }
}


