package gui.models;

import java.util.Collection;
import java.util.List;

import gui.CsvReadWrite;
import gui.User;

public class VerifyUserModel {

	 private CsvReadWrite csvReadWrite;

	    public VerifyUserModel() {
	        this.csvReadWrite = new CsvReadWrite();
	    }



	    public void verifyUser(String username) {
	        csvReadWrite.verifyUser(username);
	    }

		public List<User> getUnverifiedUsers() {
			return csvReadWrite.getUnverifiedUsers();
		}
}
