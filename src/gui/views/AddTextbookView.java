package gui.views;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AddTextbookView {

	    private JFrame frame;
	    private JTextField bookField, linkField;
	    private JComboBox<String> courseField;
	    private JButton addTextbookButton, dashboardButton;

	    public AddTextbookView() {
	        initializeUI();
	    }

	    private void initializeUI() {
	        frame = new JFrame("Add Textbook");
	        frame.setSize(400, 500);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setLayout(null);

	        JPanel panel = new JPanel();
	        panel.setLayout(null);
	        panel.setBounds(0, 0, 400, 500);

	        JLabel lblAddItem = new JLabel("Add Textbook");
	        lblAddItem.setFont(new Font("MV Boli", Font.BOLD, 30));
	        lblAddItem.setBounds(76, 20, 251, 30);
	        panel.add(lblAddItem);

	        JLabel lblBookTitle = new JLabel("Textbook Title:");
	        lblBookTitle.setBounds(47, 70, 300, 25);
	        panel.add(lblBookTitle);

	        bookField = new JTextField(20);
	        bookField.setBounds(47, 100, 300, 25);
	        panel.add(bookField);

	        JLabel lblCourse = new JLabel("Course:");
	        lblCourse.setBounds(47, 140, 134, 25);
	        panel.add(lblCourse);

	        courseField = new JComboBox<>();
	        courseField.setBounds(47, 170, 300, 25);
	        String[] courses = {"EECS 2020", "EECS 3311", "EECS 4411", "EECS 4413", "ENG 1000", "ENG 2000", "ENG 3000", "ENG 4000"};
	        for (String course : courses) {
	            courseField.addItem(course);
	        }
	        panel.add(courseField);

	        JLabel lblPDF = new JLabel("Virtual Link:");
	        lblPDF.setBounds(47, 210, 300, 25);
	        panel.add(lblPDF);

	        linkField = new JTextField(20);
	        linkField.setBounds(47, 240, 300, 25);
	        panel.add(linkField);

	        addTextbookButton = new JButton("Add");
	        addTextbookButton.setBounds(150, 280, 100, 30);
	        panel.add(addTextbookButton);

	        dashboardButton = new JButton("Dashboard");
	        dashboardButton.setBounds(150, 320, 100, 30);
	        panel.add(dashboardButton);

	        frame.add(panel);
	        frame.setLocationRelativeTo(null); // Center the window
	    }

	    public void addAddTextbookActionListener(ActionListener listener) {
	        addTextbookButton.addActionListener(listener);
	    }

	    public void addDashboardActionListener(ActionListener listener) {
	        dashboardButton.addActionListener(listener);
	    }

	    public String getBookTitle() {
	        return bookField.getText();
	    }

	    public String getSelectedCourse() {
	        return (String) courseField.getSelectedItem();
	    }

	    public String getLink() {
	        return linkField.getText();
	    }

	    public void displayMessage(String message) {
	        JOptionPane.showMessageDialog(frame, message);
	    }

	    public void setVisible(boolean visible) {
	        frame.setVisible(visible);
	    }
}


