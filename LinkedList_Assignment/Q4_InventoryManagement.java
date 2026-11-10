import java.util.Scanner;

class ItemNode {
    String itemName;
    int itemId, quantity;
    double price;
    ItemNode next;

    ItemNode(String itemName, int itemId, int quantity, double price) {
        this.itemName = itemName;
        this.itemId = itemId;
        this.quantity = quantity;
        this.price = price;
    }
}

public class Q4_InventoryManagement {
    static ItemNode head = null;
    static Scanner sc = new Scanner(System.in);

    static ItemNode inputItem() {
        sc.nextLine();
        System.out.print("Item name: ");
        String name = sc.nextLine();
        System.out.print("Item ID: ");
        int id = sc.nextInt();
        System.out.print("Quantity: ");
        int quantity = sc.nextInt();
        System.out.print("Price: ");
        double price = sc.nextDouble();
        return new ItemNode(name, id, quantity, price);
    }

    static void addBeginning() {
        ItemNode node = inputItem();
        node.next = head;
        head = node;
        System.out.println("Item added.");
    }

    static void addEnd() {
        ItemNode node = inputItem();
        if (head == null) head = node;
        else {
            ItemNode temp = head;
            while (temp.next != null) temp = temp.next;
            temp.next = node;
        }
        System.out.println("Item added.");
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
        ItemNode temp = head;
        for (int i = 1; i < position - 1 && temp != null; i++) temp = temp.next;
        if (temp == null) {
            System.out.println("Position out of range.");
            return;
        }
        ItemNode node = inputItem();
        node.next = temp.next;
        temp.next = node;
        System.out.println("Item added.");
    }

    static void removeById() {
        System.out.print("Item ID to remove: ");
        int id = sc.nextInt();
        if (head == null) {
            System.out.println("Inventory is empty.");
            return;
        }
        if (head.itemId == id) {
            head = head.next;
            System.out.println("Item removed.");
            return;
        }
        ItemNode temp = head;
        while (temp.next != null && temp.next.itemId != id) temp = temp.next;
        if (temp.next == null) System.out.println("Item not found.");
        else {
            temp.next = temp.next.next;
            System.out.println("Item removed.");
        }
    }

    static void updateQuantity() {
        System.out.print("Item ID: ");
        int id = sc.nextInt();
        ItemNode temp = head;
        while (temp != null) {
            if (temp.itemId == id) {
                System.out.print("New quantity: ");
                temp.quantity = sc.nextInt();
                System.out.println("Quantity updated.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item not found.");
    }

    static void searchItem() {
        System.out.println("Search by 1. Item ID  2. Item Name");
        int choice = sc.nextInt();
        sc.nextLine();
        boolean found = false;
        ItemNode temp = head;

        if (choice == 1) {
            System.out.print("Item ID: ");
            int id = sc.nextInt();
            while (temp != null) {
                if (temp.itemId == id) {
                    printItem(temp);
                    found = true;
                    break;
                }
                temp = temp.next;
            }
        } else if (choice == 2) {
            System.out.print("Item name: ");
            String name = sc.nextLine();
            while (temp != null) {
                if (temp.itemName.equalsIgnoreCase(name)) {
                    printItem(temp);
                    found = true;
                }
                temp = temp.next;
            }
        } else {
            System.out.println("Invalid choice.");
            return;
        }
        if (!found) System.out.println("Item not found.");
    }

    static void calculateTotalValue() {
        double total = 0;
        ItemNode temp = head;
        while (temp != null) {
            total += temp.price * temp.quantity;
            temp = temp.next;
        }
        System.out.println("Total inventory value: " + total);
    }

    static void sortInventory() {
        if (head == null || head.next == null) {
            System.out.println("Not enough items to sort.");
            return;
        }
        System.out.println("Sort by 1. Name  2. Price");
        int field = sc.nextInt();
        System.out.println("Order 1. Ascending  2. Descending");
        int order = sc.nextInt();
        if ((field != 1 && field != 2) || (order != 1 && order != 2)) {
            System.out.println("Invalid choice.");
            return;
        }

        for (ItemNode i = head; i != null; i = i.next) {
            for (ItemNode j = i.next; j != null; j = j.next) {
                int comparison;
                if (field == 1) comparison = i.itemName.compareToIgnoreCase(j.itemName);
                else comparison = Double.compare(i.price, j.price);

                boolean shouldSwap = (order == 1 && comparison > 0)
                        || (order == 2 && comparison < 0);

                if (shouldSwap) {
                    String name = i.itemName;
                    i.itemName = j.itemName;
                    j.itemName = name;

                    int id = i.itemId;
                    i.itemId = j.itemId;
                    j.itemId = id;

                    int quantity = i.quantity;
                    i.quantity = j.quantity;
                    j.quantity = quantity;

                    double price = i.price;
                    i.price = j.price;
                    j.price = price;
                }
            }
        }
        System.out.println("Inventory sorted.");
    }

    static void display() {
        if (head == null) {
            System.out.println("Inventory is empty.");
            return;
        }
        ItemNode temp = head;
        while (temp != null) {
            printItem(temp);
            temp = temp.next;
        }
    }

    static void printItem(ItemNode item) {
        System.out.println("ID: " + item.itemId + ", Name: " + item.itemName
                + ", Quantity: " + item.quantity + ", Price: " + item.price
                + ", Value: " + (item.quantity * item.price));
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- INVENTORY MANAGEMENT ---");
            System.out.println("1. Add at beginning");
            System.out.println("2. Add at end");
            System.out.println("3. Add at position");
            System.out.println("4. Remove by item ID");
            System.out.println("5. Update quantity");
            System.out.println("6. Search by ID or name");
            System.out.println("7. Calculate total inventory value");
            System.out.println("8. Sort by name or price");
            System.out.println("9. Display inventory");
            System.out.println("10. Exit");
            System.out.print("Choice: ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1: addBeginning(); break;
                case 2: addEnd(); break;
                case 3: addAtPosition(); break;
                case 4: removeById(); break;
                case 5: updateQuantity(); break;
                case 6: searchItem(); break;
                case 7: calculateTotalValue(); break;
                case 8: sortInventory(); break;
                case 9: display(); break;
                case 10: System.out.println("Program ended."); return;
                default: System.out.println("Invalid choice.");
            }
        }
    }
}
