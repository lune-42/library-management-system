import uulib.Console;
public class LibraryApp {
    public static void main(String[] args) {
        LibraryHandler handler = new LibraryHandler();
        String file = "library_data.txt";
// Load existing data on startup
        handler.loadFromFile(file);
        int choice = 0;
        while (choice != 5) {
            System.out.println("\n--- Library Management Menu ---");
            System.out.println("1. Add Book | 2. Borrow | 3. Search | 4. Popularity Report | 5.Exit");
                    choice = Console.getInt("Selection: ");
            if (choice == 1) {
// Use .trim() to prevent leading/trailing space errors
                String title = Console.getString("Enter Title: ").trim();
                handler.addBook(title);
            } else if (choice == 2) {
                String title = Console.getString("Book to borrow: ").trim();
// Find the index using your Linear Search implementation
                int idx = handler.findBook(title);
                if (idx != -1) {
                    handler.borrowBook(idx);
                    System.out.println("Success! Borrow count updated.");
                } else {
                    System.out.println("Error: '" + title + "' not found.");
                }
            } else if (choice == 3) {
                String title = Console.getString("Search Title: ").trim();
                int idx = handler.findBook(title);
                if (idx != -1) {
                    System.out.println("Found at index: " + idx);
                } else {
                    System.out.println("Book not found.");
                }
            } else if (choice == 4) {
// Display the non-destructive ranked report
                handler.showPopularityReport();
            }
        }
// Save history before closing
        System.out.println("Saving data...");
        handler.saveToFile(file);
        System.out.println("Goodbye!");
    }
}