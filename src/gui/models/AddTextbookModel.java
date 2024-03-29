package gui.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import gui.CsvReadWrite;

public class AddTextbookModel {
	
	CsvReadWrite c = new CsvReadWrite();

    public boolean isCourseInDatabase(String course) {
        File file = new File("Textbook.csv");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length > 1 && values[1].equals(course)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void writeToCSV(String bookTitle, String course, String link) {
        boolean courseExists = isCourseInDatabase(course);
        try (FileWriter fw = new FileWriter("Textbook.csv", true);
             PrintWriter out = new PrintWriter(fw)) {
            out.printf("%s,%s,%s%n", bookTitle, course, link);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        if (courseExists) {
            JOptionPane.showMessageDialog(null, "A Textbook for this course already exists, Added to System, Please Alert Faculty.");
            c.updateFacultyNotifications(course);
            CsvReadWrite csvReadWrite = new CsvReadWrite();
			csvReadWrite.updateFacultyNotifications(course);
        }
    }
}
