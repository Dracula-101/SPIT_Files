import java.util.Arrays;

public class BubbleSort {

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
        int counter = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                ++counter;
                if (arr[j] > arr[j + 1]) {
                    // swap arr[j+1] and arr[j]
                    System.out.printf("%d\t\t%d<->%d\t", counter, arr[j], arr[j + 1]);
                    System.out.print(Arrays.toString(arr) + "\n");
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                } else {
                    System.out.printf("%d\t\t----\t", counter);
                    System.out.print(Arrays.toString(arr) + "\n");
                }
            }
        }
    }
}