package gui.views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class BookSearchView {

	private JFrame frame;
    private JComboBox<String> searchTypeComboBox;
    private JTextField searchValueTextField;
    private JButton searchButton, dashboardButton;
    private JTable searchResultsTable;
    private DefaultTableModel model;

    public BookSearchView() {
        initializeUI();
    }

    private void initializeUI() {
        frame = new JFrame("Book Search Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(503, 500);
        frame.setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 503, 500);

        JLabel headerLabel = new JLabel("Book Search");
        headerLabel.setFont(new Font("MV Boli", Font.BOLD, 30));
        headerLabel.setBounds(159, 20, 201, 30);
        panel.add(headerLabel);

        JLabel searchByLabel = new JLabel("Search by:");
        searchByLabel.setBounds(50, 70, 80, 25);
        panel.add(searchByLabel);

        searchTypeComboBox = new JComboBox<>();
        searchTypeComboBox.addItem("Genre");
        searchTypeComboBox.addItem("Title");
        searchTypeComboBox.addItem("Author");
        searchTypeComboBox.setBounds(130, 70, 200, 25);
        panel.add(searchTypeComboBox);

        JLabel searchValueLabel = new JLabel("Search value:");
        searchValueLabel.setBounds(50, 110, 100, 25);
        panel.add(searchValueLabel);

        searchValueTextField = new JTextField(20);
        searchValueTextField.setBounds(130, 110, 200, 25);
        panel.add(searchValueTextField);

        searchButton = new JButton("Search");
        searchButton.setBounds(180, 150, 100, 25);
        panel.add(searchButton);

        String[] columnNames = {"ID", "Type", "Title"};
        model = new DefaultTableModel(null, columnNames);
        searchResultsTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(searchResultsTable);
        scrollPane.setBounds(30, 190, 440, 200);
        panel.add(scrollPane);

        dashboardButton = new JButton("Dashboard");
        dashboardButton.setBounds(180, 400, 120, 25);
        panel.add(dashboardButton);

        frame.getContentPane().add(panel);
        frame.setLocationRelativeTo(null); // Center on screen
        frame.setVisible(true);
    }

    public void addSearchButtonActionListener(ActionListener listener) {
        searchButton.addActionListener(listener);
    }

    public void addDashboardButtonActionListener(ActionListener listener) {
        dashboardButton.addActionListener(listener);
    }

    public String getSelectedSearchType() {
        return (String) searchTypeComboBox.getSelectedItem();
    }

    public String getSearchValue() {
        return searchValueTextField.getText();
    }

    public void updateSearchResultsTable(java.util.List<String[]> results) {
        model.setRowCount(0); 
        for (String[] row : results) {
            model.addRow(row);
        }
    }

    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

}

