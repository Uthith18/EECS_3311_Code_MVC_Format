package gui.controllers;

import gui.models.RegisterModel;
import gui.views.RegisterView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RegisterController {
    private RegisterModel model;
    private RegisterView view;

    public RegisterController(RegisterModel model, RegisterView view) {
        this.model = model;
        this.view = view;
        this.view.addRegisterActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doRegistration();
            }
        });
    }

    private void doRegistration() {
        String email = view.getEmail();
        String password = String.valueOf(view.getPassword());
        String firstName = view.getFirstName();
        String accountType = (String) view.getAccountType();
        ArrayList<String> courses = view.getSelectedCourses();
        boolean notify = view.getNotifyStatus();

        boolean success = model.registerUser(email, password, firstName, accountType, courses, notify);
        if (success && ( accountType.equalsIgnoreCase("Management Team") || accountType.equalsIgnoreCase("Vistor"))) {
            view.showErrorDialog("Registration successful");
            view.close();
        } else {
            view.showErrorDialog("Please Talk to Management Team Member to get Verified");
        }
    }
    
    
}
