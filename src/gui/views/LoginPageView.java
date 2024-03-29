package gui.views;

import javax.swing.JFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginPageView {

	 private JFrame frame;
	    private JTextField emailField;
	    private JPasswordField passwordField;
	    private JButton loginButton;
	    private JButton registerButton;

	    public LoginPageView() {
	        initialize();
	    }

	    private void initialize() {
	        frame = new JFrame("Login Page");
	        frame.setSize(800, 500);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setLayout(null);
	        frame.setResizable(false);

	        JPanel leftPanel = new JPanel();
	        leftPanel.setLayout(null);
	        leftPanel.setBackground(new Color(232, 12, 46)); // Red
	        leftPanel.setBounds(0, 0, 400, 500);

	        JPanel rightPanel = new JPanel();
	        rightPanel.setLayout(null);
	        rightPanel.setBounds(400, 0, 400, 500);

	        ImageIcon imageIcon = new ImageIcon(new ImageIcon("York-University.png").getImage().getScaledInstance(400, 300, Image.SCALE_SMOOTH));
	        JLabel yorkLogo = new JLabel(imageIcon);
	        yorkLogo.setBounds(0, 80, 400, 300);
	        leftPanel.add(yorkLogo);

	        JLabel librarySystemText = new JLabel("LIBRARY SYSTEM");
	        librarySystemText.setFont(new Font("MV Boli", Font.BOLD, 30));
	        librarySystemText.setForeground(new Color(232, 12, 46));
	        librarySystemText.setBounds(75, 20, 300, 30);
	        rightPanel.add(librarySystemText);

	        JLabel loginLabel = new JLabel("LOGIN");
	        loginLabel.setFont(new Font("MV Boli", Font.BOLD, 30));
	        loginLabel.setForeground(new Color(232, 12, 46));
	        loginLabel.setBounds(150, 70, 100, 30);
	        rightPanel.add(loginLabel);

	        JLabel emailLabel = new JLabel("Email:");
	        emailLabel.setBounds(50, 120, 80, 25);
	        rightPanel.add(emailLabel);

	        emailField = new JTextField(20);
	        emailField.setBounds(140, 120, 200, 25);
	        rightPanel.add(emailField);

	        JLabel passwordLabel = new JLabel("Password:");
	        passwordLabel.setBounds(50, 160, 80, 25);
	        rightPanel.add(passwordLabel);

	        passwordField = new JPasswordField(20);
	        passwordField.setBounds(140, 160, 200, 25);
	        rightPanel.add(passwordField);

	        loginButton = new JButton("Login");
	        loginButton.setBounds(50, 200, 90, 25);
	        rightPanel.add(loginButton);

	        JLabel noAccountLabel = new JLabel("I don't have an account");
	        noAccountLabel.setBounds(50, 240, 200, 25);
	        rightPanel.add(noAccountLabel);

	        registerButton = new JButton("Sign Up");
	        registerButton.setBounds(250, 240, 90, 25);
	        rightPanel.add(registerButton);

	        frame.add(leftPanel);
	        frame.add(rightPanel);
	        frame.setLocationRelativeTo(null); // Center the window
	        frame.setVisible(true);
	    }

	    public String getEmail() {
	        return emailField.getText();
	    }

	    public String getPassword() {
	        return new String(passwordField.getPassword());
	    }

	    public void addLoginButtonListener(ActionListener listener) {
	        loginButton.addActionListener(listener);
	    }

	    public void addRegisterButtonListener(ActionListener listener) {
	        registerButton.addActionListener(listener);
	    }

	    public void showErrorMessage(String message) {
	        JOptionPane.showMessageDialog(frame, message);
	    }

	    public void close() {
	        frame.dispose();
	    }
}
