import java.util.Scanner;

class ProcessNode {
    int processId;
    int burstTime;
    int remainingTime;
    int priority;
    int completionTime;
    ProcessNode next;

    ProcessNode(int processId, int burstTime, int priority) {
        this.processId = processId;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.priority = priority;
    }
}

public class Q6_RoundRobinScheduling {
    static ProcessNode head = null;
    static Scanner sc = new Scanner(System.in);

    static void addProcess() {
        System.out.print("Process ID: ");
        int id = sc.nextInt();
        System.out.print("Burst Time: ");
        int burst = sc.nextInt();
        System.out.print("Priority: ");
        int priority = sc.nextInt();

        if (burst <= 0) {
            System.out.println("Burst time must be greater than zero.");
            return;
        }

        ProcessNode node = new ProcessNode(id, burst, priority);

        if (head == null) {
            head = node;
            node.next = head;
        } else {
            ProcessNode tail = head;
            while (tail.next != head) tail = tail.next;
            tail.next = node;
            node.next = head;
        }
        System.out.println("Process added.");
    }

    static void displayQueue() {
        if (head == null) {
            System.out.println("No processes in the queue.");
            return;
        }
        ProcessNode temp = head;
        System.out.println("Current circular queue (ID: remaining burst):");
        do {
            System.out.println(temp.processId + ": " + temp.remainingTime);
            temp = temp.next;
        } while (temp != head);
    }

    static void removeProcess(int id) {
        if (head == null) return;

        ProcessNode current = head;
        ProcessNode previous = null;

        do {
            if (current.processId == id) {
                if (current.next == current) {
                    head = null;
                } else {
                    if (previous == null) {
                        ProcessNode tail = head;
                        while (tail.next != head) tail = tail.next;
                        head = head.next;
                        tail.next = head;
                    } else {
                        previous.next = current.next;
                    }
                }
                return;
            }
            previous = current;
            current = current.next;
        } while (current != head);
    }

    static void simulateRoundRobin() {
        if (head == null) {
            System.out.println("Add processes first.");
            return;
        }

        System.out.print("Enter time quantum: ");
        int quantum = sc.nextInt();
        if (quantum <= 0) {
            System.out.println("Time quantum must be greater than zero.");
            return;
        }

        int currentTime = 0;
        int totalBurst = 0;
        int processCount = 0;

        ProcessNode temp = head;
        do {
            totalBurst += temp.burstTime;
            processCount++;
            temp = temp.next;
        } while (temp != head);

        int[] ids = new int[processCount];
        int[] bursts = new int[processCount];
        int[] completion = new int[processCount];

        temp = head;
        for (int i = 0; i < processCount; i++) {
            ids[i] = temp.processId;
            bursts[i] = temp.burstTime;
            temp = temp.next;
        }

        ProcessNode current = head;

        System.out.println("\n--- ROUND ROBIN EXECUTION ---");

        while (head != null) {
            int executed = Math.min(quantum, current.remainingTime);
            current.remainingTime -= executed;
            currentTime += executed;

            System.out.println("Process " + current.processId
                    + " executes for " + executed + " unit(s).");

            if (current.remainingTime == 0) {
                for (int i = 0; i < processCount; i++) {
                    if (ids[i] == current.processId && completion[i] == 0) {
                        completion[i] = currentTime;
                        break;
                    }
                }

                int finishedId = current.processId;
                ProcessNode nextProcess = current.next;

                if (current.next == current) {
                    head = null;
                } else {
                    ProcessNode previous = current;
                    while (previous.next != current) previous = previous.next;
                    previous.next = current.next;
                    if (head == current) head = current.next;
                }

                System.out.println("Process " + finishedId + " completed.");
                if (head != null) current = nextProcess;
            } else {
                current = current.next;
            }

            displayQueue();
            System.out.println("---------------------------");
        }

        double totalWaiting = 0;
        double totalTurnaround = 0;

        System.out.println("\n--- PROCESS TIMES ---");
        System.out.println("ID\tBurst\tCompletion\tWaiting\tTurnaround");

        for (int i = 0; i < processCount; i++) {
            int turnaround = completion[i]; // All processes arrive at time 0.
            int waiting = turnaround - bursts[i];

            totalWaiting += waiting;
            totalTurnaround += turnaround;

            System.out.println(ids[i] + "\t" + bursts[i] + "\t"
                    + completion[i] + "\t\t" + waiting + "\t" + turnaround);
        }

        System.out.println("Average waiting time: " + (totalWaiting / processCount));
        System.out.println("Average turnaround time: " + (totalTurnaround / processCount));
        System.out.println("All processes completed.");
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- ROUND ROBIN CPU SCHEDULING ---");
            System.out.println("1. Add process");
            System.out.println("2. Remove process by ID");
            System.out.println("3. Simulate Round Robin");
            System.out.println("4. Display process queue");
            System.out.println("5. Exit");
            System.out.print("Choice: ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1: addProcess(); break;
                case 2:
                    System.out.print("Process ID to remove: ");
                    removeProcess(sc.nextInt());
                    break;
                case 3: simulateRoundRobin(); break;
                case 4: displayQueue(); break;
                case 5: System.out.println("Program ended."); return;
                default: System.out.println("Invalid choice.");
            }
        }
    }
}
