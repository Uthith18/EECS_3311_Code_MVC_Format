package gui.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class RegisterView {
    private JFrame frame;
    private JTextField emailField, firstNameField;
    private JPasswordField passwordField;
    private JComboBox<String> accountTypeComboBox;
    private List<JCheckBox> courseCheckBoxes;
    private JButton registerButton;

    public RegisterView() {
    	courseCheckBoxes = new ArrayList<>();
        initializeUI();
        
    }

    private void initializeUI() {
    	 frame = new JFrame("Registration Page");
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setSize(800, 631);
         frame.getContentPane().setLayout(null);

         JPanel leftPanel = new JPanel();
         leftPanel.setLayout(null);
         leftPanel.setBackground(new Color(232, 12, 46)); // Red background
         leftPanel.setBounds(0, 0, 400, 631);

         JPanel rightPanel = new JPanel();
         rightPanel.setBounds(400, 0, 400, 631);

         // Left Panel with Logo
         ImageIcon imageIcon = new ImageIcon("York-University.png"); // Make sure this path is correct
         Image yorkSize = imageIcon.getImage().getScaledInstance(400, 300, Image.SCALE_SMOOTH);
         JLabel yorkLogo = new JLabel(new ImageIcon(yorkSize));
         yorkLogo.setBounds(0, 132, 400, 300);
         leftPanel.add(yorkLogo);

         // Right Panel with Form
         JLabel librarySystemText = new JLabel("LIBRARY SYSTEM");
         librarySystemText.setBounds(70, 12, 300, 30);
         librarySystemText.setFont(new Font("MV Boli", Font.BOLD, 30));
         librarySystemText.setForeground(new Color(232, 12, 46));

         JLabel registrationLabel = new JLabel("REGISTRATION");
         registrationLabel.setBounds(80, 50, 300, 30);
         registrationLabel.setFont(new Font("MV Boli", Font.BOLD, 30));
         registrationLabel.setForeground(new Color(232, 12, 46));
         
         JLabel email = new JLabel("Enter Email");
         email.setBounds(50, 60, 300, 100);
     	JLabel password = new JLabel("Enter Password");
     	password.setBounds(47, 130, 100, 100);
        
       
     	JLabel firstName = new JLabel("Enter First Name");
     	firstName.setBounds(50, 200, 300, 100);
        

         emailField = new JTextField();
         emailField.setBounds(50, 120, 300, 30);

         passwordField = new JPasswordField();
         passwordField.setBounds(50, 200, 300, 30);

         firstNameField = new JTextField();
         firstNameField.setBounds(50, 266, 300, 30);

         String[] accountTypes = {"Student", "Faculty Member", "Non-Faculty Staff", "Visitor", "Management Team"};
         accountTypeComboBox = new JComboBox<>(accountTypes);
         accountTypeComboBox.setBounds(50, 312, 300, 30);

         registerButton = new JButton("Register");
         registerButton.setBounds(109, 550, 180, 40);
         rightPanel.setLayout(null);
         
         
         
         JCheckBox chckbxNewCheckBox = new JCheckBox("EECS 2020");
         chckbxNewCheckBox.setBounds(82, 410, 128, 23);
         courseCheckBoxes.add(chckbxNewCheckBox);
         rightPanel.add(chckbxNewCheckBox);
         
         JCheckBox chckbxEng = new JCheckBox("ENG 1000");
         chckbxEng.setBounds(211, 410, 128, 23);
         courseCheckBoxes.add(chckbxEng);
         rightPanel.add(chckbxEng);
         
         JCheckBox ENG2000 = new JCheckBox("ENG 2000");
         ENG2000.setBounds(211, 445, 128, 23);
         courseCheckBoxes.add(ENG2000);
         rightPanel.add(ENG2000);
         
         JCheckBox chckbxEecs = new JCheckBox("EECS 3311");
         chckbxEecs.setBounds(82, 433, 128, 48);
         courseCheckBoxes.add(chckbxEecs);
         rightPanel.add(chckbxEecs);
         
         JCheckBox chckbxEng_2 = new JCheckBox("ENG 3000");
         chckbxEng_2.setBounds(211, 480, 128, 23);
         courseCheckBoxes.add(chckbxEng_2);
         rightPanel.add(chckbxEng_2);
         
         JCheckBox chckbxEecs_1 = new JCheckBox("EECS 4411");
         chckbxEecs_1.setBounds(82, 480, 128, 23);
         courseCheckBoxes.add(chckbxEecs_1);
         rightPanel.add(chckbxEecs_1);
         
         JCheckBox chckbxNewCheckBox_1_1 = new JCheckBox("ENG 4000");
         chckbxNewCheckBox_1_1.setBounds(211, 515, 128, 23);
         courseCheckBoxes.add(chckbxNewCheckBox_1_1);
         rightPanel.add(chckbxNewCheckBox_1_1);
         
         JCheckBox chckbxNewCheckBox_1_2 = new JCheckBox("EECS 4413");
         chckbxNewCheckBox_1_2.setBounds(82, 515, 128, 23);
         courseCheckBoxes.add(chckbxNewCheckBox_1_2);
         rightPanel.add(chckbxNewCheckBox_1_2);
         
         JLabel lblWhatCoursesAre = new JLabel("<html> What Courses Are You Taking/Teaching ");
         lblWhatCoursesAre.setBounds(6, 354, 369, 76);
         lblWhatCoursesAre.setHorizontalAlignment(SwingConstants.CENTER);
         lblWhatCoursesAre.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
         rightPanel.add(lblWhatCoursesAre);
         
         

         
         rightPanel.add(librarySystemText);
         rightPanel.add(registrationLabel);
         rightPanel.add(emailField);
         rightPanel.add(passwordField);
         rightPanel.add(firstNameField);
         rightPanel.add(accountTypeComboBox);
         rightPanel.add(registerButton);
         rightPanel.add(firstName);
         rightPanel.add(email);
         rightPanel.add(password);
         

         frame.getContentPane().add(leftPanel);
         frame.getContentPane().add(rightPanel);
         frame.setLocationRelativeTo(null); // Center on screen
         frame.setVisible(true);
     }

    public String getEmail() {
        return emailField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public String getFirstName() {
        return firstNameField.getText();
    }

    public String getAccountType() {
        return (String) accountTypeComboBox.getSelectedItem();
    }

    public ArrayList<String> getSelectedCourses() {
        ArrayList<String> selectedCourses = new ArrayList<>();
        for (JCheckBox checkBox : courseCheckBoxes) {
            if (checkBox.isSelected()) {
                selectedCourses.add(checkBox.getText());
            }
        }
        return selectedCourses;
    }

    public void addRegisterActionListener(ActionListener listener) {
        registerButton.addActionListener(listener);
    }

    public void close() {
        frame.dispose();
    }

    public void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void showSuccessDialog(String message) {
        JOptionPane.showMessageDialog(frame, message, "Registration Successful", JOptionPane.INFORMATION_MESSAGE);
    }

	public boolean getNotifyStatus() {
		return false;
	}
}