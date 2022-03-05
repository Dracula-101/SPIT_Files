import java.io.IOException;
import java.util.Random;

import sort.*;

public class sortAssign {
    public void assign() throws IOException {
        int files = fileCreator.numFiles;

        String filename;
        double totalTime = 0;
        fileCreator fc = new fileCreator();
        fileReader fReader = new fileReader();
        fileWriter fw = new fileWriter();
        for (int i = 1; i <= files; i++) {
            filename = "numbers-" + i + ".txt";
            int arr[] = fReader.readFile(fc.folderName + "\\" + filename);
            Random r = new Random();
            int index = r.nextInt(6);
            double time1 = (float) System.nanoTime() / 1000000000, time2 = 0;
            switch (index) {
                case 0:
                    System.out.println("\nUsing Bubble Sort for " + filename);
                    bubblesort bs = new bubblesort();
                    bs.bubbleSortIteration(arr);
                    time2 = (float) System.nanoTime();
                    System.out.println(
                            "Time taken for Bubble Sort = " + (time2 - time1) + "ns");
                    break;
                case 1:
                    System.out.println("\nUsing Insertion Sort for " + filename);
                    insertionsort is = new insertionsort();
                    is.insertionSort(arr);
                    time2 = (float) System.nanoTime();
                    System.out.println(
                            "Time taken for Insertion Sort = " + (time2 - time1) + "ns");
                    break;
                case 2:
                    System.out.println("\nUsing Selection Sort for " + filename);
                    selectionsort ss = new selectionsort();
                    ss.selectionSort(arr);
                    time2 = (float) System.nanoTime();
                    System.out.println(
                            "Time taken for Selection Sort = " + (time2 - time1) + "ns");
                    break;
                case 3:
                    System.out.println("\nUsing Quick Sort for " + filename);
                    quicksort qs = new quicksort();
                    qs.quickSort(arr, 0, arr.length - 1);
                    time2 = (float) System.nanoTime();
                    System.out.println(
                            "Time taken for Quick Sort = " + (time2 - time1) + "ns");
                    break;
                case 4:
                    System.out.println("\nUsing Heap Sort for " + filename);
                    heapsort hs = new heapsort();
                    hs.heapSort(arr);
                    time2 = (float) System.nanoTime();
                    System.out.println(
                            "Time taken for Heap Sort = " + (time2 - time1) + "ns");

                    break;
                case 5:
                    System.out.println("\nUsing Radix Sort for " + filename);
                    radixsort rs = new radixsort();
                    rs.radixSort(arr, arr.length);
                    time2 = (float) System.nanoTime();
                    System.out.println(
                            "Time taken for Radix Sort = " + (time2 - time1) + "ns");
                    break;
                case 6:
                    System.out.println("\nUsing Merge Sort for " + filename);
                    mergesort ms = new mergesort();
                    ms.mergeSort(arr, 0, arr.length - 1);
                    time2 = (float) System.nanoTime();
                    System.out.println(
                            "Time taken for Merge Sort = " + (time2 - time1) + "ns");
                    break;
                default:
                    System.out.println("\nError in assigning sort");
                    break;
            }
            totalTime += (time2 - time1);
            fw.filewrite(arr, filename);
        }
        System.out.println("\nTotal time taken for all sorts = " + totalTime + "ns");
    }
}
