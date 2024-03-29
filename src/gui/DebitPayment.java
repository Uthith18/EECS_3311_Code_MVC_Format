package gui;

import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DebitPayment implements PaymentState {
    
    
    public void pay(PaymentContext context) {
        // Create and display a GUI for debit card details
        JFrame frame = new JFrame("Debit Payment");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(new Dimension(300, 200));
        frame.setLayout(new GridLayout(4, 2));

        // Card number
        frame.add(new JLabel("Card Number:"));
        JTextField cardNumberField = new JTextField();
        frame.add(cardNumberField);

        // Cardholder's name
        frame.add(new JLabel("Cardholder's Name:"));
        JTextField nameField = new JTextField();
        frame.add(nameField);

        // CVV
        frame.add(new JLabel("CVV:"));
        JTextField cvvField = new JTextField();
        frame.add(cvvField);

        // Submit button
        JButton submitButton = new JButton("Submit");
        frame.add(submitButton);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Payment Accepted");
                
                context.paymentFinished(true);
                
                frame.dispose();
            }
        });

        frame.add(new JLabel());

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}