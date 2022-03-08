import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class fileCreator {
    static int numFiles = 100;
    String fileName = "";
    String folderName = "sorted";
    double duration;

    public void createFiles() throws IOException {

        for (int i = 1; i <= numFiles; i++) {
            fileName = "numbers-" + i + ".txt";
            if (new File(fileName).createNewFile()) {
                // file created successfully
            } else {
                System.err.print("\nError creating file " + fileName);
            }
        }

    }

    public void writeToFiles(numberGenerator ng) throws IOException {
        int lastOffset = 0, j;
        deleteFiles();
        double time1 = System.currentTimeMillis();
        for (int i = 1; i <= numFiles; i++) {
            fileName = "numbers-" + i + ".txt";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(folderName + "\\" + fileName))) {
                for (j = lastOffset + 1; j <= lastOffset + numberGenerator.numbers / numFiles
                        && j < numberGenerator.numbers + 1; j++) {
                    if (ng.list.size() > 0) {
                        writer.write(ng.list.get(j - 1) + "\n");
                    } else {
                        writer.write(ng.array[j - 1] + "\n");
                    }
                    writer.flush();

                }
                System.out.print("\nFinished writing to file " + fileName);
                lastOffset = j;
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.err.print("\nError writing to file " + fileName);
            }
        }
        double time2 = System.currentTimeMillis();
        duration = (time2 - time1);
        System.out.print("\n\nTime taken for writing files = " + duration + "ms");

    }

    public void folderMaker() {
        File folderFile = new File(folderName);

        if (!folderFile.exists() && folderFile.mkdir()) {
            System.out.print("\nFolder created successfully");
        }
    }

    public void deleteFiles() throws IOException {
        File folder = new File(folderName);
        for (File file : folder.listFiles()) {
            file.delete();
        }
    }
}
