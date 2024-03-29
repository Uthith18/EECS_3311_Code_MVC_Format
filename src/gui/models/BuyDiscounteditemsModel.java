package gui.models;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BuyDiscounteditemsModel 
{
	
	public List<String> loadItemsFromCSV(String filePath) {
        List<String> items = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                items.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return items;
    }

    public void removeItemFromCSV(String itemToRemove, String filePath) {
        List<String> itemsList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.equals(itemToRemove)) {
                    itemsList.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter fw = new FileWriter(filePath, false)) {
            for (String item : itemsList) {
                fw.write(item + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
