package gui.controllers;

import java.awt.Color;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import gui.views.*;
import gui.Main;
import gui.Rental;
import gui.RequestQueue;
import gui.models.AddDiscountedItemsModel;
import gui.models.AddItemsModel;
import gui.models.AddTextbookModel;
import gui.models.BookSearchModel;
import gui.models.BuyDiscounteditemsModel;
import gui.models.DashboardPageModel;
import gui.models.ItemManagementModel;
import gui.models.LoginPageModel;
import gui.models.RegisterModel;
import gui.models.RentModel;
import gui.models.VerifyUserModel;
import gui.views.AddDiscountedItemsView;
import gui.views.AddItemsView;
import gui.views.AddTextbookView;
import gui.views.BookSearchView;
import gui.views.BookVirtualCopy;
import gui.views.BuyDiscountedItemsView;
import gui.views.DashboardPageView;
import gui.views.LoginPageView;
import gui.views.NewspaperView;
import gui.views.OrderBookPageView;
import gui.views.RentItemView;
import gui.views.VerifyUserView;

public class DashboardPageController 
{
	    private DashboardPageView view;
	    private DashboardPageModel model;

	    public DashboardPageController(DashboardPageView view, DashboardPageModel model) {
	        this.view = view;
	        this.model = model;
	        initView();
	    }

	 
	    private void initView() {
	        view.addRentItemActionListener(e -> openRentItemPage());
	        view.addValidateClientsActionListener(e -> openValidateClientsPage());
	        view.addAddDiscountedItemsActionListener(e -> openAddDiscountedItemsPage());
	        view.addAddItemActionListener(e -> openAddItemsPage());
	        view.addAddTextbookActionListener(e -> openAddTextbookPage());
	        view.addSearchForBookActionListener(e -> openBookSearchPage());
	        view.addBuyDiscountedItemsActionListener(e -> openBuyDiscountedPage());
	        view.addSubscribeNewsletterActionListener(e -> openNewspaperPage());
	        view.addItemManagementActionListener(e -> openItemManagementPage());
	        view.addOrderBookActionListener(e -> openOrderBookPage());
	        view.addTextbookManagementActionListener(e -> openTextbookManagementPage());
	        view.addVirtualTextbookActionListener(e -> openVirtualBook());
	        view.addLogoutActionListener(e -> logout());
	        displayRentalsWithItemNames();
	        
	        view.setVisible(true);
	    }
	    
	    private void openVirtualBook() {
	        // Check user role and open RentItemPage if allowed
	        if ("Student".equals(DashboardPageModel.getRole())) {
	        	new BookVirtualCopy();
	        } else {
	            JOptionPane.showMessageDialog(view.getFrame(), "You do not have permission");
	        }
	    }

	    private void openRentItemPage() 
	    {
	    	 ItemManagementModel model = new ItemManagementModel();
             RentItemView view = new RentItemView();
             new RentItemController(view, model);
	    }
	    
	    private void openOrderBookPage() {
	       
	        if ("Student".equals(DashboardPageModel.getRole())) {
	        	OrderBookPageView view = new OrderBookPageView();
	            RequestQueue model = RequestQueue.getInstance();
	            new OrderBookPageController(view, model);
	        	
	        } else {
	            JOptionPane.showMessageDialog(view.getFrame(), "You do not have permission");
	        }
	    }
	    
	    
	    private void openValidateClientsPage() {
	        
	        if ("Management Team".equals(DashboardPageModel.getRole())) {
	        	VerifyUserModel model = new VerifyUserModel();
	        	VerifyUserView view = new VerifyUserView();
	        	new VerifyUserController(model, view);
	        } else {
	            JOptionPane.showMessageDialog(view.getFrame(), "You do not have permission");
	        }
	    }
	    
	    private void openAddDiscountedItemsPage() {
	        
	        if ("Management Team".equals(DashboardPageModel.getRole())) {
	        	 EventQueue.invokeLater(() -> {
	        		 AddDiscountedItemsModel model = new AddDiscountedItemsModel();
	        		 AddDiscountedItemsView view = new AddDiscountedItemsView();
	                 new AddDiscountedItemController(model, view);
	             });
	 
	        } else {
	            JOptionPane.showMessageDialog(view.getFrame(), "You do not have permission");
	        }
	    }
	    
	    private void openAddItemsPage() {
	        
	        if ("Management Team".equals(DashboardPageModel.getRole())) {
	        	 
	        		 AddItemsModel model = new AddItemsModel();
	        		 AddItemsView view = new AddItemsView();
	                 new AddItemController(model, view);
	 
	        } else {
	            JOptionPane.showMessageDialog(view.getFrame(), "You do not have permission");
	        }
	    }
	    
	    private void openAddTextbookPage() {
	        
	        if ("Management Team".equals(DashboardPageModel.getRole())) {
	        	 
	        	AddTextbookModel model = new AddTextbookModel();
	            AddTextbookView view = new AddTextbookView();
	            new AddTextbookController(model, view);
	 
	        } else {
	            JOptionPane.showMessageDialog(view.getFrame(), "You do not have permission");
	        }
	    }
	    
	   private void openItemManagementPage() {
	        
	        if ("Management Team".equals(DashboardPageModel.getRole())) {
	        	 
	        	new gui.views.ItemManagement();
	 
	        } else {
	            JOptionPane.showMessageDialog(view.getFrame(), "You do not have permission");
	        }
	    }   
	    
	 private void openBookSearchPage() 
	 {
	 BookSearchModel model = new BookSearchModel();
     BookSearchView view = new BookSearchView();
     new BookSearchController(model, view);
	 }
	    
	 private void openBuyDiscountedPage() 
	 {
		 BuyDiscounteditemsModel model = new BuyDiscounteditemsModel();
	     BuyDiscountedItemsView view = new BuyDiscountedItemsView();
	     new BuyDiscountedItemControler(model, view);
	 }
	 
	 private void openNewspaperPage() 
	 {
		 NewspaperView view = new NewspaperView();
	       new NewspaperController(view);
	 }
	 
	 private void openTextbookManagementPage() {
	        
	        if ("Faculty Member".equals(DashboardPageModel.getRole())) {
	        	 
	        	new TextbookManage(Main.getCurrentUser().getCourses());
	 
	        } else {
	            JOptionPane.showMessageDialog(view.getFrame(), "You do not have permission");
	        }
	    }
	 
	 private void logout() 
	 {
		 LoginPageModel model = new LoginPageModel();
	     LoginPageView view = new LoginPageView();
	      LoginPageController controller = new LoginPageController(model, view);
	 }
	 
	 
	 private void displayRentalsWithItemNames() {
		    String username = Main.getCurrentUser().getUserName();
		    RentModel rentModel = new RentModel();
		    ItemManagementModel itemModel = new ItemManagementModel();

		    //rentals for the current user
		    List<Rental> rentals = rentModel.getRentalsForCurrentUser(username);

		    //get id number 
		    List<Integer> itemIds = rentals.stream().map(Rental::getItemId).collect(Collectors.toList());

		    //get name from id 
		    Map<Integer, String> itemNames = itemModel.getItemNamesByIds(itemIds);

		    // like a tostring , not perfect 
		    StringBuilder displayText = new StringBuilder();
		    for (Rental rental : rentals) {
		        String itemName = itemNames.getOrDefault(rental.getItemId(), "Unknown Item");
		        displayText.append(itemName)
		                   .append(" - Due: ")
		                   .append(rental.getDueDate().toString())
		                   .append("\n");
		    }
	 

		    view.getTextArea().setText(displayText.toString());

	 }
}
