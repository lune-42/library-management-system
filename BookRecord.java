public class BookRecord {
    private String title;
    private int borrowCount;

    public BookRecord(String title) {
        this.title = title;
        this.borrowCount = 0; // initialise borrow count to 0
    }
    public void incrementBorrow() {
        borrowCount++; //implements borrow count
    }
    public String getTitle() {
        return title; //returns title
    }
    public int getBorrowCount() {
     return borrowCount; //returns borrow count
    }
    public String getDetails() {
        return "Title: " + title + " |  Borrows: " + borrowCount;
        //returns title and borrow count
    }
}
