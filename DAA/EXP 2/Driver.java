import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        // User Input
        Scanner input = new Scanner(System.in);
        System.out.print("\n                             QUICKSORT");
        System.out.print("\n-------------------------------------------------------------------\n");
        System.out.print("\nEnter the roll no: ");
        int rollNo = input.nextInt();

        int array[];
        ArrayList<Integer> list = new ArrayList<Integer>();

        // Case input
        System.out.print("\n1.Random Case\n2.Worst Case\n3.Manual Case  :");
        int choice = input.nextInt();
        if (choice == 2) {
            // Worst Case
            for (int i = 9; i >= 0; i--)
                list.add(rollNo + (rollNo + 1) * i);

        } else if (choice == 1) {
            // Random Case
            for (int i = 0; i < 10; i++)
                list.add(rollNo + (rollNo + 1) * i);

            Collections.shuffle(list);
        } else {
            // Manual Case
            System.out.print("\nEnter the elements: ");
            for (int i = 0; i < 10; i++)
                list.add(input.nextInt());
        }
        array = new int[10];
        for (int i = 0; i < 10; i++)
            array[i] = list.get(i);
        quickSort qSort = new quickSort();
        System.out.print("\nBefore Sorting: " + qSort.printArray(array));
        System.out.print("\n-------------------------------------------------------------------\n");
        qSort.sort(array, 0, array.length - 1);
        System.out.print("\n-------------------------------------------------------------------\n");
        System.out.print("\nAfter Sorting: " + qSort.printArray(array));
        System.out.print("\n-------------------------------------------------------------------\n");
        System.out.print("\nTotal swaps: " + qSort.totalSwaps);
        System.out.print("\nTotal comparisons: " + qSort.totalComparisons);
        input.close();
    }
}
// Best case: 274 54 109 219 164 439 329 384 549 494
// Avg case: 164 439 54 494 549 329 274 384 219 109