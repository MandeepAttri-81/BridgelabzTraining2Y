import java.util.Scanner;

class BookNode {
    String title, author, genre, availability;
    int bookId;
    BookNode prev, next;

    BookNode(String title, String author, String genre, int bookId, String availability) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.bookId = bookId;
        this.availability = availability;
    }
}

public class Q5_LibraryManagement {
    static BookNode head = null, tail = null;
    static Scanner sc = new Scanner(System.in);

    static BookNode inputBook() {
        sc.nextLine();
        System.out.print("Book title: ");
        String title = sc.nextLine();
        System.out.print("Author: ");
        String author = sc.nextLine();
        System.out.print("Genre: ");
        String genre = sc.nextLine();
        System.out.print("Book ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Availability (Available/Issued): ");
        String availability = sc.nextLine();
        return new BookNode(title, author, genre, id, availability);
    }

    static void addBeginning() {
        BookNode node = inputBook();
        if (head == null) head = tail = node;
        else {
            node.next = head;
            head.prev = node;
            head = node;
        }
        System.out.println("Book added.");
    }

    static void addEnd() {
        BookNode node = inputBook();
        if (head == null) head = tail = node;
        else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        System.out.println("Book added.");
    }

    static void addAtPosition() {
        System.out.print("Position (1-based): ");
        int position = sc.nextInt();
        if (position < 1) {
            System.out.println("Invalid position.");
            return;
        }
        if (position == 1) {
            addBeginning();
            return;
        }

        BookNode temp = head;
        for (int i = 1; i < position - 1 && temp != null; i++) temp = temp.next;
        if (temp == null) {
            System.out.println("Position out of range.");
            return;
        }
        if (temp == tail) {
            addEnd();
            return;
        }

        BookNode node = inputBook();
        node.next = temp.next;
        node.prev = temp;
        temp.next.prev = node;
        temp.next = node;
        System.out.println("Book added.");
    }

    static void removeById() {
        System.out.print("Book ID to remove: ");
        int id = sc.nextInt();
        BookNode temp = head;
        while (temp != null && temp.bookId != id) temp = temp.next;

        if (temp == null) {
            System.out.println("Book not found.");
            return;
        }
        if (temp.prev != null) temp.prev.next = temp.next;
        else head = temp.next;

        if (temp.next != null) temp.next.prev = temp.prev;
        else tail = temp.prev;

        System.out.println("Book removed.");
    }

    static void searchBook() {
        System.out.println("Search by 1. Title  2. Author");
        int choice = sc.nextInt();
        sc.nextLine();
        System.out.print(choice == 1 ? "Title: " : "Author: ");
        String key = sc.nextLine();

        boolean found = false;
        BookNode temp = head;
        while (temp != null) {
            String value = choice == 1 ? temp.title : temp.author;
            if ((choice == 1 || choice == 2) && value.equalsIgnoreCase(key)) {
                printBook(temp);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("No matching book found.");
    }

    static void updateAvailability() {
        System.out.print("Book ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        BookNode temp = head;
        while (temp != null) {
            if (temp.bookId == id) {
                System.out.print("New status (Available/Issued): ");
                temp.availability = sc.nextLine();
                System.out.println("Availability updated.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Book not found.");
    }

    static void displayForward() {
        if (head == null) {
            System.out.println("Library is empty.");
            return;
        }
        BookNode temp = head;
        while (temp != null) {
            printBook(temp);
            temp = temp.next;
        }
    }

    static void displayReverse() {
        if (tail == null) {
            System.out.println("Library is empty.");
            return;
        }
        BookNode temp = tail;
        while (temp != null) {
            printBook(temp);
            temp = temp.prev;
        }
    }

    static int countBooks() {
        int count = 0;
        BookNode temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    static void printBook(BookNode book) {
        System.out.println("ID: " + book.bookId + ", Title: " + book.title
                + ", Author: " + book.author + ", Genre: " + book.genre
                + ", Status: " + book.availability);
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- LIBRARY MANAGEMENT ---");
            System.out.println("1. Add at beginning");
            System.out.println("2. Add at end");
            System.out.println("3. Add at position");
            System.out.println("4. Remove by book ID");
            System.out.println("5. Search by title or author");
            System.out.println("6. Update availability");
            System.out.println("7. Display forward");
            System.out.println("8. Display reverse");
            System.out.println("9. Count books");
            System.out.println("10. Exit");
            System.out.print("Choice: ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1: addBeginning(); break;
                case 2: addEnd(); break;
                case 3: addAtPosition(); break;
                case 4: removeById(); break;
                case 5: searchBook(); break;
                case 6: updateAvailability(); break;
                case 7: displayForward(); break;
                case 8: displayReverse(); break;
                case 9: System.out.println("Total books: " + countBooks()); break;
                case 10: System.out.println("Program ended."); return;
                default: System.out.println("Invalid choice.");
            }
        }
    }
}
