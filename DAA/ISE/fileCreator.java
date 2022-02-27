import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class fileCreator {
    int numFiles = 10;
    String fileName = "";
    String folderName = "files";
    public numberGenerator ng = new numberGenerator();

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

    public void writeToFiles() throws IOException {
        ng.generate();
        int lastOffset = 0, j;
        deleteFiles();
        for (int i = 1; i <= numFiles; i++) {
            fileName = "numbers-" + i + ".txt";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(folderName + "\\" + fileName))) {
                for (j = lastOffset + 1; j <= lastOffset + ng.numbers / numFiles && j < ng.numbers + 1; j++) {
                    writer.write(ng.list.get(j - 1) + "\n");
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

    }

    public void folderMaker() {
        File folderFile = new File(folderName);

        if (!folderFile.exists() && folderFile.mkdir()) {
            System.out.print("\nFolder created successfully");
        }
    }

    public void deleteFiles() {
        try {
            for (int i = 0; i < numFiles; i++) {
                fileName = "numbers-" + (i + 1) + ".txt";
                Files.deleteIfExists(Paths.get(folderName + "\\" + fileName));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("\nError deleting files");
        }
    }

    public static void main(String[] args) {
        fileCreator fc = new fileCreator();
        try {
            fc.folderMaker();
            fc.writeToFiles();
        } catch (IOException e) {
            System.err.print("\nError creating file");
        }
    }
}
