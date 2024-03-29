package gui;

public class Item {

	    private int itemId;
	    private String type;
	    private String name;
	    private String location;
	    private int copiesAvailable;
	    private String author;
	    private String genre;

	    // Constructor
	    public Item(int itemId, String type, String name, String location, int copiesAvailable, String author, String genre) {
	        this.setItemId(itemId);
	        this.setType(type);
	        this.setName(name);
	        this.setLocation(location);
	        this.setCopiesAvailable(copiesAvailable);
	        this.setAuthor(author);
	        this.setGenre(genre);
	    }
	    
	    public String toCsvString() {
	        return itemId + "," + type + "," + name + "," + location + "," +
	               copiesAvailable + "," + author + "," + genre;
	    }
	    
	    public String toString() {
	        return this.name + " (" + this.type + ") - Copies: " + this.copiesAvailable;
	    }
	    

		public int getCopiesAvailable() {
			
			return copiesAvailable;
		}

		public int getItemId() {
			return itemId;
		}

		public void setItemId(int itemId) {
			this.itemId = itemId;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public void setCopiesAvailable(int copiesAvailable) {
			this.copiesAvailable = copiesAvailable;
		}

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		public String getGenre() {
			return genre;
		}

		public void setGenre(String genre) {
			this.genre = genre;
		}

}
