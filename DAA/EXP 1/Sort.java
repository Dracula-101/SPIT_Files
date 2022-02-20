import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class Sort {
    public static void insertionSort(int array[]) {
        int arrayLen = array.length;
        for (int i = 1; i < arrayLen; ++i) {
            System.out.println("\n**********************************");
            int currentPos = array[i];
            System.out.print("\nIteration on Element " + currentPos);
            int j = i - 1;
            while (j >= 0 && array[j] > currentPos) {
                System.out.print("\nSwapping " + array[j] + " with " + array[j + 1]);
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = currentPos;
            System.out.print("\nArray : " + printArray(array));
        }
    }

    public static void selectionSort(int array[]) {
        int arrayLen = array.length;

        for (int i = 0; i < arrayLen - 1; i++) {
            System.out.print("\n**********************************");
            int minElement = i;
            for (int j = i + 1; j < arrayLen; j++) {
                if (array[j] < array[minElement]) {
                    minElement = j;
                }
            }
            System.out.print("\nThe minimum Element is " + array[minElement]);
            System.out.print("\nSwapping " + array[i] + " with " + array[minElement]);
            int temp = array[minElement];
            array[minElement] = array[i];
            array[i] = temp;
            System.out.print("\nArray : " + printArray(array));
        }
    }

    public static String printArray(int array[]) {
        String result = "[ ";
        for (int i : array) {
            result += i + " ";
        }
        result += "]";
        return result;
    }

    public static void main(String[] args) throws IOException {

        ArrayList<Integer> arrayList = new ArrayList<Integer>();

        int sortingArray[];
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        System.out.print("\n1.Manual Input\n2.Random Input\n3.Roll no Input : ");
        int choice = Integer.parseInt(br.readLine());

        if (choice == 1) {
            System.out.print("\nEnter the elements of the array(with space)\n-> ");
            String numbers[] = br.readLine().split(" ");
            for (String number : numbers)
                arrayList.add(Integer.parseInt(number));
        } else if (choice == 2) {
            System.out.print("\nEnter the size of the array : ");
            int size = Integer.parseInt(br.readLine());
            for (int i = 0; i < size; i++) {
                arrayList.add((int) (Math.random() * 100));
            }
        } else {
            System.out.print("\nEnter the roll no : ");
            int rollNo = Integer.parseInt(br.readLine());
            System.out.print("\n1.Best Case\n2.Average Case\n3.Worst Case : ");
            int scenario = Integer.parseInt(br.readLine());
            if (scenario == 1) {
                for (int i = 0; i < 10; i++) {
                    arrayList.add(rollNo + (rollNo + 1) * i);
                }
            } else if (scenario == 2) {
                for (int i = 0; i < 10; i++)
                    arrayList.add(rollNo + (rollNo + 1) * i);
                Collections.shuffle(arrayList);
            } else {
                for (int i = 9; i >= 0; i--)
                    arrayList.add(rollNo + (rollNo + 1) * i);
            }

        }

        sortingArray = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            sortingArray[i] = arrayList.get(i);
        }

        System.out.print("\nArray : " + printArray(sortingArray));
        System.out.print("\n\n==================================");
        System.out.print("\nWhich sorting algorithm do you want to use?\n1. Insertion Sort\n2. Selection Sort\n-> ");
        int decision = Integer.parseInt(br.readLine());
        System.out.print("\n==================================");
        System.out.print("\nBefore Sort: " + printArray(sortingArray));
        switch (decision) {
            case 1:
                System.out.print("\nInsertion Sort");
                insertionSort(sortingArray);
                break;
            case 2:
                System.out.print("\nSelection Sort");
                selectionSort(sortingArray);
                break;
            default:
                System.out.print("\nInvalid Choice");
        }

        System.out.print("\n-----------------------------------------------------");
        System.out.print("\nFinal After Sort: " + printArray(sortingArray));

        System.out.print("\n\n1.Exit\n2.Continue\n-> ");
        int exit = Integer.parseInt(br.readLine());
        if (exit == 1)
            System.exit(0);
        else
            main(null);
    }
}