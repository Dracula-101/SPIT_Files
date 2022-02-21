import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {

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
        int arrLen = list.size();
        array = new int[arrLen];
        switch (newChoice) {
            case 1:
                for (int i = 0; i < arrLen; i++) {
                    array[i] = list.get(i);
                }
                break;
            case 2:
                for (int i = arrLen - 1; i >= 0; i--) {
                    array[(arrLen - 1) - i] = list.get(i);
                }
                break;
            case 3:
                Collections.shuffle(list);
                for (int i = 0; i < arrLen; i++) {
                    array[i] = list.get(i);
                }
                break;
            case 4:
                System.out.print("Enter the size of the array: ");
                int size = input.nextInt();
                list.clear();
                System.out.print("Enter the elements of the array(with space): ");
                for (int i = 0; i < size; i++) {
                    list.add(input.nextInt());
                }
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
        System.out.print("\nIteration Buuble Sort\n");
        // Create bubble sort object
        BubbleSort bs = new BubbleSort();
        // Call bubble sort function
        bs.bubbleSortIteration(array);

        // input closing method
        input.close();
    }
}
