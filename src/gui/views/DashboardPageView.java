package gui.views;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DashboardPageView {
	
	   private JFrame frame;
	    private JButton rentItemButton;
	    private JButton subscribeNewsletterButton;
	    private JButton searchForBookButton;
	    private JButton textbookManagementButton;
	    private JButton itemManagementButton;
	    private JButton orderBookButton;
	    private JButton validateClientsButton;
	    private JButton buyDiscountedItemsButton;
	    private JButton virtualTextbookButton;
	    private JButton addItemButton;
	    private JButton logoutButton;
	    private JButton addTextbookButton;
	    private JButton addDiscountedItemsButton;
	    private static JTextArea textArea;

	    public DashboardPageView() {
	        initializeUI();
	    }

	    private void initializeUI() {
	        frame = new JFrame("🎉 Welcome to the Dashboard 🎉");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(459, 775);
	        frame.setResizable(false);
	        frame.getContentPane().setLayout(null);

	        JPanel rightPanel = new JPanel();
	        rightPanel.setLayout(null);
	        rightPanel.setBounds(0, 0, 459, 742);
	        rightPanel.setBackground(new Color(238, 238, 238));

	        // Initialize buttons
	        JLabel dashText = new JLabel("🎓 Dashboard 🎓 ");
	        dashText.setBackground(SystemColor.activeCaption);
	        dashText.setBounds(73, 34, 326, 60);
	        rightPanel.add(dashText);
	        dashText.setFont(new Font("Comic Sans MS", Font.BOLD, 40)); // font
	        dashText.setForeground(Color.BLACK);
	        rentItemButton = new JButton("Rent Item");
	        subscribeNewsletterButton = new JButton("<html>Subscribe to<br>Newsletter</html>");
	        searchForBookButton = new JButton("Search for Book");
	        textbookManagementButton = new JButton("<html>Textbook<br>Management<br>(Faculty)</html>");
	        itemManagementButton = new JButton("<html>Item Management<br>(Lib Management)</html>");
	        orderBookButton = new JButton("Order Book (Student)");
	        validateClientsButton = new JButton("<html>Validate Clients<br>(Lib Management)</html>");
	        buyDiscountedItemsButton = new JButton("Buy Discounted Items");
	        virtualTextbookButton = new JButton("<html>Virtual Textbook<br>(Student)</html>");
	        addItemButton = new JButton("<html>Add Item<br>(Lib Management)</html>");
	        logoutButton = new JButton("Log out");
	        addTextbookButton = new JButton("<html>Add Textbook<br>(Lib Management)</html>");
	        addDiscountedItemsButton = new JButton("<html>Add Discounted Items<br>(Lib Management)</html>");

	        // Add components to the right panel
	        addComponentToPanel(rightPanel, rentItemButton, 54, 244, 161, 70);
	        addComponentToPanel(rightPanel, subscribeNewsletterButton, 54, 328, 160, 70);
	        addComponentToPanel(rightPanel, searchForBookButton, 54, 412, 160, 70);
	        addComponentToPanel(rightPanel, textbookManagementButton, 257, 244, 161, 70);
	        addComponentToPanel(rightPanel, itemManagementButton, 258, 328, 160, 70);
	        addComponentToPanel(rightPanel, orderBookButton, 256, 411, 162, 70);
	        addComponentToPanel(rightPanel, validateClientsButton, 54, 503, 161, 70);
	        addComponentToPanel(rightPanel, buyDiscountedItemsButton, 257, 503, 161, 70);
	        addComponentToPanel(rightPanel, virtualTextbookButton, 54, 585, 161, 70);
	        addComponentToPanel(rightPanel, addItemButton, 258, 584, 160, 70);
	        addComponentToPanel(rightPanel, logoutButton, 336, 6, 117, 29);
	        addComponentToPanel(rightPanel, addTextbookButton, 54, 667, 160, 70);
	        addComponentToPanel(rightPanel, addDiscountedItemsButton, 257, 666, 161, 70);

	        // Text Area for displaying rented items or timeline information
	        textArea = new JTextArea();
	        textArea.setEditable(false);
	        textArea.setBounds(54, 117, 364, 104);
	        rightPanel.add(textArea);

	        frame.getContentPane().add(rightPanel);
	        frame.setLocationRelativeTo(null); 
	    }

	    private void addComponentToPanel(JPanel panel, JComponent component, int x, int y, int width, int height) {
	        component.setBounds(x, y, width, height);
	        panel.add(component);
	    }

	    public void setVisible(boolean visible) {
	        frame.setVisible(visible);
	    }

	    
	    public void addRentItemActionListener(ActionListener listener) { rentItemButton.addActionListener(listener); }
	    public void addSubscribeNewsletterActionListener(ActionListener listener) { subscribeNewsletterButton.addActionListener(listener);}
	    public void addSearchForBookActionListener(ActionListener listener) {searchForBookButton.addActionListener(listener);}
	    public void addTextbookManagementActionListener(ActionListener listener) {textbookManagementButton.addActionListener(listener);}
	    public void addItemManagementActionListener(ActionListener listener) {itemManagementButton.addActionListener(listener);}

	    public void addOrderBookActionListener(ActionListener listener) {orderBookButton.addActionListener(listener);}

	    public void addValidateClientsActionListener(ActionListener listener) {
	        validateClientsButton.addActionListener(listener);
	    }

	    public void addBuyDiscountedItemsActionListener(ActionListener listener) {
	        buyDiscountedItemsButton.addActionListener(listener);
	    }

	    public void addVirtualTextbookActionListener(ActionListener listener) {
	        virtualTextbookButton.addActionListener(listener);
	    }

	    public void addAddItemActionListener(ActionListener listener) {
	        addItemButton.addActionListener(listener);
	    }

	    public void addLogoutActionListener(ActionListener listener) {
	        logoutButton.addActionListener(listener);
	    }

	    public void addAddTextbookActionListener(ActionListener listener) {
	        addTextbookButton.addActionListener(listener);
	    }

	    public void addAddDiscountedItemsActionListener(ActionListener listener) {
	        addDiscountedItemsButton.addActionListener(listener);
	    }
	    
	    
	    
	    public JFrame getFrame() {
	        return frame;
	    }
	    // need this for a delierable 
	    public JTextArea getTextArea() {
	        return textArea;
	    }
	    
	    

	   
}