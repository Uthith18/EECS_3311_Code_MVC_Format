package gui.controllers;

import java.util.List;
import java.util.stream.Collectors;

import gui.User;
import gui.models.DashboardPageModel;
import gui.models.VerifyUserModel;
import gui.views.DashboardPageView;
import gui.views.VerifyUserView;

public class VerifyUserController {

	 private VerifyUserModel model;
	    private VerifyUserView view;

	    public VerifyUserController(VerifyUserModel model, VerifyUserView view) {
	        this.model = model;
	        this.view = view;
	        initView();
	    }

	    private void initView() {
	        view.addVerifyButtonListener(e -> verifySelectedUser());
	        view.addReturnToDashboardButtonListener(e -> returnToDashboard());
	        updateViewWithUnverifiedUsers();
	        view.setVisible(true);
	    }

	    private void verifySelectedUser() {
	        String selectedValue = view.getSelectedUser();
	        if (selectedValue != null) {
	            String username = selectedValue.split(" - ")[0]; 
	            model.verifyUser(username);
	            updateViewWithUnverifiedUsers(); // Refresh list
	        } else {
	            view.showErrorDialog("No user selected.");
	        }
	    }

	    private void updateViewWithUnverifiedUsers() {
	        List<String> unverifiedUsersInfo = model.getUnverifiedUsers().stream()
	                .map(this::formatUserInfo)
	                .collect(Collectors.toList());
	        view.setUserList(unverifiedUsersInfo);
	    }

	    private String formatUserInfo(User user) {
	        return user.getUserName() + " - " + user.getRole() + " - " +
	               user.getFirstName() + " "  +
	               " - Verified: " + (user.isValidated() ? "Yes" : "No");
	    }

	    private void returnToDashboard() {
	        view.setVisible(false);
	        DashboardPageView d = new DashboardPageView();
            DashboardPageModel model = new DashboardPageModel();
            DashboardPageController controler = new DashboardPageController(d, model);
	    }
}

