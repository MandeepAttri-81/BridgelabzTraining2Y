import java.util.Scanner;

public class Q7_NumberFormatException {
    public static int generateException(String text) {
        return Integer.parseInt(text);
    }

    public static void handleException(String text) {
        try {
            int number = Integer.parseInt(text);
            System.out.println("Number: " + number);
        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException handled");
        } catch (RuntimeException e) {
            System.out.println("RuntimeException handled");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter text that is not a valid integer: ");
        String text = sc.next();

        try {
            System.out.println("Number: " + generateException(text));
        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException generated and caught in main");
        }

        handleException(text);
        sc.close();
    }
}
