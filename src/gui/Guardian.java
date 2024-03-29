package gui;

import java.util.concurrent.Flow.Subscription;

import javax.swing.JFrame;

public class Guardian implements SubscriptionStrategy {
    public void openSubscriptionPage(JFrame frame) {
        openUrl("https://www.theguardian.com/international", frame);
    }
}
