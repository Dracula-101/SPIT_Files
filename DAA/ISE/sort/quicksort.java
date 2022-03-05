package sort;

import java.util.Arrays;

public class quicksort {

    int totalSwaps = 0;
    int totalComparisons = 0;

    // This function takes last element as pivot, places
    public int partition(int arr[], int low, int high) {

        int pivot = low;
        low++;
        int comparisons = 0;
        int swaps = 0;
        do {
            // increment low pointer until that element is larger than the pivot element
            while (low < high && arr[low] <= arr[pivot]) {
                low++;
                comparisons++;
            }
            // decrement high pointer until that element is smaller than the pivot element
            while (high > pivot && arr[high] > arr[pivot]) {
                high--;
                comparisons++;
            }
            // if the low and high pointers cross each other swap the corresponding elements
            if (low < high) {
                int temp = arr[low];
                arr[low] = arr[high];
                arr[high] = temp;
                swaps++;

            }
        } while (low < high);
        // swap the pivot element and the element pointed by high
        int temp = arr[high];
        arr[high] = arr[pivot];
        arr[pivot] = temp;
        swaps++;
        totalComparisons += comparisons;
        totalSwaps += swaps;
        return high;
    }

    // swap function
    public void swap(int arr[], int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void quickSort(int arr[], int low, int high) {
        if (low < high) {
            /*
             * pi is partitioning index, arr[pi] is
             * now at right place
             */
            int pi = partition(arr, low, high);

            // Recursively quickSort elements before
            // partition and after partition
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    /* A utility function to print array of size n */
    public String printArray(int arr[]) {
        return Arrays.toString(arr);
    }
}
