package gui.views;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AddItemsView {
	
	  private JFrame frame;
	    private JTextField itemIdField, nameField, locationField, authorField, genreField;
	    private JComboBox<String> typeField;
	    private JButton btnAdd;
	
	public AddItemsView() {
        initializeUI();
    }

    private void initializeUI() {
        frame = new JFrame("Add Item");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 450);
        frame.setLayout(null);

        JLabel lblAddItem = new JLabel("Add Item");
        lblAddItem.setFont(new Font("Dialog", Font.BOLD, 27));
        lblAddItem.setBounds(120, 20, 160, 30);
        frame.add(lblAddItem);

        frame.add(createLabel("Type:", 30, 70, 80, 25));
        typeField = new JComboBox<>(new String[]{"Book", "Hard Cover", "Magazine", "CD"});
        typeField.setBounds(140, 70, 200, 25);
        frame.add(typeField);

        frame.add(createLabel("Name:", 30, 110, 80, 25));
        nameField = new JTextField(20);
        nameField.setBounds(140, 110, 200, 25);
        frame.add(nameField);

        frame.add(createLabel("Location:", 30, 150, 80, 25));
        locationField = new JTextField(20);
        locationField.setBounds(140, 150, 200, 25);
        frame.add(locationField);

        frame.add(createLabel("Item ID:", 30, 190, 80, 25));
        itemIdField = new JTextField(20);
        itemIdField.setBounds(140, 190, 200, 25);
        frame.add(itemIdField);

        frame.add(createLabel("Author:", 30, 230, 80, 25));
        authorField = new JTextField(20);
        authorField.setBounds(140, 230, 200, 25);
        frame.add(authorField);

        frame.add(createLabel("Genre:", 30, 270, 80, 25));
        genreField = new JTextField(20);
        genreField.setBounds(140, 270, 200, 25);
        frame.add(genreField);

        btnAdd = new JButton("Add");
        btnAdd.setBounds(140, 310, 100, 30);
        frame.add(btnAdd);

        frame.setLocationRelativeTo(null); // Center on screen
        frame.setVisible(true);
    }

    private JLabel createLabel(String text, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        return label;
    }

    // Getter methods for UI elements values
    public String getItemId() {
        return itemIdField.getText();
    }

    public String getType() {
        return (String) typeField.getSelectedItem();
    }

    public String getName() {
        return nameField.getText();
    }

    public String getLocation() {
        return locationField.getText();
    }

    public String getAuthor() {
        return authorField.getText();
    }

    public String getGenre() {
        return genreField.getText();
    }

    public void addBtnAddActionListener(ActionListener listener) {
        btnAdd.addActionListener(listener);
    }

    
    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }
}
