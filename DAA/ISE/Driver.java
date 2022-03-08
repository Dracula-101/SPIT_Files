import java.io.IOException;
import java.util.Scanner;

public class Driver {
    int totalTime;

    public static void main(String[] args) throws IOException {
        fileCreator fc = new fileCreator();
        Scanner input = new Scanner(System.in);
        numberGenerator ng = new numberGenerator();
        fileWriter fw = new fileWriter();
        fw.deleteFiles(fc.folderName);
        fw.deleteFiles(fw.folderoutput);
        System.out.print("\n----------SORTING NUMBERS----------\n");
        System.out.print("\nEnter the number of elements to be sorted: ");
        int num = input.nextInt();
        numberGenerator.numbers = num;
        System.out.print("\nEnter the number of files to be created: ");
        int numFiles = input.nextInt();
        fileCreator.numFiles = numFiles;
        if (numFiles > num) {
            System.out.print("\nNumber of files cannot be greater than number of elements");
            System.exit(0);
        }
        System.out.print("\n1.Unique Sorting\n2.Random Wise Sorting     :");
        int choice = input.nextInt();
        if (choice == 1) {
            ng.generate();
        } else if (choice == 2) {
            ng.arrayGen();
        } else {
            System.out.print("\nInvalid Choice");
            System.exit(0);
        }
        try {
            System.out.print("\n----------FILE MAKING----------\n");
            fc.folderMaker();
            // fc.createFiles();
            System.out.print("\n----------WRITING TO FILES----------\n");
            fc.writeToFiles(ng);
        } catch (IOException e) {
            System.err.print("\nError creating file");
        }
        sortAssign sa = new sortAssign();
        fileMerger fm = new fileMerger();
        System.out.print("\n----------SORTING----------\n");
        System.out.print("\n1.Manual Sorting\n2.Automatic Sorting      :");
        int choice1 = input.nextInt();
        if (choice1 == 1) {
            System.out.print(
                    "\n1.Bubble Sort\n2.Insertion Sort\n3.Selection Sort\n4.Quick Sort\n5.Heap Sort\n6.Radix Sort\n7.Merge Sort\n");
            System.out.print("\n\nEnter the sorting method: ");
            sa.assign(input.nextInt() - 1, 0);
        } else if (choice1 == 2) {
            sa.assign(0, 1);
        } else {
            System.out.print("\nInvalid Choice");
            System.exit(0);
        }
        System.out.print("\n----------TOTAL TIME----------\n");
        System.out.print("\n\nTOTAL TIME FOR ALL THE SORTING IS : "
                + (numberGenerator.duration + numberGenerator.shufffleTime + fc.duration + sa.totalTime
                        + fm.duration)
                + "ms");
        input.close();
    }
}
