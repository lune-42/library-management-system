import java.util.ArrayList;
import uulib.OutputFile;
import uulib.InputFile;
import java.io.File;
public class LibraryHandler {
    //store all book records
    private ArrayList<BookRecord> books = new ArrayList<>();
    //adds a new book
    public void addBook(String title) {
        title = title.replace(" ", "_"); //allows for multi-word titles
        books.add(new BookRecord(title));
    }
    public int findBook(String title) {
    //a case-insensitive linear search
        for(int i = 0; i < books.size(); i++){
            title = title.replace(" ", "_"); //allows for multi-word titles
            if(books.get(i).getTitle().equalsIgnoreCase(title)){
                return i; //return the index if found
            }
        }
        return -1; //index not found
    }
    //increases the borrow count of the books
    public void borrowBook(int index) {
        if (index >= 0 && index < books.size()) {
            books.get(index).incrementBorrow();
        }
    }
    //shows the popularity of the books based on borrow count
    public void showPopularityReport() {
        if (books.isEmpty()) return;
    //create a copy
        ArrayList<BookRecord> sortedCopy = new ArrayList<>(books);
        //selection sort
        for(int i = 0; i < sortedCopy.size() -1; i++) {
            int maxIndex = i;
            //find the index of highest value
            for (int j = i + 1; j < sortedCopy.size(); j++) {
                if (sortedCopy.get(j).getBorrowCount() > sortedCopy.get(maxIndex).getBorrowCount()) {
                    maxIndex = j;
                }
            }
            //swaps to move into correct position
            BookRecord temp = sortedCopy.get(i);
            sortedCopy.set(i, sortedCopy.get(maxIndex));
            sortedCopy.set(maxIndex, temp);
        }
            //prints results
            System.out.println("--- Popularity Report ---");
            for(BookRecord b : sortedCopy) {
                System.out.println((b.getDetails()));
        }
    }
    //save book data to a txt file called library_data
    public void saveToFile(String filename) {
        OutputFile out = new OutputFile(filename);
        out.println(books.size()); //write number of books
        for(BookRecord b : books) { //write each book's data
            out.println(b.getTitle());
           out.println(b.getBorrowCount());
        }
        out.close();
    }
    //loads the data from the file
    public void loadFromFile(String filename) {
        File f = new File(filename);
        if (f.exists()) {
            InputFile in = new InputFile(filename);
            //reads how many books to expect
            int count = in.readInt();
            //loops the books
            for( int i = 0; i < count; i++) {
                String title = in.readString();
                int borrows = in.readInt();
                BookRecord b = new BookRecord(title);
                for (int j = 0; j < borrows; j++) {
                    b.incrementBorrow();
                }
                books.add(b);
            }
            in.close();
        }
    }
}
