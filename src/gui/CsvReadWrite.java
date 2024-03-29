
package gui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.*;


	public class CsvReadWrite {
		
	    private String filePath = "Users.csv";

	    
	    
	    public User loginUser(String username, String password) {
	        try (Scanner scanner = new Scanner(new File(filePath))) {
	            while (scanner.hasNextLine()) {
	                String[] data = scanner.nextLine().split(",");
	                if (data[0].equals(username) && data[1].equals(password)) {
	                    boolean validated = Boolean.parseBoolean(data[2]);
	                    if (validated) { // Proceed only if the user is validated
	                        User user = new User();
	                        user.setUserName(username);
	                        user.setPassword(password);
	                        user.setValidated(validated);
	                        user.setRole(data[3]);
	                        user.setFirstName(data[4]);
	                        user.setNotify(Boolean.parseBoolean(data[5]));

	                        ArrayList<String> coursesList = null;
	                        if (data.length > 6 && !data[6].isEmpty()) {
	                            coursesList = new ArrayList<>(Arrays.asList(data[6].split(";")));
	                        }
	                        user.setCourses(coursesList);
	                        

	                        return user;
	                    }
	                }
	            }
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
	        return null; // Login failed
	    }

	    public boolean registerUser(String username, String password,boolean validated, String role, String userFirstName, boolean notify, ArrayList<String> selectedCourses) {
	        // First, check if the username exists
	        if (loginUser(username, password) != null) {
	            return false; // User already exists
	        } else {
	            // Add new user to CSV
	        	String coursesStr = (selectedCourses != null) ? String.join(";", selectedCourses) : "";
	        	String notifyStr = Boolean.toString(notify);
	        	
	            try (FileWriter fw = new FileWriter(filePath, true);
	                 BufferedWriter bw = new BufferedWriter(fw);
	                 PrintWriter out = new PrintWriter(bw)) {

	            	String validatedStr = Boolean.toString(validated);
	            	
	            	 out.println(username + "," + password + "," + Boolean.toString(validated) + "," + role + "," + userFirstName + "," + notifyStr + "," + coursesStr);
	                return true; // Registration successful
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	        System.out.println("fail");
	        return false; // Registration failed
	    }
	    
	    
	    public List<User> getUnverifiedUsers() {
	        List<User> unverifiedUsers = new ArrayList<>();
	        try (Scanner scanner = new Scanner(new File(filePath))) {
	            while (scanner.hasNextLine()) {
	                String[] data = scanner.nextLine().split(",");
	                boolean validated = Boolean.parseBoolean(data[2]);
	                if (!validated) {
	                    User user = new User();
	                    user.setUserName(data[0]);
	                    user.setPassword(data[1]);
	                    user.setValidated(validated);
	                    user.setRole(data[3]);
	                    user.setFirstName(data[4]);
	                    user.setNotify(Boolean.parseBoolean(data[5]));
	                    ArrayList<String> coursesList = null;
                        if (data.length > 6 && !data[6].isEmpty()) {
                            coursesList = new ArrayList<>(Arrays.asList(data[6].split(";")));
                        }
                        user.setCourses(coursesList);

	                    unverifiedUsers.add(user);
	                }
	            }
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
	        return unverifiedUsers;
	    }

	    public void verifyUser(String username) {
	        List<User> allUsers = new ArrayList<>();
	        boolean userFound = false;

	        // Read all users from the CSV
	        try (Scanner scanner = new Scanner(new File(filePath))) {
	            while (scanner.hasNextLine()) {
	                String[] data = scanner.nextLine().split(",");
	                if (data.length > 5) { 
	                    User user = new User();
	                    user.setUserName(data[0]);
	                    user.setPassword(data[1]);
	                    user.setValidated(Boolean.parseBoolean(data[2]));
	                    user.setRole(data[3]);
	                    user.setFirstName(data[4]);
	                    user.setNotify(Boolean.parseBoolean(data[5]));
	                    ArrayList<String> coursesList = null;
	                    if (data.length > 6 && !data[6].isEmpty()) {
	                        coursesList = new ArrayList<>(Arrays.asList(data[6].split(";")));
	                    }
	                    user.setCourses(coursesList);

	                    if (user.getUserName().equals(username)) {
	                        user.setValidated(true); // Only update the verification status
	                        userFound = true;
	                    }

	                    allUsers.add(user); // Add every user to the list, ensuring no one is left out
	                }
	            }
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }

	        
	        if (userFound) {
	            try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, false))) { // Overwrite the file
	                for (User user : allUsers) {
	                    String coursesStr = user.getCourses() != null ? String.join(";", user.getCourses()) : "";
	                    String line = user.getUserName() + "," +
	                                  user.getPassword() + "," +
	                                  user.isValidated() + "," +
	                                  user.getRole() + "," +
	                                  user.getFirstName() + "," +
	                                  user.isNotify() + "," +
	                                  coursesStr;
	                    writer.println(line);
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    // needed for addTextbook Model
	    public void updateFacultyNotifications(String course) {
	        List<User> users = new ArrayList<>();
	        boolean needsUpdate = false;

	        // Read all users and update notification flag for relevant faculty members
	        try (Scanner scanner = new Scanner(new File(filePath))) {
	            while (scanner.hasNextLine()) {
	                String[] data = scanner.nextLine().split(",");
	                User user = new User();
	                user.setUserName(data[0]);
	                user.setPassword(data[1]);
	                user.setValidated(Boolean.parseBoolean(data[2]));
	                user.setRole(data[3]);
	                user.setFirstName(data[4]);
	                user.setNotify(Boolean.parseBoolean(data[5]));

	                ArrayList<String> coursesList = new ArrayList<>();
	                if (data.length > 6 && !data[6].isEmpty()) {
	                    coursesList = new ArrayList<>(Arrays.asList(data[6].split(";")));
	                }
	                user.setCourses(coursesList);

	                // Check if the user is a faculty member and teaches the course in question
	                if ("Faculty Member".equals(user.getRole()) && coursesList.contains(course)) {
	                    user.setNotify(true); // Set notify to true
	                    needsUpdate = true;
	                }

	                users.add(user);
	            }
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }

	        // Rewrite the file only if updates are necessary
	        if (needsUpdate) {
	            try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
	            	 for (User user : users) {
		                	if (user.getCourses() != null) {
		                    String coursesStr = String.join(";", user.getCourses());
		                    String line = user.getUserName() + "," +
		                                  user.getPassword() + "," +
		                                  user.isValidated() + "," +
		                                  user.getRole() + "," +
		                                  user.getFirstName() + "," +
		                                  user.isNotify() + "," +
		                                  coursesStr;
		                    writer.println(line);
	                }}}
	             catch (IOException e) {
	                e.printStackTrace();
	            }
	            
	            
	        }
	    }
	    

	    
	    }
	
		
	
