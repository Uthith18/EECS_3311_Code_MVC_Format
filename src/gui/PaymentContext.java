package gui;

public class PaymentContext {
    private PaymentState currentState;
    private PaymentFinishedListener listener; 

    // Constructor that accepts a PaymentFinishedListener
    public PaymentContext(PaymentFinishedListener listener) {
        this.listener = listener;
    }

    public void setState(PaymentState state) {
        this.currentState = state;
    }

    public void pay() {
        if (currentState != null) {
            currentState.pay(this);
        } else {
            System.out.println("Payment method not selected.");
        }
    }

    public void paymentFinished(boolean success) {
        if (listener != null) {
            listener.onPaymentFinished(success);
        }
    }
}