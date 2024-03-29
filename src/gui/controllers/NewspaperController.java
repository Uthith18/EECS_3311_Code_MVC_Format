package gui.controllers;

import javax.swing.JOptionPane;
import gui.views.*;

import gui.SubscriptionStrategy;
import gui.NewYorkTimes;
import gui.Guardian;
import gui.Wash;

public class NewspaperController {
	
	 private NewspaperView view;

	    public NewspaperController(NewspaperView view){
	    	
	    	this.view = view;
	        this.view.addSubscribeButtonListener(e -> {
	            String selectedNewspaper = view.getSelectedNewspaper();
	            SubscriptionStrategy strategy = getSubscriptionStrategy(selectedNewspaper);
	            if (strategy != null) {
	                strategy.openSubscriptionPage(view.getFrame());
	            } else {
	                JOptionPane.showMessageDialog(view.getFrame(), "Newspaper not recognized", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        });

	        this.view.addDashboardButtonListener(e -> view.getFrame().setVisible(false));
	    }

	    private SubscriptionStrategy getSubscriptionStrategy(String newspaper) {
	        switch (newspaper) {
	            case "New York Times":
	                return new NewYorkTimes();
	            case "The Guardian":
	                return new Guardian();
	            case "The Washington Post":
	                return new Wash();
	            default:
	                return null;
	        }
	    }
	}


