package gui.views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import gui.controllers.DashboardPageController;
import gui.models.DashboardPageModel;

import javax.swing.JComboBox;

/*
 * We used a observer design pattern for this code, due to this unable to convert to MVC model had issues 
 */

interface Observer {
    void update(List<String> selectedCourses);
}

interface Subject {
    void registerObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers();
}

class CourseSelection implements Subject {
    private final List<Observer> observers;
    private final ArrayList<String> selectedCourses;

    CourseSelection() {
        observers = new ArrayList<>();
        selectedCourses = new ArrayList<>();
    }

    public void addCourse(String course) {
        selectedCourses.add(course);
        notifyObservers();
    }

    public void removeCourse(String course) {
        selectedCourses.remove(course);
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(selectedCourses);
        }
    }
}


class TextbookTable implements Observer {
    private final DefaultTableModel model;
    private final JTable table;
    private final HashMap<String, String[]> allTextbooks;

    TextbookTable(DefaultTableModel model, JTable table, HashMap<String, String[]> allTextbooks) {
        this.model = model;
        this.table = table;
        this.allTextbooks = allTextbooks;
    }

    @Override
    public void update(List<String> selectedCourses) {
        model.setRowCount(0); // Clear the table

        for (String course : selectedCourses) {
            String[] textbookInfo = allTextbooks.get(course);
            if (textbookInfo != null) {
                model.addRow(new Object[]{textbookInfo[0], course, textbookInfo[1]});
            }
        }
    }
}


public class BookVirtualCopy{


	public BookVirtualCopy()
	{

		JFrame frmTextbookManagement = new JFrame();
		JPanel right = new JPanel();
		JLabel fullName = new JLabel("Full Name");
	    JButton orderButton = new JButton("Confirm");
			
		frmTextbookManagement.setTitle("Request Virtual Copy"); // title
		frmTextbookManagement.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		frmTextbookManagement.getContentPane().setLayout(null);
		fullName.setBounds(0, 0, 0, 0);   	
		frmTextbookManagement.setResizable(false); // can't change size 
		frmTextbookManagement.setSize(486, 651);
		frmTextbookManagement.getContentPane().add(right);
		right.setLayout(null);
	    right.add(orderButton);
	    
	    
	    // Button
	    JButton btnDashboard = new JButton("Dashboard");
	    btnDashboard.setBounds(376, 6, 100, 30);
	    right.add(btnDashboard);
	    btnDashboard.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	frmTextbookManagement.setVisible(false);
	            DashboardPageView d = new DashboardPageView();
	            DashboardPageModel model = new DashboardPageModel();
	            DashboardPageController controler = new DashboardPageController(d, model);
	        }
	    });
	    
	    JLabel lblNewLabel = new JLabel("<html> What Courses Are You Taking </html>");
	    lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 20));
	    lblNewLabel.setBounds(60, 51, 369, 76);
	    right.add(lblNewLabel);
	    
	    JCheckBox chckbxNewCheckBox = new JCheckBox("EECS 2020");
	    
        CourseSelection courseSelection = new CourseSelection();
        TextbookTable textbookTable = new TextbookTable(new DefaultTableModel(), new JTable(), loadTextbooksFromCSV("Textbook.csv"));
        courseSelection.registerObserver(textbookTable);
        
	    chckbxNewCheckBox.setBounds(79, 127, 128, 23);
	    right.add(chckbxNewCheckBox);
        chckbxNewCheckBox.addActionListener(e -> {
            if (chckbxNewCheckBox.isSelected())
                courseSelection.addCourse("EECS 2020");
            else
                courseSelection.removeCourse("EECS 2020");
        });
	    
        JCheckBox chckbxEng = new JCheckBox("ENG 1000");
        chckbxEng.setBounds(289, 127, 128, 23);
        right.add(chckbxEng);
        chckbxEng.addActionListener(e -> {
            if (chckbxEng.isSelected())
                courseSelection.addCourse("ENG 1000");
            else
                courseSelection.removeCourse("ENG 1000");
        });

        JCheckBox ENG2000 = new JCheckBox("ENG 2000");
        ENG2000.setBounds(289, 162, 128, 23);
        right.add(ENG2000);
        ENG2000.addActionListener(e -> {
            if (ENG2000.isSelected())
                courseSelection.addCourse("ENG 2000");
            else
                courseSelection.removeCourse("ENG 2000");
        });

        JCheckBox chckbxEecs = new JCheckBox("EECS 3311");
        chckbxEecs.setBounds(79, 162, 128, 23);
        right.add(chckbxEecs);
        chckbxEecs.addActionListener(e -> {
            if (chckbxEecs.isSelected())
                courseSelection.addCourse("EECS 3311");
            else
                courseSelection.removeCourse("EECS 3311");
        });

        JCheckBox chckbxEng_2 = new JCheckBox("ENG 3000");
        chckbxEng_2.setBounds(289, 197, 128, 23);
        right.add(chckbxEng_2);
        chckbxEng_2.addActionListener(e -> {
            if (chckbxEng_2.isSelected())
                courseSelection.addCourse("ENG 3000");
            else
                courseSelection.removeCourse("ENG 3000");
        });

        JCheckBox chckbxEecs_1 = new JCheckBox("EECS 4411");
        chckbxEecs_1.setBounds(79, 197, 128, 23);
        right.add(chckbxEecs_1);
        chckbxEecs_1.addActionListener(e -> {
            if (chckbxEecs_1.isSelected())
                courseSelection.addCourse("EECS 4411");
            else
                courseSelection.removeCourse("EECS 4411");
        });

        JCheckBox chckbxNewCheckBox_1_1 = new JCheckBox("ENG 4000");
        chckbxNewCheckBox_1_1.setBounds(289, 232, 128, 23);
        right.add(chckbxNewCheckBox_1_1);
        chckbxNewCheckBox_1_1.addActionListener(e -> {
            if (chckbxNewCheckBox_1_1.isSelected())
                courseSelection.addCourse("ENG 4000");
            else
                courseSelection.removeCourse("ENG 4000");
        });

        JCheckBox chckbxNewCheckBox_1_2 = new JCheckBox("EECS 4413");
        chckbxNewCheckBox_1_2.setBounds(79, 229, 128, 23);
        right.add(chckbxNewCheckBox_1_2);
        chckbxNewCheckBox_1_2.addActionListener(e -> {
            if (chckbxNewCheckBox_1_2.isSelected())
                courseSelection.addCourse("EECS 4413");
            else
                courseSelection.removeCourse("EECS 4413");
        });

		
		frmTextbookManagement.setVisible(true);
		frmTextbookManagement.getContentPane().setBackground(new Color(255,255,255));
		
		// Set up the table for search results
        String[] columnNames = {"Book Title", "Course", "Virtual Copy","End Date"};
        DefaultTableModel model = new DefaultTableModel(null, columnNames);
        JTable searchResultsTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(searchResultsTable);
        scrollPane.setBounds(29, 409, 428, 197);
        right.add(scrollPane);
        
	    orderButton.setBounds(164, 367, 135, 30);
		right.setBounds(0,0,486,625);
		
		JLabel lblSemester = new JLabel("<html> Semester");
		lblSemester.setHorizontalAlignment(SwingConstants.CENTER);
		lblSemester.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblSemester.setBounds(48, 243, 369, 76);
		right.add(lblSemester);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(96, 316, 278, 27);
		right.add(comboBox);
		comboBox.addItem("Summer");
		comboBox.addItem("Winter");
		comboBox.addItem("Fall");
		

        
        orderButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	    	      String semester = (String) comboBox.getSelectedItem();
	    	        String date = null;

	    	        if (semester.equalsIgnoreCase("Summer")) {
	    	            date = "08.2025";
	    	        } else if (semester.equalsIgnoreCase("Winter")) {
	    	            date = "04.2026";
	    	        } else if (semester.equalsIgnoreCase("Fall")) {
	    	            date = "12.2025";
	    	        }

	    		
	    	       HashMap<String, String[]> allTextbooks = loadTextbooksFromCSV("Textbook.csv");
	    		    // Get selected courses
	            ArrayList<String> selectedCourses = new ArrayList<>();
	            if (chckbxNewCheckBox.isSelected()) selectedCourses.add("EECS 2020");
	            if (chckbxEng.isSelected()) selectedCourses.add("ENG 1000");
	            if (ENG2000.isSelected()) selectedCourses.add("ENG 2000");
	            if (chckbxEecs.isSelected()) selectedCourses.add("EECS 3311");
	            if (chckbxEecs_1.isSelected()) selectedCourses.add("EECS 4411");
	            if (chckbxEng_2.isSelected()) selectedCourses.add("ENG 3000");
	            if (chckbxNewCheckBox_1_2.isSelected()) selectedCourses.add("EECS 4413");
	            if (chckbxNewCheckBox_1_1.isSelected()) selectedCourses.add("ENG 4000");
	            
	            DefaultTableModel model = (DefaultTableModel) searchResultsTable.getModel();
	            model.setRowCount(0); // Clear the table
	            
	            // Display textbooks for selected courses
	            for (String course : selectedCourses) {
	                String[] textbookInfo = allTextbooks.get(course);
	                if (textbookInfo != null) {
	                    model.addRow(new Object[]{textbookInfo[0], course, textbookInfo[1], date}); // Add the textbook details with link
	                }
	            }
	            

	            
	    	}
	    	});
        


	}
	
	private HashMap<String, String[]> loadTextbooksFromCSV(String filePath) {
		HashMap<String, String[]> textbooks = new HashMap<>();
	    String line;
	    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
	        while ((line = br.readLine()) != null) {
	            String[] values = line.split(",");
	            
	            if (values.length >= 3) {
	                String title = values[0].trim();
	                String courseCode = values[1].trim();
	                String link = values[2].trim();
	                textbooks.put(courseCode, new String[]{title, link}); // Map course code to title and link
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return textbooks;
	}

}
