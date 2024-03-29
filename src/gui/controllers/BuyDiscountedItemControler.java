package gui.controllers;

import java.awt.EventQueue;

import javax.swing.JOptionPane;

import gui.CreditPayment;
import gui.MobileWalletPayment;
import gui.PaymentContext;
import gui.PaymentFinishedListener;
import gui.models.BuyDiscounteditemsModel;
import gui.views.BuyDiscountedItemsView;
import gui.DebitPayment;

public class BuyDiscountedItemControler implements PaymentFinishedListener  {
	
	 private BuyDiscounteditemsModel model;
	    private BuyDiscountedItemsView view;

	    public BuyDiscountedItemControler(BuyDiscounteditemsModel model, BuyDiscountedItemsView view) {
	        this.model = model;
	        this.view = view;
	        this.view.addConfirmButtonListener(e -> confirmSelection());
	        this.view.displayItems(model.loadItemsFromCSV("Discount.csv"));
	        this.view.display();
	    }

	    private void confirmSelection() {
	        String selectedItem = view.getSelectedValue();
	        if (selectedItem != null) {
	            view.showPaymentOptions(e -> handlePaymentMethod(e.getActionCommand()));
	        } else {
	            view.showMessage("Please select an item.", "No selection", JOptionPane.WARNING_MESSAGE);
	        }
	    }

	    private void handlePaymentMethod(String method) {
	        PaymentContext context = new PaymentContext(this);
	        switch (method) {
	            case "Debit":
	                context.setState(new DebitPayment());
	                break;
	            case "Credit":
	                context.setState(new CreditPayment());
	                break;
	            case "Mobile Wallet":
	                context.setState(new MobileWalletPayment());
	                break;
	            default:
	                return;
	        }
	        context.pay();
	    }

	    @Override
	    public void onPaymentFinished(boolean success) {
	        if (success) {
	            EventQueue.invokeLater(() -> {
	                String selectedItem = view.getSelectedValue();
	                if (selectedItem != null) {
	                    model.removeItemFromCSV(selectedItem, "Discount.csv");
	                    view.showMessage("Payment successful, item removed.", "Success", JOptionPane.INFORMATION_MESSAGE);
	                }
	            });
	        } else {
	            view.showMessage("Payment failed, please try again.", "Payment Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	}


