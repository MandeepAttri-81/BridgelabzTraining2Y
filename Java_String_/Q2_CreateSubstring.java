import java.util.Scanner;

public class Q2_CreateSubstring {
    public static String createSubstring(String text, int start, int end) {
        String result = "";

        for (int i = start; i < end; i++) {
            result = result + text.charAt(i);
        }

        return result;
    }

    public static boolean compareStrings(String first, String second) {
        if (first.length() != second.length()) {
            return false;
        }

        for (int i = 0; i < first.length(); i++) {
            if (first.charAt(i) != second.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String text = sc.next();

        System.out.print("Enter start index: ");
        int start = sc.nextInt();

        System.out.print("Enter end index: ");
        int end = sc.nextInt();

        if (start < 0 || end > text.length() || start > end) {
            System.out.println("Invalid indexes");
        } else {
            String userSubstring = createSubstring(text, start, end);
            String builtInSubstring = text.substring(start, end);

            System.out.println("Using charAt(): " + userSubstring);
            System.out.println("Using substring(): " + builtInSubstring);
            System.out.println("Both results are same: " +
                    compareStrings(userSubstring, builtInSubstring));
        }

        sc.close();
    }
}
