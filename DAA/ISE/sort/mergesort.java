package sort;

import java.util.Arrays;

public class mergesort {
    // Merge two subarrays L and M into arr
    int shift = 0;

    void merge(int arr[], int p, int q, int r) {
        // Create temp array to store the merged subarrays
        int n1 = q - p + 1;
        int n2 = r - q;
        shift = 0;
        int L[] = new int[n1];
        int M[] = new int[n2];

        for (int i = 0; i < n1; i++)
            L[i] = arr[p + i];
        for (int j = 0; j < n2; j++)
            M[j] = arr[q + 1 + j];

        // Maintain current index of sub-arrays and main array
        int i, j, k;
        i = 0;
        j = 0;
        k = p;

        // Until we reach either end of either L or M, pick larger among
        // elements L and M and place them in the correct position at A[p..r]
        while (i < n1 && j < n2) {
            if (L[i] <= M[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = M[j];
                j++;
                shift++;
            }
            k++;
        }

        // When we run out of elements in either L or M,
        // pick up the remaining elements and put in A[p..r]
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = M[j];
            j++;
            k++;
        }
    }

    // Divide the array into two subarrays, sort them and merge them
    public void mergeSort(int arr[], int l, int r) {
        if (l < r) {
            // m is the point where the array is divided into two subarrays
            int m = (l + r) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);

            merge(arr, l, m, r);

        }
    }

    /* A utility function to print array of size n */
    public String printArray(int arr[]) {
        return Arrays.toString(arr);
    }

    // Driver program
    // public static void main(String args[]) {
    // Scanner input = new Scanner(System.in);
    // System.out.print("\n MERGESORT");
    // System.out.print("\n-------------------------------------------------------------------\n");
    // System.out.print("\nEnter the roll no: ");
    // int rollNo = input.nextInt();

    // int array[];
    // ArrayList<Integer> list = new ArrayList<Integer>();

    // // Case input
    // System.out.print("\n1.Random Case\n2.Worst Case\n3.Manual Case :");
    // int choice = input.nextInt();
    // if (choice == 2) {
    // // Worst Case
    // for (int i = 9; i >= 0; i--)
    // list.add(rollNo + (rollNo + 1) * i);

    // } else if (choice == 1) {
    // // Random Case
    // for (int i = 0; i < 10; i++)
    // list.add(rollNo + (rollNo + 1) * i);

    // Collections.shuffle(list);
    // } else {
    // // Manual Case
    // System.out.print("\nEnter the elements: ");
    // for (int i = 0; i < 10; i++)
    // list.add(input.nextInt());
    // }
    // array = new int[10];
    // for (int i = 0; i < 10; i++)
    // array[i] = list.get(i);
    // mergeSortClass ob = new mergeSortClass();
    // ob.mergeSort(array, 0, array.length - 1);

    // System.out.println("\nSorted array:" + ob.printArray(array));
    // input.close();
    // }
}
