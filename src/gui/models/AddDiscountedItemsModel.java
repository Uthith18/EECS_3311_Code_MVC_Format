package gui.models;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class AddDiscountedItemsModel {
	
	public void addDiscountedItem(String type, String name, String price) {
	try (FileWriter fw = new FileWriter("Discount.csv", true);
            PrintWriter out = new PrintWriter(fw)) {
           out.printf("%s,%s,%s%n", type, name, price);
       } catch (IOException e) {
           e.printStackTrace();
       }

	}
}
