package gui.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;

public class BuyDiscountedItemsView 
{

	private JFrame frame;
    private JList<String> itemList;
    private DefaultListModel<String> itemListModel = new DefaultListModel<>();

    public BuyDiscountedItemsView() {
        frame = new JFrame("Select Item");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        itemList = new JList<>(itemListModel);
        JScrollPane scrollPane = new JScrollPane(itemList);
        frame.add(scrollPane, BorderLayout.CENTER);

        JButton confirmButton = new JButton("Confirm Selection");
        frame.add(confirmButton, BorderLayout.SOUTH);

        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
    }

    public void displayItems(List<String> items) {
        items.forEach(itemListModel::addElement);
    }

    public String getSelectedValue() {
        return itemList.getSelectedValue();
    }

    public void addConfirmButtonListener(ActionListener listener) {
        JButton confirmButton = (JButton) frame.getContentPane().getComponent(1);
        confirmButton.addActionListener(listener);
    }

    public void display() {
        frame.setVisible(true);
    }

    public void showPaymentOptions(ActionListener paymentMethodHandler) {
        String[] options = {"Debit", "Credit", "Mobile Wallet"};
        int choice = JOptionPane.showOptionDialog(frame, "Choose your payment method:",
                "Payment Method", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]);

        paymentMethodHandler.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, options[choice]));
    }

    public void showMessage(String message, String title, int messageType) {
        JOptionPane.showMessageDialog(frame, message, title, messageType);
    }

    public JFrame getFrame() {
        return frame;
    }
}

