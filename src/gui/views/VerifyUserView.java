package gui.views;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class VerifyUserView {
	
	private JFrame frame;
    private JList<String> userList;
    private DefaultListModel<String> listModel;
    private JButton verifyButton;
    private JButton returnToDashboardButton;

    public VerifyUserView() {
        initializeUI();
    }

    private void initializeUI() {
        frame = new JFrame("Verify Users");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        listModel = new DefaultListModel<>();
        userList = new JList<>(listModel);
        JScrollPane listScrollPane = new JScrollPane(userList);
        frame.getContentPane().add(listScrollPane, BorderLayout.CENTER);

        verifyButton = new JButton("Verify Selected User");
        returnToDashboardButton = new JButton("Return to Dashboard");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(verifyButton);
        buttonPanel.add(returnToDashboardButton);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

    public void addVerifyButtonListener(ActionListener listener) {
        verifyButton.addActionListener(listener);
    }

    public void addReturnToDashboardButtonListener(ActionListener listener) {
        returnToDashboardButton.addActionListener(listener);
    }

    public String getSelectedUser() {
        return userList.getSelectedValue();
    }

    public void setUserList(List<String> users) {
        listModel.clear();
        for (String user : users) {
            listModel.addElement(user);
        }
    }

    public void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }


}
