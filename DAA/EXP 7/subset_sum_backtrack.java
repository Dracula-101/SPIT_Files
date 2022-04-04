import java.util.Arrays;

public class subset_sum_backtrack {

    // Subset sum problem
    // Given a set of numbers, find if there is a subset of the given set with sum
    // equal to given number.
    // Input: set[] = {3, 34, 4, 12, 5, 2}, sum = 9
    // Output: True //There is a subset (4, 5) with sum 9.

    static void subsetSum(long set[], long subSet[], long n, int subSize, long total, int l, long sum) {
        if (total == sum) {
            // Print the subset
            displaySubset(subSet, subSize);
            // for other subsets
            subsetSum(set, subSet, n, subSize - 1, total - set[l], l + 1, sum);
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
        subsetSum(set, subSet, n, 0, 0, 0, sum);
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
        int listSize = 50;
        long list[] = new long[listSize];
        for (int i = 0; i < listSize; i++) {
            list[i] = (long) (Math.random() * 100);
        }
        System.out.print(Arrays.toString(list) + "\n");
        // Take the Target sum
        long sum = 13;
        int n = list.length - 1;
        // Find subset
        findSubset(list, n, sum);
    }

}
