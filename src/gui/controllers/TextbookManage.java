package gui.controllers;

import javax.swing.*;

import gui.models.DashboardPageModel;
import gui.models.RegisterModel;
import gui.views.DashboardPageView;
import gui.views.RegisterView;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextbookManage {

    private JFrame frame;
    private JList<String> textbookList;
    private JButton backButton;

    /*
     * This code is not in MVC Format, put the logic and GUI in one code
     */
    public TextbookManage(ArrayList<String> userCourses) {
    
        frame = new JFrame("Matched Textbooks");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);

        // make a J List of textbooks 
        textbookList = new JList<>();
        DefaultListModel<String> listModel = new DefaultListModel<>();
        ArrayList<String> matchedTextbooks = getMatchedTextbooksForUser(userCourses);

        for (String textbook : matchedTextbooks) {
            listModel.addElement(textbook);
        }

        textbookList.setModel(listModel);
        textbookList.setFont(new Font("Serif", Font.PLAIN, 18)); 
        JScrollPane scrollPane = new JScrollPane(textbookList);
        scrollPane.setBounds(0, 0, 500, 333);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 

        // Create the back button
        backButton = new JButton("Back to Dashboard");
        backButton.setBounds(157, 337, 176, 29);
        
        backButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					frame.setVisible(false);
					 DashboardPageView d = new DashboardPageView();
			         DashboardPageModel model = new DashboardPageModel();
			         DashboardPageController controler = new DashboardPageController(d, model);
				}
        });
        backButton.setFont(new Font("Inria Serif", Font.PLAIN, 13));
        backButton.addActionListener(e -> {
            
            frame.dispose();
            
        });
        frame.getContentPane().setLayout(null);

        frame.getContentPane().add(scrollPane);
        frame.getContentPane().add(backButton);

        // Display the window.
        frame.setVisible(true);
    }



    private ArrayList<String> getMatchedTextbooksForUser(ArrayList<String> userCourses) {
        ArrayList<String> matchedTextbooks = new ArrayList<>();
        String filePath = "Textbook.csv"; 
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length > 1 && userCourses.contains(values[1].trim())) {
                    matchedTextbooks.add(values[0] + " - " + values[1] + " - " + values[2]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return matchedTextbooks;
    }
}
