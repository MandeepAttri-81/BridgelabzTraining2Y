import java.util.Scanner;

public class DSAComplexitySolutions {

    // TC-1 and SC-1: Linear Search
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    // TC-2 and SC-2: Iterative Binary Search
    // The array must be sorted before calling this method.
    public static int binarySearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    // TC-3 and SC-3: Merge Sort
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] first = new int[n1];
        int[] second = new int[n2];

        for (int i = 0; i < n1; i++) {
            first[i] = arr[left + i];
        }

        for (int j = 0; j < n2; j++) {
            second[j] = arr[mid + 1 + j];
        }

        int i = 0;
        int j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            if (first[i] <= second[j]) {
                arr[k] = first[i];
                i++;
            } else {
                arr[k] = second[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = first[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = second[j];
            j++;
            k++;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] linearArray = {8, 61, 2, 3, 4, 0};
        System.out.print("Enter target for Linear Search: ");
        int linearTarget = sc.nextInt();
        int linearResult = linearSearch(linearArray, linearTarget);

        if (linearResult == -1) {
            System.out.println("Linear Search: Element not found");
        } else {
            System.out.println("Linear Search: Found at index " + linearResult);
        }

        int[] sortedArray = {0, 2, 3, 4, 8, 61};
        System.out.print("Enter target for Binary Search: ");
        int binaryTarget = sc.nextInt();
        int binaryResult = binarySearch(sortedArray, binaryTarget);

        if (binaryResult == -1) {
            System.out.println("Binary Search: Element not found");
        } else {
            System.out.println("Binary Search: Found at index " + binaryResult);
        }

        int[] mergeArray = {8, 61, 2, 3, 4, 0};
        mergeSort(mergeArray, 0, mergeArray.length - 1);

        System.out.print("Merge Sort result: ");
        for (int value : mergeArray) {
            System.out.print(value + " ");
        }
        System.out.println();

        sc.close();
    }
}

/*
==================== ANSWERS: TIME COMPLEXITY ====================

TC-1: LINEAR SEARCH
Sample array: {8, 61, 2, 3, 4, 0}

Best case:
Target = 8. It is at index 0, so only one comparison is needed.
Time Complexity = O(1).

Worst case:
Target = 0. It is at the last index, so all 6 elements are checked.
If the target is absent, all N elements are also checked.
Time Complexity = O(N).

Average case:
For a target at a random position, approximately (N + 1) / 2
comparisons are needed on average.
Ignoring constants, Time Complexity = O(N).

TC-2: ITERATIVE BINARY SEARCH
Sorted array: {0, 2, 3, 4, 8, 61}, target = 4

Step 1:
low = 0, high = 5
mid = 0 + (5 - 0) / 2 = 2
arr[2] = 3. Since 3 < 4, search the right half.
New range: indices 3 to 5.

Step 2:
low = 3, high = 5
mid = 3 + (5 - 3) / 2 = 4
arr[4] = 8. Since 8 > 4, search the left half.
New range: indices 3 to 3.

Step 3:
low = 3, high = 3
mid = 3
arr[3] = 4. Target found at index 3.

Worst-case search range after each step:
N, N/2, N/4, N/8, ... , 1

After k steps, N / 2^k <= 1.
Therefore 2^k >= N, so k >= log2(N).
Worst-case Time Complexity = O(log N).

TC-3: MERGE SORT
Sample array: {8, 61, 2, 3, 4, 0}

Split tree:
                 [8, 61, 2, 3, 4, 0]
                  /               \
             [8, 61, 2]          [3, 4, 0]
              /      \            /      \
           [8]      [61, 2]     [3]     [4, 0]
                     /   \                /  \
                   [61]  [2]            [4]  [0]

Each level processes a total of N elements during splitting/merging.
There are approximately log2(N) levels.
Recurrence: T(N) = 2T(N/2) + cN
Level 0 work = cN
Level 1 work = 2 * c(N/2) = cN
Level 2 work = 4 * c(N/4) = cN
There are log2(N) levels, so total work = cN log2(N).
Time Complexity = O(N log N).

==================== ANSWERS: SPACE COMPLEXITY ====================

SC-1: LINEAR SEARCH
Iterative Linear Search uses only a few variables (i and target/index).
It does not create another array or data structure.
Auxiliary Space Complexity = O(1).

SC-2: ITERATIVE BINARY SEARCH
Iterative Binary Search uses only low, high, and mid variables.
It does not use recursion or an extra array.
Auxiliary Space Complexity = O(1).

SC-3: MERGE SORT
Merge Sort creates temporary arrays while merging subarrays.
At one time, the temporary arrays together can store N elements.
The recursion stack also uses O(log N) space.
The temporary arrays dominate the total auxiliary space.
Auxiliary Space Complexity = O(N).

Comparison:
Insertion Sort is in-place and uses O(1) auxiliary space.
Typical in-place Quick Sort uses O(log N) average recursion-stack space
(and O(N) in the worst case for an unbalanced recursion).
Merge Sort uses O(N) extra array space, so it generally needs more
auxiliary memory than Insertion Sort and typical in-place Quick Sort.
*/
