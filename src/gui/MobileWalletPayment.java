package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


	public class MobileWalletPayment implements PaymentState {
	    
		
	   
	    public void pay(PaymentContext context) {
	    	 JFrame frame = new JFrame();
	        JTextField emailField = new JTextField();
	        JPasswordField passwordField = new JPasswordField();

	        Object[] message = {
	            "PayPal Email:", emailField,
	            "Password:", passwordField
	        };

	        int option = JOptionPane.showConfirmDialog(null, message, "Enter PayPal Details", JOptionPane.OK_CANCEL_OPTION);
	        if (option == JOptionPane.OK_OPTION) {
	        	JOptionPane.showMessageDialog(frame, "Payment Accepted");
	        	 context.paymentFinished(true);
	        } else {
	            System.out.println("Payment cancelled.");
	        }
	    }
	}

