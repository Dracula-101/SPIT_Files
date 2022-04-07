import java.util.Arrays;
import java.util.Scanner;

public class subset_sum_backtrack {
    static int total_nodes = 0;

    // Subset sum problem
    // Given a set of numbers, find if there is a subset of the given set with sum
    // equal to given number.
    // Input: set[] = {3, 34, 4, 12, 5, 2}, sum = 9
    // Output: True //There is a subset (4, 5) with sum 9.
    static void subset_sum(long set[], long subSet[], int size, int subSize, long total, int l, long sum) {
        total_nodes++;
        if (sum == total) {
            displaySubset(subSet, subSize);
            subset_sum(set, subSet, size, subSize - 1, total - set[l], l + 1, sum);
            return;
        } else {
            for (int i = l; i < size; i++) {
                subSet[subSize] = set[i];
                subset_sum(set, subSet, size, subSize + 1, total + set[i], i + 1, sum);
            }
        }
    }

    static void subsetSum(long set[], long subSet[], long n, int subSize, long total, int l, long sum) {
        if (total == sum) {
            // Print the subset
            displaySubset(subSet, subSize);
            // for other subsets
            subsetSum(set, subSet, n, subSize, total - set[l], l + 1, sum);
            return;
        } else {

            for (int i = l; i < n; i++) {
                // find node along breadth
                subSet[subSize] = set[i];
                // do for next node in depth
                subsetSum(set, subSet, n, subSize + 1, total + set[i], i + 1, sum);
            }
        }
    }

    static void findSubset(long set[], int n, long sum) {
        long subSet[] = new long[n]; // create subset array to pass parameter of subsetSum
        subset_sum(set, subSet, n, 0, 0, 0, sum);
        return;
    }

    private static void displaySubset(long[] subSet, long subSize) {
        // print the subset
        for (int i = 0; i < subSize; i++) {
            System.out.print(subSet[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Declare and initialize array
        Scanner input = new Scanner(System.in);
        // int listSize;
        // System.out.print("\nEnter the number of elements in the set: ");
        // listSize = input.nextInt();
        long list[] = { 12, 1, 61, 9, 5, 2 };

        System.out.print("\nEnter the elements in the set: ");
        // for (int i = 0; i < listSize; i++) {
        // list[i] = input.nextLong();
        // }
        System.out.print("---------------------------------");
        System.out.print("\n\nThe set is: ");
        System.out.print(Arrays.toString(list) + "\n");
        // Take the Target sum
        long sum;
        System.out.print("\nEnter the sum: ");
        sum = input.nextLong();
        int n = list.length - 1;
        // Find subset
        findSubset(list, n, sum);
        input.close();
    }

}
