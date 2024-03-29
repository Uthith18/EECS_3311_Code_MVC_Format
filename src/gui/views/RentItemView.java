package gui.views;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import gui.Item;

public class RentItemView {
	
	 private JFrame frame;
	    private JList<Item> itemList;
	    private DefaultListModel<Item> listModel;
	    private JButton rentButton;

	    public RentItemView() {
	        initializeUI();
	    }

	    private void initializeUI() {
	        frame = new JFrame("Rent Item");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(400, 300);

	        listModel = new DefaultListModel<>();
	        itemList = new JList<>(listModel);
	        itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	        JScrollPane scrollPane = new JScrollPane(itemList);
	        frame.add(scrollPane, BorderLayout.CENTER);

	        rentButton = new JButton("Rent Selected Item");
	        frame.add(rentButton, BorderLayout.SOUTH);

	        frame.setVisible(true);
	    }

	    public void setItemList(List<Item> items) {
	        listModel.clear();
	        items.forEach(listModel::addElement);
	    }

	    public JFrame getFrame() {
	        return frame;
	    }

	    public Item getSelectedItem() {
	        return itemList.getSelectedValue();
	    }

	    public void addRentButtonListener(ActionListener listener) {
	        rentButton.addActionListener(listener);
	    }
	    
	    public void showMessageDialog(String message, String title, int messageType) {
	        JOptionPane.showMessageDialog(frame, message, title, messageType);
	    }

}
