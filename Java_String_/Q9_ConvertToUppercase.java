import java.util.Scanner;

public class Q9_ConvertToUppercase {
    public static String convertToUppercase(String text) {
        String result = "";

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (ch >= 'a' && ch <= 'z') {
                ch = (char) (ch - 32);
            }

            result = result + ch;
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

        System.out.print("Enter complete text: ");
        String text = sc.nextLine();

        String userResult = convertToUppercase(text);
        String builtInResult = text.toUpperCase();

        System.out.println("Using charAt(): " + userResult);
        System.out.println("Using toUpperCase(): " + builtInResult);
        System.out.println("Both results are same: " +
                compareStrings(userResult, builtInResult));

        sc.close();
    }
}
