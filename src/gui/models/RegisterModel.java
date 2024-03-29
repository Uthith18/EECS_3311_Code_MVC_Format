package gui.models;

import gui.CsvReadWrite;
import java.util.ArrayList;

public class RegisterModel {
    private CsvReadWrite csvReadWrite;

    public RegisterModel() {
        csvReadWrite = new CsvReadWrite();
    }

    public boolean registerUser(String email, String password, String firstName, String accountType, ArrayList<String> courses, boolean notify) {
    	boolean validated = false; // initial validation status
    	if (accountType.equalsIgnoreCase("Visitor") || accountType.equalsIgnoreCase("Management Team"))
    	{
    		validated = true;
    	}
        return csvReadWrite.registerUser(email, password, validated, accountType, firstName, notify, courses);
    }
}