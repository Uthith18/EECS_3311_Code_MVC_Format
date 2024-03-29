package gui.views;
import javax.swing.*;

import gui.Item;

import java.awt.*;
import java.awt.event.ActionListener;

public class RemoveItemView {
	private JFrame frame;
    private JList<Item> itemList;
    private DefaultListModel<Item> listModel;
    private JButton removeButton;

    public RemoveItemView() {
        initializeUI();
    }

    private void initializeUI() {
        frame = new JFrame("Remove Item");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        listModel = new DefaultListModel<>();
        itemList = new JList<>(listModel);
        itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(itemList);
        frame.add(scrollPane, BorderLayout.CENTER);

        removeButton = new JButton("Remove Selected Item");
        frame.add(removeButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    public DefaultListModel<Item> getListModel() {
        return listModel;
    }

    public JList<Item> getItemList() {
        return itemList;
    }

    public void addRemoveButtonListener(ActionListener listener) {
        removeButton.addActionListener(listener);
    }

    public void showMessageDialog(String message, String title, int messageType) {
        JOptionPane.showMessageDialog(null, message, title, messageType);
    }
    
}