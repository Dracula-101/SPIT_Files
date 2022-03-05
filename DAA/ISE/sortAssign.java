import java.io.IOException;
import sort.*;

public class sortAssign {
    public void assign() throws IOException {
        int files = fileCreator.numFiles;

        String filename;
        double totalTime = 0;
        fileCreator fc = new fileCreator();
        fileReader fReader = new fileReader();
        fileWriter fw = new fileWriter();
        System.out.printf("\n|      Sort-Method\t|    Time Taken\t\t|       File Name\t|");
        for (int i = 1; i <= files; i++) {
            filename = "numbers-" + i + ".txt";
            int arr[] = fReader.readFile(fc.folderName + "\\" + filename);
            double time1 = System.currentTimeMillis(), time2 = 0;
            switch (files % i) {
                case 0:
                    System.out.printf("\n|\tBubble Sort   \t|");
                    bubblesort bs = new bubblesort();
                    bs.bubbleSortIteration(arr);
                    time2 = System.currentTimeMillis();
                    System.out.printf(
                            "\t" + (time2 - time1) + " ms\t\t|\t" + filename + "\t|");
                    break;
                case 1:
                    System.out.printf("\n|\tInsertion Sort\t|");
                    insertionsort is = new insertionsort();
                    is.insertionSort(arr);
                    time2 = System.currentTimeMillis();
                    System.out.printf(
                            "\t" + (time2 - time1) + " ms\t\t|\t" + filename + "\t|");
                    break;
                case 2:
                    System.out.printf("\n|\tSelection Sort\t|");
                    selectionsort ss = new selectionsort();
                    ss.selectionSort(arr);
                    time2 = System.currentTimeMillis();
                    System.out.printf(
                            "\t" + (time2 - time1) + " ms\t\t|\t" + filename + "\t|");
                    break;
                case 3:
                    System.out.printf("\n|\tQuick Sort    \t|");
                    quicksort qs = new quicksort();
                    qs.quickSort(arr, 0, arr.length - 1);
                    time2 = System.currentTimeMillis();
                    System.out
                            .printf("\t" + (time2 - time1) + " ms\t\t|\t" + filename
                                    + "\t|");
                    break;
                case 4:
                    System.out.printf("\n|\tHeap Sort\t|");
                    heapsort hs = new heapsort();
                    hs.heapSort(arr);
                    time2 = System.currentTimeMillis();
                    System.out
                            .printf("\t" + (time2 - time1) + " ms\t\t|\t" + filename
                                    + "\t|");
                    break;
                case 5:
                    System.out.printf("\n|\tRadix Sort\t|");
                    radixsort rs = new radixsort();
                    rs.radixSort(arr, arr.length);
                    time2 = System.currentTimeMillis();
                    System.out
                            .printf("\t" + (time2 - time1) + " ms\t\t|\t" + filename + "\t|");
                    break;
                case 6:
                    System.out.printf("\n|\tMerge Sort\t|");
                    mergesort ms = new mergesort();
                    ms.mergeSort(arr, 0, arr.length - 1);
                    time2 = System.currentTimeMillis();
                    System.out
                            .printf("\t" + (time2 - time1) + " ms\t\t|\t" + filename + "\t|");
                    break;
                default:
                    System.out.println("\nError in assigning sort");
                    break;
            }
            totalTime += (time2 - time1);
            fw.filewrite(arr, filename);
        }
        System.out.println("\nTotal time taken for all sorts = " + totalTime + "ms");
    }
}
