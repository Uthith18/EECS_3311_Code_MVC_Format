package gui.views;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AddDiscountedItemsView {
	
	 private JFrame frame;
	    private JComboBox<String> typeField;
	    private JTextField nameField, priceField;
	    private JButton btnAdd;

	    public AddDiscountedItemsView() {
	        initializeUI();
	    }

	    private void initializeUI() {
	        frame = new JFrame("Add Item");
	        JPanel panel = new JPanel();
	        panel.setLayout(null);
	        frame.getContentPane().add(panel);
	        frame.setSize(350, 271);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        panel.add(createLabel("Add Discounted Item", 30, 17, 302, 30, 27));
	        panel.add(createLabel("Type:", 30, 70, 80, 25, 16));
	        panel.add(createLabel("Name:", 30, 110, 80, 25, 16));
	        panel.add(createLabel("Price:", 30, 150, 80, 25, 16));

	        typeField = new JComboBox<>(new String[]{"Book", "DVD"});
	        typeField.setBounds(120, 70, 200, 25);
	        panel.add(typeField);

	        nameField = new JTextField(20);
	        nameField.setBounds(120, 110, 200, 25);
	        panel.add(nameField);

	        priceField = new JTextField(20);
	        priceField.setBounds(120, 150, 200, 25);
	        panel.add(priceField);

	        btnAdd = new JButton("Add");
	        btnAdd.setBounds(140, 190, 100, 30);
	        panel.add(btnAdd);
	    }

	    private JLabel createLabel(String text, int x, int y, int width, int height, int fontSize) {
	        JLabel label = new JLabel(text);
	        label.setBounds(x, y, width, height);
	        label.setFont(new Font("Dialog", Font.BOLD, fontSize));
	        label.setForeground(SystemColor.activeCaptionText);
	        return label;
	    }

	    public void displayMessage(String message) {
	        JOptionPane.showMessageDialog(frame, message);
	    }

	    public void clearFields() {
	        nameField.setText("");
	        priceField.setText("");
	        typeField.setSelectedIndex(0);
	    }

	    public void addAddButtonListener(ActionListener listener) {
	        btnAdd.addActionListener(listener);
	    }

	    public String getType() {
	        return (String) typeField.getSelectedItem();
	    }

	    public String getName() {
	        return nameField.getText();
	    }

	    public String getPrice() {
	        return priceField.getText();
	    }

	    public void setVisible(boolean visible) {
	        frame.setVisible(visible);
	    }
}


