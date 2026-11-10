import java.util.Scanner;

public class Q1_CompareStrings {
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

        System.out.print("Enter first string: ");
        String first = sc.next();

        System.out.print("Enter second string: ");
        String second = sc.next();

        boolean userResult = compareStrings(first, second);
        boolean builtInResult = first.equals(second);

        System.out.println("Using charAt(): " + userResult);
        System.out.println("Using equals(): " + builtInResult);
        System.out.println("Both results are same: " + (userResult == builtInResult));

        sc.close();
    }
}
