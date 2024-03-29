package gui;

import java.util.Objects;

public class BookRequest {

		// needed for Order Book Page, Design pattern 
	    private String bookTitle;
	    private RequestType requestType;

	    public BookRequest(String bookTitle, RequestType requestType) {
	        this.setBookTitle(bookTitle);
	        this.setRequestType(requestType);
	    }

		public String getBookTitle() {
			return bookTitle;
		}

		public void setBookTitle(String bookTitle) {
			this.bookTitle = bookTitle;
		}

		public RequestType getRequestType() {
			return requestType;
		}

		public void setRequestType(RequestType requestType) {
			this.requestType = requestType;
		}

		 public String toCSVString() {
		        return bookTitle + "," + requestType;
		    }
		 
		 @Override
		    public boolean equals(Object o) {
		        if (this == o) return true;
		        if (o == null || getClass() != o.getClass()) return false;
		        BookRequest that = (BookRequest) o;
		        return bookTitle.equals(that.bookTitle) && requestType == that.requestType;
		    }

		    @Override
		    public int hashCode() {
		        return Objects.hash(bookTitle, requestType);
		    }
	}



