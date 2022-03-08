import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class fileWriter {
    String folderoutput = "unsorted";

    public void deleteFiles(String folderName) {
        File folder = new File(folderName);
        if (folder.list() == null) {
            return;
        }
        for (File file : folder.listFiles()) {
            file.delete();
        }
    }

    public fileWriter() {
        File folderFile = new File(folderoutput);

        if (!folderFile.exists() && folderFile.mkdir()) {
            System.out.print("\nFolder created successfully");
        }
    }

    public void filewrite(int arr[], String filename) {
        try {
            FileWriter fw = new FileWriter(folderoutput + "\\" + filename);
            for (int i = 0; i < arr.length; i++) {
                fw.write(arr[i] + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void filewrite(int arr[], String filename, String foldername) {
        try {
            FileWriter fw = new FileWriter(foldername + "\\" + filename);
            for (int i = 0; i < arr.length; i++) {
                fw.write(arr[i] + " ");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
