import java.util.ArrayList;
import java.util.Collections;

public class numberGenerator {

    public static int numbers = 10000;
    public int threadNum = 6;
    // Thread threads[] = new Thread[];
    public ArrayList<Integer> list = new ArrayList<Integer>(numbers);
    int[] array = new int[numbers];

    public void generate() {
        double time1 = (float) System.nanoTime() / 1000000000;
        System.out.print("\nGenerating Numbers...");
        for (int i = 1; i <= numbers; i++) {
            list.add(i);
        }
        double time2 = (float) System.nanoTime() / 1000000000;
        System.out.print("\nFinished Generating numbers Time=" + (time2 - time1));
        Collections.shuffle(list);
        double time3 = (float) System.nanoTime() / 1000000000;
        System.out.print("\nShuffled Finished Time =" + (time3 - time1));

    }

    public void arrayGen() {
        double time1 = (float) System.nanoTime() / 1000000000;
        System.out.print("\nGenerating Numbers Time=" + time1);
        for (int i = 1; i <= numbers; i++) {
            array[i - 1] = (int) (Math.random() * numbers * Math.random());
        }
        double time2 = (float) System.nanoTime() / 1000000000;
        System.out.print("\nFinished Generating numbers Time=" + (time2 - time1));
    }

}