package gui.views;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class NewspaperView 
{
	
	private JFrame frame;
    private JComboBox<String> newspaperComboBox;
    private JButton subscribeButton, dashboardButton;

    public NewspaperView() {
        frame = new JFrame("Newspaper Subscription");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(414, 184);
        frame.setLayout(new FlowLayout());

        String[] newspapers = {"New York Times", "The Guardian", "The Washington Post"};
        newspaperComboBox = new JComboBox<>(newspapers);

        subscribeButton = new JButton("Subscribe");
        JLabel lblNewLabel = new JLabel("Which Newspaper Would you like to Read?");

        dashboardButton = new JButton("Dashboard");

        frame.add(lblNewLabel);
        frame.add(newspaperComboBox);
        frame.add(subscribeButton);
        frame.add(dashboardButton);

        frame.setVisible(true);
    }

    public void addSubscribeButtonListener(ActionListener listenForSubscribeButton) {
        subscribeButton.addActionListener(listenForSubscribeButton);
    }

    public void addDashboardButtonListener(ActionListener listenForDashboardButton) {
        dashboardButton.addActionListener(listenForDashboardButton);
    }

    public String getSelectedNewspaper() {
        return (String) newspaperComboBox.getSelectedItem();
    }

    public JFrame getFrame() {
        return frame;
    }
}


