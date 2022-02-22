import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) throws Exception {

        // Arraylist of Integers
        ArrayList<Integer> list = new ArrayList<Integer>();
        // array for sorting
        int[] array;
        // User input
        Scanner input = new Scanner(System.in);
        System.out.print("\n1.Random Array\n2.Roll.no Input    : ");
        int choice = input.nextInt();
        if (choice == 1) {
            System.out.print("\nEnter the size of the array : ");
            int size = input.nextInt();
            for (int i = 0; i < size; i++) {
                list.add((int) (i + (Math.random() * 100)));
            }
            Collections.sort(list);
        } else {
            System.out.print("\nEnter the roll no: ");
            int roll = input.nextInt();
            for (int i = 0; i < 10; i++) {
                list.add(roll + (roll + 1) * i);
            }
        }
        System.out.print("\n1.Best Case\n2.Worst Case\n3.Average Case\n4.Manual Choice\nEnter your choice: ");
        int newChoice = input.nextInt();
        int listSize = list.size();
        array = new int[listSize];
        switch (newChoice) {
            case 1:
                for (int i = 0; i < listSize; i++) {
                    array[i] = list.get(i);
                }
                break;
            case 2:
                for (int i = listSize - 1; i >= 0; i--) {
                    array[(listSize - 1) - i] = list.get(i);
                }
                break;
            case 3:
                Collections.shuffle(list);
                for (int i = 0; i < listSize; i++) {
                    array[i] = list.get(i);
                }
                break;
            case 4:
                if (choice == 1) {
                    System.out.print("\nAre u sure u want to enter the array manually?(y/n): ");
                    String choice1 = input.next().toLowerCase();
                    if (choice1.equals("y")) {
                        System.out.print("Enter the size of the array: ");
                        int size = input.nextInt();
                        list.clear();
                        System.out.print("Enter the elements of the array(with space): ");
                        for (int i = 0; i < size; i++) {
                            list.add(input.nextInt());
                        }
                    }
                }
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
        int arrLen = array.length;
        // Array segregation
        switch (arrLen) {
            case 0:
                input.close();
                throw new Exception("Array is empty");
            case 1:
                input.close();
                throw new Exception("Array has only one element");
            default:
                System.out.print("\nArray: " + Arrays.toString(array));
                break;
        }
        System.out
                .print("\n***********************************************\n");
        System.out.print("\nBubble Sort\n");
        // Bubble Sort
        System.out.print("\n1.Iteration Buuble Sort\n2.Recursive Buuble Sort\n3.Bubble Sort (without for loops)   : ");
        int choice2 = input.nextInt();
        // Create bubble sort object
        BubbleSort bs = new BubbleSort();
        // Call bubble sort function
        System.out.print("\nBefore sorting: " + Arrays.toString(array));
        System.out.print("\n---------------------------------------------\n");
        if (choice2 == 1) {
            bs.bubbleSortIteration(array);
        } else if (choice2 == 2) {
            bs.bubbleSortRec(array, listSize);
            System.out.print("\nTotal Swaps: " + bs.getSwapCount());
        } else {
            bs.bubbleSort(array);
        }

        System.out.print("\n---------------------------------------------\n");
        System.out.print("\nFinal Array: " + Arrays.toString(array) + " \n");
        // input closing method
        input.close();
    }
}
