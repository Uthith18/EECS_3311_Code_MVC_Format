package gui.views;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import gui.Item;
import gui.controllers.RemoveItemController;

import gui.models.ItemManagementModel;
import gui.models.RentModel;
import gui.views.RemoveItemView;
import gui.views.ReturnRentedBooks;

public class ItemManagement {
    private JPanel contentPane;
    private JFrame frame;
    private JList<Item> itemList;
    private ItemManagementModel itemModel = new ItemManagementModel();

    public ItemManagement() {
        frame = new JFrame("Item Management");
        frame.setResizable(false);
        frame.setBounds(100, 100, 369, 190);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = new JPanel();
        contentPane.setLayout(null);
        frame.setContentPane(contentPane);

        JLabel Action = new JLabel("Select Action");
        Action.setFont(new Font("Dialog", Font.BOLD, 27));
        Action.setForeground(SystemColor.activeCaptionText);
        Action.setBounds(92, 26, 189, 30);
        contentPane.add(Action);

        JButton btnReturnRental = new JButton("Return Rental");
        btnReturnRental.setBounds(52, 84, 117, 29);
        contentPane.add(btnReturnRental);
        btnReturnRental.addActionListener(e -> {
        	new ReturnRentedBooks();
        	
            frame.dispose();
        });

        JButton btnRemoveItem = new JButton("Remove Item");
        btnRemoveItem.setBounds(210, 84, 117, 29);
        contentPane.add(btnRemoveItem);
        btnRemoveItem.addActionListener(e -> {
        	List<Item> items = itemModel.fetchItems(); 
        	 RemoveItemView view = new RemoveItemView();
             new RemoveItemController(view, items);
            frame.dispose();
        });

        frame.setVisible(true); 
    }
}