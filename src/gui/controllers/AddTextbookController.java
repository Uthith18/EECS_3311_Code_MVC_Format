package gui.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.models.AddTextbookModel;
import gui.views.AddTextbookView;

public class AddTextbookController {
	
	  private AddTextbookModel model;
	    private AddTextbookView view;

	    public AddTextbookController(AddTextbookModel model, AddTextbookView view) {
	        this.model = model;
	        this.view = view;
	        initView();
	    }

	    private void initView() {
	        view.addAddTextbookActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                addTextbook();
	            }
	        });

	        
	        view.setVisible(true);
	    }

	    private void addTextbook() {
	        String bookTitle = view.getBookTitle();
	        String course = view.getSelectedCourse();
	        String link = view.getLink();

	        model.writeToCSV(bookTitle, course, link);
	        view.displayMessage("Textbook added successfully.");
	    }
}


