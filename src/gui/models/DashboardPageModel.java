package gui.models;

import gui.Main;

public class DashboardPageModel 
{

	private static String role;
	 
	 public DashboardPageModel()
	 {
		 this.role = Main.getCurrentUser().getRole();
	 }
	 
	 public static String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}
	 

}
