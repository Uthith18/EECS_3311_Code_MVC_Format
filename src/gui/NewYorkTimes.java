package gui;

import java.util.concurrent.Flow.Subscription;

import javax.swing.JFrame;

public class NewYorkTimes implements SubscriptionStrategy {
    
	public void openSubscriptionPage(JFrame frame) {
        openUrl("https://www.nytimes.com/", frame);
    }
}
