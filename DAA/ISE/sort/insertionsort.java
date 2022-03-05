package sort;

public class insertionsort {
    public void insertionSort(int array[]) {
        int arrayLen = array.length;
        for (int i = 1; i < arrayLen; ++i) {
            int currentPos = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > currentPos) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = currentPos;
        }
    }
}
