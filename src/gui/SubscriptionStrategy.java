package gui;

import java.awt.Desktop;
import java.net.URI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public interface SubscriptionStrategy {
    void openSubscriptionPage(JFrame frame);
    
    default void openUrl(String urlString, JFrame frame) {
        
        try {
            Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
            if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
                desktop.browse(new URI(urlString));
            } else {
                JOptionPane.showMessageDialog(frame, "Desktop is not supported, cannot open the browser", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Failed to open the website", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
