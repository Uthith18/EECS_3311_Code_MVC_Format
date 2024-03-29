package gui.views;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class OrderBookPageView {

	private JFrame frame;
    private JTextField bookField;
    private JComboBox<String> reasonComboBox;
    private JButton orderButton, priorityListButton, dashboardButton;

    public OrderBookPageView() {
        initializeUI();
    }

    private void initializeUI() {
        frame = new JFrame("Order Book Page");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 248);
        frame.getContentPane().setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel librarySystemText = new JLabel("Order Textbook");
        librarySystemText.setBounds(141, 14, 128, 20);
        librarySystemText.setFont(new Font("MV Boli", Font.BOLD, 16));
        panel.add(librarySystemText);

        bookField = new JTextField(20);
        bookField.setBounds(100, 64, 230, 26);
        JLabel label = new JLabel("Book Title:");
        label.setBounds(100, 49, 67, 16);
        panel.add(label);
        panel.add(bookField);

        reasonComboBox = new JComboBox<>(new String[]{"For Class", "Personal/Self-Development"});
        reasonComboBox.setBounds(100, 130, 230, 27);
        JLabel label_1 = new JLabel("Reason for Request:");
        label_1.setBounds(100, 102, 125, 16);
        panel.add(label_1);
        panel.add(reasonComboBox);

        orderButton = new JButton("Order");
        orderButton.setBounds(281, 172, 79, 29);
        panel.add(orderButton);

        priorityListButton = new JButton("Priority List");
        priorityListButton.setBounds(33, 172, 116, 29);
        panel.add(priorityListButton);

        dashboardButton = new JButton("Dashboard");
        dashboardButton.setBounds(157, 172, 112, 29);
        panel.add(dashboardButton);

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public String getBookTitle() {
        return bookField.getText().trim();
    }

    public String getReasonForRequest() {
        return (String) reasonComboBox.getSelectedItem();
    }

    public void addOrderButtonListener(ActionListener listener) {
        orderButton.addActionListener(listener);
    }

    public void addPriorityListButtonListener(ActionListener listener) {
        priorityListButton.addActionListener(listener);
    }

    public void addDashboardButtonListener(ActionListener listener) {
        dashboardButton.addActionListener(listener);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }
    
    public void clearInputs() {
        bookField.setText("");
        reasonComboBox.setSelectedIndex(0); // Resets the combo box to its default state
    }

    public void close() {
        frame.dispose();
    }
}