import java.util.Scanner;
import java.io.BufferedReader;
import java.util.ArrayList;

public class KnapSack {
    public static void listtoArray(int array[], ArrayList<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
    }

    public static void main(String[] args) throws Exception {

        Scanner input = new Scanner(System.in);

        // Arraylist for storing temp values
        ArrayList<Integer> _temp = new ArrayList<Integer>();

        String numbers[];
        int length;
        int values[], weight[], W;

        System.out.print("\n\t\tKNAPSACK ALGORITHM");
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        // Take values input
        System.out.print("\nEnter the Values of the array(with space)\n-> ");

        numbers = br.readLine().split(" ");
        for (String number : numbers)
            _temp.add(Integer.parseInt(number));

        length = _temp.size();
        values = new int[length];
        listtoArray(values, _temp);

        // weight input
        weight = new int[length];
        System.out.print("\n---- Weights of items ----\n ");
        for (int i = 0; i < length; i++) {
            System.out.print("\n-> Weight of item '" + _temp.get(i) + "' : ");
            weight[i] = input.nextInt();
        }

        // Maz weight
        System.out.print("\nEnter the Max Weight: ");
        W = input.nextInt();

        System.out.println("\nThe limit of max possible weight is " + knapsack(values, weight, W));
        input.close();

    }

    public static int knapsack(int val[], int wt[], int W) {

        System.out.print("\nFormulating the problem \n");

        // Get the total number of items.
        // Could be wt.length or val.length. Doesn't matter

        int N = wt.length;

        // Create a matrix.
        // Items are in rows and weight at in columns +1 on each side

        int[][] values = new int[N + 1][W + 1];

        // What if the knapsack's capacity is 0 - Set
        // all columns at row 0 to be 0

        for (int col = 0; col <= W; col++) {
            values[0][col] = 0;

        }

        // What if there are no items at home.
        // Fill the first row with 0
        for (int row = 0; row <= N; row++) {
            values[row][0] = 0;
        }

        for (int item = 1; item <= N; item++) {

            // Let's fill the values row by row
            for (int weight = 1; weight <= W; weight++) {
                // Is the current items weight less
                // than or equal to running weight
                if (wt[item - 1] <= weight) {

                    // Given a weight, check if the value of the current
                    // item + value of the item that we could afford
                    // with the remaining weight is greater than the value
                    // without the current item itself

                    values[item][weight] = Math.max(val[item - 1] + values[item - 1][weight - wt[item - 1]],
                            values[item - 1][weight]);
                }

                else {
                    // If the current item's weight is more than the
                    // running weight, just carry forward the value
                    // without the current item

                    values[item][weight] = values[item - 1][weight];
                }
            }

        }

        // Printing the matrix
        for (int[] rows : values) {
            for (int col : rows) {
                System.out.format("%5d", col);
            }
            System.out.println();
        }
        return values[N][W];
    }

}