package gui.controllers;

import gui.Main;
import gui.User;
import gui.models.DashboardPageModel;
import gui.models.LoginPageModel;
import gui.controllers.DashboardPageController;
import gui.models.RegisterModel;
import gui.views.DashboardPageView;
import gui.views.LoginPageView;
import gui.views.RegisterView;

public class LoginPageController {

    private LoginPageModel model;
    private LoginPageView view;

    public LoginPageController(LoginPageModel model, LoginPageView view) {
        this.model = model;
        this.view = view;
        initView();
    }

    public void initView() {
        view.addLoginButtonListener(e -> performLogin());
        view.addRegisterButtonListener(e -> openRegistrationPage());
    }

    private void performLogin() {
        String email = view.getEmail();
        String password = view.getPassword();
        User user = model.loginUser(email, password);

        if (user != null) {
            Main.setCurrentUser(user);
            view.showErrorMessage("Login Successful!");
            DashboardPageView d = new DashboardPageView();
            DashboardPageModel model = new DashboardPageModel();
            DashboardPageController controler = new DashboardPageController(d, model);
           
            view.close();
            
        } else {
            view.showErrorMessage("Login Failed. Incorrect email, password or not verified.");
        }
    }

    private void openRegistrationPage() {
        view.close();
        RegisterModel registerModel = new RegisterModel();
        RegisterView registerView = new RegisterView();
        new RegisterController(registerModel, registerView);
    }
}
