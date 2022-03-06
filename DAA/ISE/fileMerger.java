import java.io.File;
import java.io.IOException;

import sort.mergesort;

public class fileMerger {
    String output = "output.txt";
    int files = fileCreator.numFiles;
    double duration = 0;

    fileCreator fc = new fileCreator();
    fileReader fr = new fileReader();
    fileWriter fw = new fileWriter();
    mergesort ms = new mergesort();
    int tempArray[], newArray[];

    fileMerger() throws IOException {
        tempArray = new int[Math.abs(numberGenerator.numbers / files)];
        // newArray = fr.readFile(fc.folderName + "\\" + "numbers-1.txt");
        // System.out.print("\n\n" + Arrays.toString(newArray));
        File folderFile = new File("output");
        if (!folderFile.exists() && folderFile.mkdir()) {
            System.out.print("\nFolder created successfully");
        }
    }

    public void merger() throws IOException {
        newArray = new int[numberGenerator.numbers];

        int totalTime = 0;
        for (int i = 1; i <= files; i++) {
            String filename = fc.folderName + "\\" + "numbers-" + i + ".txt";
            tempArray = fr.readFile(filename);
            System.arraycopy(tempArray, 0, newArray, 0, tempArray.length);
            double time1 = System.currentTimeMillis();
            ms.mergeSort(newArray, 0, newArray.length - 1);
            double time2 = System.currentTimeMillis();
            duration = time2 - time1;
            System.out.print("\nMerging Step (" + i + ") : " + duration + " ms");
            totalTime += duration;
            // System.out.print("\n\n" + Arrays.toString(newArray));
        }
        System.out.print("\n\nTotal Time taken for Merging = " + totalTime + "ms");
        fw.filewrite(newArray, output, "output");
    }
}
