import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class fileReader {
    String filename;

    public void readFile(String filename) throws IOException {
        // File fileptr = new File(filename);
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String st;
        ArrayList<Integer> arraylist = new ArrayList<Integer>();
        while ((st = br.readLine()) != null) {
            arraylist.add(Integer.parseInt(st));

        }
        int array[] = arraylist.to;

    }

    public static void main(String[] args) throws IOException {
        fileReader fr = new fileReader();

        fr.readFile("files\\numbers-1.txt");
    }
}
