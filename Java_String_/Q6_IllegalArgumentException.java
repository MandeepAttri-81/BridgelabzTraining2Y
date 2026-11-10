import java.util.Scanner;

public class Q6_IllegalArgumentException {
    public static String getSubstring(String text, int start, int end) {
        if (start > end) {
            throw new IllegalArgumentException("Start index cannot be greater than end index");
        }

        return text.substring(start, end);
    }

    public static void generateException(String text) {
        getSubstring(text, 4, 2);
    }

    public static void handleException(String text) {
        try {
            getSubstring(text, 4, 2);
        } catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException handled");
        } catch (RuntimeException e) {
            System.out.println("RuntimeException handled");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string with at least 5 characters: ");
        String text = sc.next();

        try {
            generateException(text);
        } catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException generated and caught in main");
        }

        handleException(text);
        sc.close();
    }
}
