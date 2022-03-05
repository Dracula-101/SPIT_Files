package sort;

public class selectionsort {
    public void selectionSort(int array[]) {
        int arrayLen = array.length;

        for (int i = 0; i < arrayLen - 1; i++) {
            int minElement = i;
            for (int j = i + 1; j < arrayLen; j++) {
                if (array[j] < array[minElement]) {
                    minElement = j;
                }
            }
            int temp = array[minElement];
            array[minElement] = array[i];
            array[i] = temp;

        }
    }
}
