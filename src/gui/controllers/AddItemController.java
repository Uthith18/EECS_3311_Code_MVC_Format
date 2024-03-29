package gui.controllers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.models.AddItemsModel;
import gui.views.AddItemsView;


public class AddItemController {
	
	private AddItemsModel model;
    private AddItemsView view;

    public AddItemController(AddItemsModel model, AddItemsView view) {
        this.model = model;
        this.view = view;
        initView();
    }

    private void initView() {
        view.addBtnAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addItem();
            }
        });
    }

    private void addItem() {
        try {
            int itemId = Integer.parseInt(view.getItemId());
            String type = view.getType();
            String name = view.getName();
            String location = view.getLocation();
            String author = view.getAuthor();
            String genre = view.getGenre();
            model.saveItemToCSV(itemId, type, name, location, author, genre);
            view.displayMessage("Item Added Successfully!");
            // Clear fields 
        } catch (NumberFormatException e) {
            view.displayMessage("Please enter valid item ID.");
        }
    }
}


