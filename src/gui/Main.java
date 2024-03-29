package gui;

import gui.controllers.LoginPageController;
import gui.models.LoginPageModel;
import gui.views.DashboardPageView;
import gui.views.LoginPageView;

public class Main {
	
	private static User currentUser;
	
    public static void main(String[] args) {
        
    	// We start off on the login Page 
    	LoginPageModel model = new LoginPageModel();
        LoginPageView view = new LoginPageView();
        LoginPageController controller = new LoginPageController(model, view);

    }
    
    // Needed to keep track of current user 
    public static void setCurrentUser(User user) {
	       currentUser = user;
	    }

	    public static User getCurrentUser() {
	        return currentUser;
	    }
}
