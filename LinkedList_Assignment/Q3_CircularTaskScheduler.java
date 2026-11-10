import java.util.Scanner;

class TaskNode {
    int taskId, priority;
    String taskName, dueDate;
    TaskNode next;

    TaskNode(int taskId, String taskName, int priority, String dueDate) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.priority = priority;
        this.dueDate = dueDate;
    }
}

public class Q3_CircularTaskScheduler {
    static TaskNode head = null, current = null;
    static Scanner sc = new Scanner(System.in);

    static TaskNode inputTask() {
        System.out.print("Task ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Task name: ");
        String name = sc.nextLine();
        System.out.print("Priority: ");
        int priority = sc.nextInt();
        sc.nextLine();
        System.out.print("Due date: ");
        String dueDate = sc.nextLine();
        return new TaskNode(id, name, priority, dueDate);
    }

    static void addBeginning() {
        TaskNode node = inputTask();
        if (head == null) {
            head = node;
            node.next = head;
            current = head;
        } else {
            TaskNode tail = head;
            while (tail.next != head) tail = tail.next;
            node.next = head;
            tail.next = node;
            head = node;
        }
        System.out.println("Task added.");
    }

    static void addEnd() {
        TaskNode node = inputTask();
        if (head == null) {
            head = node;
            node.next = head;
            current = head;
        } else {
            TaskNode tail = head;
            while (tail.next != head) tail = tail.next;
            tail.next = node;
            node.next = head;
        }
        System.out.println("Task added.");
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
        if (head == null) {
            System.out.println("List is empty; use position 1.");
            return;
        }

        TaskNode temp = head;
        for (int i = 1; i < position - 1 && temp.next != head; i++) temp = temp.next;
        if (position > 1 && temp.next == head && position > countTasks()) {
            System.out.println("Position out of range.");
            return;
        }

        TaskNode node = inputTask();
        node.next = temp.next;
        temp.next = node;
        System.out.println("Task added.");
    }

    static int countTasks() {
        if (head == null) return 0;
        int count = 0;
        TaskNode temp = head;
        do {
            count++;
            temp = temp.next;
        } while (temp != head);
        return count;
    }

    static void removeById() {
        if (head == null) {
            System.out.println("No tasks.");
            return;
        }
        System.out.print("Task ID to remove: ");
        int id = sc.nextInt();

        TaskNode temp = head, previous = null;
        do {
            if (temp.taskId == id) {
                if (temp == head && temp.next == head) {
                    head = current = null;
                } else {
                    if (previous == null) {
                        TaskNode tail = head;
                        while (tail.next != head) tail = tail.next;
                        head = head.next;
                        tail.next = head;
                    } else {
                        previous.next = temp.next;
                    }
                    if (current == temp) current = temp.next;
                }
                System.out.println("Task removed.");
                return;
            }
            previous = temp;
            temp = temp.next;
        } while (temp != head);

        System.out.println("Task not found.");
    }

    static void viewCurrentAndMoveNext() {
        if (current == null) {
            System.out.println("No tasks.");
            return;
        }
        printTask(current);
        current = current.next;
    }

    static void displayAll() {
        if (head == null) {
            System.out.println("No tasks.");
            return;
        }
        TaskNode temp = head;
        do {
            printTask(temp);
            temp = temp.next;
        } while (temp != head);
    }

    static void searchByPriority() {
        if (head == null) {
            System.out.println("No tasks.");
            return;
        }
        System.out.print("Priority to search: ");
        int priority = sc.nextInt();
        boolean found = false;
        TaskNode temp = head;
        do {
            if (temp.priority == priority) {
                printTask(temp);
                found = true;
            }
            temp = temp.next;
        } while (temp != head);
        if (!found) System.out.println("No task with this priority.");
    }

    static void printTask(TaskNode t) {
        System.out.println("ID: " + t.taskId + ", Name: " + t.taskName
                + ", Priority: " + t.priority + ", Due: " + t.dueDate);
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- CIRCULAR TASK SCHEDULER ---");
            System.out.println("1. Add at beginning");
            System.out.println("2. Add at end");
            System.out.println("3. Add at position");
            System.out.println("4. Remove by task ID");
            System.out.println("5. View current task and move next");
            System.out.println("6. Display all tasks");
            System.out.println("7. Search by priority");
            System.out.println("8. Exit");
            System.out.print("Choice: ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1: addBeginning(); break;
                case 2: addEnd(); break;
                case 3: addAtPosition(); break;
                case 4: removeById(); break;
                case 5: viewCurrentAndMoveNext(); break;
                case 6: displayAll(); break;
                case 7: searchByPriority(); break;
                case 8: System.out.println("Program ended."); return;
                default: System.out.println("Invalid choice.");
            }
        }
    }
}
