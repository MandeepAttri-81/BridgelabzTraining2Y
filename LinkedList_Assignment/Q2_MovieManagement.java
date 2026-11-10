import java.util.Scanner;

class MovieNode {
    String title, director;
    int year;
    double rating;
    MovieNode prev, next;

    MovieNode(String title, String director, int year, double rating) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.rating = rating;
    }
}

public class Q2_MovieManagement {
    static MovieNode head = null, tail = null;
    static Scanner sc = new Scanner(System.in);

    static MovieNode inputMovie() {
        sc.nextLine();
        System.out.print("Movie title: ");
        String title = sc.nextLine();
        System.out.print("Director: ");
        String director = sc.nextLine();
        System.out.print("Year of release: ");
        int year = sc.nextInt();
        System.out.print("Rating: ");
        double rating = sc.nextDouble();
        return new MovieNode(title, director, year, rating);
    }

    static void addBeginning() {
        MovieNode node = inputMovie();
        if (head == null) head = tail = node;
        else {
            node.next = head;
            head.prev = node;
            head = node;
        }
        System.out.println("Movie added.");
    }

    static void addEnd() {
        MovieNode node = inputMovie();
        if (head == null) head = tail = node;
        else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        System.out.println("Movie added.");
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

        MovieNode temp = head;
        for (int i = 1; i < position - 1 && temp != null; i++) temp = temp.next;
        if (temp == null) {
            System.out.println("Position out of range.");
            return;
        }
        if (temp == tail) {
            addEnd();
            return;
        }

        MovieNode node = inputMovie();
        node.next = temp.next;
        node.prev = temp;
        temp.next.prev = node;
        temp.next = node;
        System.out.println("Movie added.");
    }

    static void removeByTitle() {
        sc.nextLine();
        System.out.print("Movie title to remove: ");
        String title = sc.nextLine();
        MovieNode temp = head;

        while (temp != null && !temp.title.equalsIgnoreCase(title)) temp = temp.next;
        if (temp == null) {
            System.out.println("Movie not found.");
            return;
        }

        if (temp.prev != null) temp.prev.next = temp.next;
        else head = temp.next;

        if (temp.next != null) temp.next.prev = temp.prev;
        else tail = temp.prev;

        System.out.println("Movie removed.");
    }

    static void searchMovie() {
        System.out.println("Search by 1. Director  2. Rating");
        int choice = sc.nextInt();
        sc.nextLine();
        boolean found = false;
        MovieNode temp = head;

        if (choice == 1) {
            System.out.print("Director: ");
            String director = sc.nextLine();
            while (temp != null) {
                if (temp.director.equalsIgnoreCase(director)) {
                    printMovie(temp);
                    found = true;
                }
                temp = temp.next;
            }
        } else if (choice == 2) {
            System.out.print("Rating: ");
            double rating = sc.nextDouble();
            while (temp != null) {
                if (Double.compare(temp.rating, rating) == 0) {
                    printMovie(temp);
                    found = true;
                }
                temp = temp.next;
            }
        } else {
            System.out.println("Invalid choice.");
            return;
        }
        if (!found) System.out.println("No matching movie found.");
    }

    static void printMovie(MovieNode m) {
        System.out.println("Title: " + m.title + ", Director: " + m.director
                + ", Year: " + m.year + ", Rating: " + m.rating);
    }

    static void displayForward() {
        if (head == null) {
            System.out.println("No movies.");
            return;
        }
        MovieNode temp = head;
        while (temp != null) {
            printMovie(temp);
            temp = temp.next;
        }
    }

    static void displayReverse() {
        if (tail == null) {
            System.out.println("No movies.");
            return;
        }
        MovieNode temp = tail;
        while (temp != null) {
            printMovie(temp);
            temp = temp.prev;
        }
    }

    static void updateRating() {
        sc.nextLine();
        System.out.print("Movie title: ");
        String title = sc.nextLine();
        MovieNode temp = head;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {
                System.out.print("New rating: ");
                temp.rating = sc.nextDouble();
                System.out.println("Rating updated.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Movie not found.");
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- MOVIE MANAGEMENT ---");
            System.out.println("1. Add at beginning");
            System.out.println("2. Add at end");
            System.out.println("3. Add at position");
            System.out.println("4. Remove by movie title");
            System.out.println("5. Search by director or rating");
            System.out.println("6. Display forward");
            System.out.println("7. Display reverse");
            System.out.println("8. Update rating");
            System.out.println("9. Exit");
            System.out.print("Choice: ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1: addBeginning(); break;
                case 2: addEnd(); break;
                case 3: addAtPosition(); break;
                case 4: removeByTitle(); break;
                case 5: searchMovie(); break;
                case 6: displayForward(); break;
                case 7: displayReverse(); break;
                case 8: updateRating(); break;
                case 9: System.out.println("Program ended."); return;
                default: System.out.println("Invalid choice.");
            }
        }
    }
}
