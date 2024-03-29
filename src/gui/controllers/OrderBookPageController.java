package gui.controllers;

import java.awt.event.ActionEvent;
import java.util.Set;

import gui.BookRequest;
import gui.RequestQueue;
import gui.RequestType;
import gui.views.OrderBookPageView;

public class OrderBookPageController {
    private OrderBookPageView view;
    private RequestQueue model; 

    public OrderBookPageController(OrderBookPageView view, RequestQueue model) {
        this.view = view;
        this.model = model;
        this.view.addOrderButtonListener(this::placeOrder);
        this.view.addPriorityListButtonListener(this::showPriorityList);
        this.view.addDashboardButtonListener(e -> view.close()); 
    }

    // When button is pressed 
    private void placeOrder(ActionEvent e) {
        String bookTitle = view.getBookTitle();
        String reason = view.getReasonForRequest();
        RequestType type = "For Class".equals(reason) ? RequestType.COURSE : RequestType.SELF_IMPROVEMENT;

        BookRequest request = new BookRequest(bookTitle, type);
        model.addRequest(request);
        model.refreshAndSaveToCSV("queue.csv");

        view.showMessage("Your request has been placed.");
        view.clearInputs();
    }

    // when priorityList Button is Pressed 
    private void showPriorityList(ActionEvent e) {
        Set<BookRequest> requests = model.loadRequestsFromCSV("queue.csv");
        StringBuilder listContent = new StringBuilder("Priority List:\n");
        for (BookRequest request : requests) {
            listContent.append(request.getBookTitle()).append(" - ").append(request.getRequestType()).append("\n");
        }
        view.showMessage(listContent.toString());
    }
}