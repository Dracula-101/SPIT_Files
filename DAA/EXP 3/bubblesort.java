import java.util.Arrays;

public class BubbleSort {

    int counter = 0;
    int swapNum = 0;
    static int maxPos = 0;
    String swaps = "Swaps: |";

    // swap function
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // bubble sort function
    public void bubbleSortIteration(int arr[]) {
        int n = arr.length;
        System.out.printf("Iteration\tSwap\t\tArray\n");
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                ++counter;
                if (arr[j] > arr[j + 1]) {
                    // swap arr[j+1] and arr[j]
                    System.out.printf("%d\t\t%d<->%d\t\t%s\n", counter, arr[j], arr[j + 1], printArr(arr));

                    // swappging the elements
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                } else {
                    System.out.printf("%d\t\t----\t\t%s\n", counter, printArr(arr));
                }
            }
        }
    }

    public void bubbleSortRec(int[] arr, int n) {

        for (int i = 0; i < n - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                ++swapNum;
                // swap arr[i+1] and arr[i]
                swaps += arr[i] + "<->" + arr[i + 1] + " | ";
                // swappging the elements
                swap(arr, i, i + 1);
            }
        }

        if (n - 1 > 1) {
            System.out.print("\n\n" + swaps);
            System.out.print(
                    "\nUnsorted + Sorted => " + printArr(arr, 0, n - 1) + " " + printArr(arr, n - 1, arr.length)
                            + "\n");
            swaps = "Swaps: |";
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
            System.out.print("\n-----------------------------------------------------");
            System.out.println("\nMax Places " + arr[i] + " is shifted " + maxPos + " times");
            System.out.println("\nArray: " + printArr(arr));
            maxPos = 0;
            bubbleSort(i + 1, arr);
        }
        return arr;
    }

    public void bubbleSort(int arr[], int n) {
        // Base case
        if (n == 1)
            return;

        if (arr[n - 1] > arr[n - 2]) {
            swap(arr, n - 1, n - 2);
            maxPos++;
            bubbleSort(arr, n - 1);
        }

        bubbleSort(arr, n - 1);
    }

    public int[] bubbleSort(int[] arr) {
        return bubbleSort(0, arr);
    }

    public void duplicateRemove(int arr[]) {

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