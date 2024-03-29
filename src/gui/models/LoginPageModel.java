package gui.models;

import gui.CsvReadWrite;
import gui.User;

public class LoginPageModel {

    private CsvReadWrite csvReadWrite;

    public LoginPageModel() {
        csvReadWrite = new CsvReadWrite();
    }

    public User loginUser(String email, String password) {
        
        return csvReadWrite.loginUser(email, password);
    }
}
