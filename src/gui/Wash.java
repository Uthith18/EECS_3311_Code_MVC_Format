package gui;

import java.util.concurrent.Flow.Subscription;

import javax.swing.JFrame;


public class Wash implements SubscriptionStrategy {
    @Override
    public void openSubscriptionPage(JFrame frame) {
        openUrl("https://www.washingtonpost.com/", frame);
    }
}
