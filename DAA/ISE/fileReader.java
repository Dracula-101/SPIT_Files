import java.io.BufferedReader;
// import java.io.File;
// import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class fileReader {
    String filename;
    int array[] = new int[Math.abs(numberGenerator.numbers / fileCreator.numFiles)];

    public int[] readFile(String filename) throws IOException {
        // File fileptr = new File(filename);
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String st;
        int i = 0;
        while ((st = br.readLine()) != null) {
            array[i++] = Integer.parseInt(st);
        }
        // while ((i = fr.read()) != -1)
        // array[i] = Integer.parseInt(String.valueOf((char) i));
        br.close();
        return array;
    }
}
