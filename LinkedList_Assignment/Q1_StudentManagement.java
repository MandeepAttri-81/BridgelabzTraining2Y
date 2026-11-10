import java.util.Scanner;

class StudentNode {
    int rollNumber, age;
    String name, grade;
    StudentNode next;

    StudentNode(int rollNumber, String name, int age, String grade) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.next = null;
    }
}

public class Q1_StudentManagement {
    static StudentNode head = null;
    static Scanner sc = new Scanner(System.in);

    static StudentNode inputStudent() {
        System.out.print("Roll Number: ");
        int roll = sc.nextInt();
        sc.nextLine();
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Age: ");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.print("Grade: ");
        String grade = sc.nextLine();
        return new StudentNode(roll, name, age, grade);
    }

    static void insertBeginning() {
        StudentNode node = inputStudent();
        node.next = head;
        head = node;
        System.out.println("Student added.");
    }

    static void insertEnd() {
        StudentNode node = inputStudent();
        if (head == null) {
            head = node;
        } else {
            StudentNode temp = head;
            while (temp.next != null) temp = temp.next;
            temp.next = node;
        }
        System.out.println("Student added.");
    }

    static void insertAtPosition() {
        System.out.print("Position (1-based): ");
        int position = sc.nextInt();
        if (position < 1) {
            System.out.println("Invalid position.");
            return;
        }
        if (position == 1) {
            insertBeginning();
            return;
        }

        StudentNode temp = head;
        for (int i = 1; i < position - 1 && temp != null; i++) {
            temp = temp.next;
        }
        if (temp == null) {
            System.out.println("Position out of range.");
            return;
        }

        StudentNode node = inputStudent();
        node.next = temp.next;
        temp.next = node;
        System.out.println("Student added.");
    }

    static void deleteByRoll() {
        System.out.print("Roll Number to delete: ");
        int roll = sc.nextInt();

        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        if (head.rollNumber == roll) {
            head = head.next;
            System.out.println("Student deleted.");
            return;
        }

        StudentNode temp = head;
        while (temp.next != null && temp.next.rollNumber != roll) {
            temp = temp.next;
        }
        if (temp.next == null) {
            System.out.println("Student not found.");
        } else {
            temp.next = temp.next.next;
            System.out.println("Student deleted.");
        }
    }

    static void searchByRoll() {
        System.out.print("Roll Number to search: ");
        int roll = sc.nextInt();
        StudentNode temp = head;

        while (temp != null) {
            if (temp.rollNumber == roll) {
                printStudent(temp);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Student not found.");
    }

    static void printStudent(StudentNode s) {
        System.out.println("Roll: " + s.rollNumber + ", Name: " + s.name
                + ", Age: " + s.age + ", Grade: " + s.grade);
    }

    static void display() {
        if (head == null) {
            System.out.println("No student records.");
            return;
        }
        StudentNode temp = head;
        while (temp != null) {
            printStudent(temp);
            temp = temp.next;
        }
    }

    static void updateGrade() {
        System.out.print("Roll Number: ");
        int roll = sc.nextInt();
        sc.nextLine();

        StudentNode temp = head;
        while (temp != null) {
            if (temp.rollNumber == roll) {
                System.out.print("New grade: ");
                temp.grade = sc.nextLine();
                System.out.println("Grade updated.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Student not found.");
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- STUDENT RECORD MANAGEMENT ---");
            System.out.println("1. Insert at beginning");
            System.out.println("2. Insert at end");
            System.out.println("3. Insert at position");
            System.out.println("4. Delete by roll number");
            System.out.println("5. Search by roll number");
            System.out.println("6. Display all students");
            System.out.println("7. Update grade");
            System.out.println("8. Exit");
            System.out.print("Choice: ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1: insertBeginning(); break;
                case 2: insertEnd(); break;
                case 3: insertAtPosition(); break;
                case 4: deleteByRoll(); break;
                case 5: searchByRoll(); break;
                case 6: display(); break;
                case 7: updateGrade(); break;
                case 8: System.out.println("Program ended."); return;
                default: System.out.println("Invalid choice.");
            }
        }
    }
}
