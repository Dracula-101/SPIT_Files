package sort;

import java.util.Arrays;

public class bubblesort {

    int counter = 0;
    int swapNum = 0;
    static int maxPos = 0;

    // swap function
    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // bubble sort function
    public void bubbleSortIteration(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                ++counter;
                if (arr[j] > arr[j + 1]) {
                    // swap arr[j+1] and arr[j]

                    // swappging the elements
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                } else {
                }
            }
        }
    }

    public void bubbleSortRec(int[] arr, int n) {

        for (int i = 0; i < n - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                ++swapNum;
                // swap arr[i+1] and arr[i]
                // swappging the elements
                swap(arr, i, i + 1);
            }
        }

        if (n - 1 > 1) {
            bubbleSortRec(arr, n - 1);
        }
    }

    public int getSwapCount() {
        return swapNum;
    }

    public int[] bubbleSort(int i, int[] arr) {
        if (arr[i] > arr[i + 1] && (i + 1) < arr.length - 1) {
            swap(arr, i, i + 1);
            maxPos++;
        }
        if (i < arr.length - 1) {
            maxPos = 0;
            bubbleSort(i + 1, arr);
        }
        return arr;
    }

    public String printArr(int arr[]) {
        // printing the array
        if (arr.length > 10)
            return " ... ";
        else
            return Arrays.toString(arr);
    }

    public String printArr(int[] arr, int start, int end) {
        // printing the array
        if (arr.length > 10)
            return " ... ";
        else
            return Arrays.toString(Arrays.copyOfRange(arr, start, end));
    }
    // They need the array size case

}