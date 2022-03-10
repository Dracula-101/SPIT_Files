import java.io.File;
import java.io.IOException;
import sort.*;

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

        double time2 = 0;
        for (int i = 1; i <= files; i++) {
            String filename = fc.folderName + "\\" + "numbers-" + i + ".txt";
            tempArray = fr.readFile(filename);
            System.arraycopy(tempArray, 0, newArray, 0, tempArray.length);
            double time1 = System.currentTimeMillis();
            time2 = System.currentTimeMillis();
            if (i == 1)
                System.out.print("\n\nMerge Sort: ");
            else
                System.out.print("\nMerging Files 1-" + i + " : " + (time2 - time1) + " ms");
            totalTime += time2 - time1;
            // System.out.print("\n\n" + Arrays.toString(newArray));
        }
        System.out.print("\n\nTotal Time taken for Merging Files = " + totalTime + "ms");
        fw.filewrite(newArray, output, "output");
    }
}
