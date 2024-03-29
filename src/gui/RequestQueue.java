package gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

public class RequestQueue {
	 private static RequestQueue instance;
	 private Queue<BookRequest> queue;

    // Comparator to prioritize COURSE requests over SELF_IMPROVEMENT
    private Comparator<BookRequest> comparator = (r1, r2) -> r1.getRequestType().compareTo(r2.getRequestType());

    private RequestQueue() {
        queue = new PriorityQueue<>(comparator);
    }

    public static synchronized RequestQueue getInstance() {
        if (instance == null) {
            instance = new RequestQueue();
        }
        return instance;
    }
    
    public void addRequest(BookRequest request) {
        queue.offer(request);
    }

    public BookRequest processNextRequest() {
        return queue.poll();
    }

	
    public void saveRequestsToCSV(String filePath) {
        // Load existing requests to prevent duplication
        Set<BookRequest> allRequests = new LinkedHashSet<>(loadRequestsFromCSV(filePath));
        allRequests.addAll(queue);

        // Now write all the requests back to the CSV file, overwriting it
        try (PrintWriter out = new PrintWriter(new FileWriter(filePath, false))) {
            out.println("BookTitle,RequestType"); // Header
            for (BookRequest request : allRequests) {
                out.println(request.toCSVString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Set<BookRequest> loadRequestsFromCSV(String filePath) {
        Set<BookRequest> requests = new LinkedHashSet<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return requests;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 2) {
                    BookRequest request = new BookRequest(data[0], RequestType.valueOf(data[1]));
                    requests.add(request); // Add to the set, duplicates will be ignored
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return requests;
    }
    
    public void refreshAndSaveToCSV(String filePath) {
        // Load existing requests and merge them with the current queue
        Set<BookRequest> allRequests = new LinkedHashSet<>(loadRequestsFromCSV(filePath));
        allRequests.addAll(queue);

        // Sort the requests, putting COURSE first
        List<BookRequest> sortedRequests = allRequests.stream()
            .sorted(comparator)
            .collect(Collectors.toList());

        // Now write the sorted requests back to the CSV file, overwriting it
        try (PrintWriter out = new PrintWriter(new FileWriter(filePath, false))) { 
            out.println("BookTitle,RequestType"); // Header, getting issues with macbook, so needed this one 
            for (BookRequest request : sortedRequests) {
                out.println(request.toCSVString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}