import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Puzzle15Dfs {

    static int initialarr[][] = { { 1, 2, 3, 4 }, { 5, 6, 0, 8 }, { 9, 10, 7, 11 }, { 13, 14, 15, 12 } };

    static int temp[][] = new int[4][4];
    static int targetmatrix[][] = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 0 } };
    static int blank_i, blank_j, lastindex;

    static List<int[][]> visitedStates = new ArrayList<int[][]>();
    static Stack<int[][]> stack = new Stack<int[][]>();

    public static void main(String[] args) {

        lastindex = -1;

        // getBlankPosition(); //..

        stack.push(initialarr);

        while (!stack.isEmpty()) {

            int[][] arr = stack.pop();

            printMatrix(arr);
            visitedStates.add(arr);

            if (isSolutionFound(arr)) {
                System.out.println("Solution found");
                System.exit(0);
            }
            getBlankPosition(arr);

            // System.out.println("l i is"+ lastindex);
            System.out.println("");

            // System.out.println("\n current stack 1:");
            // for (int[][] is : stack) {
            // printMatrix(is);
            // }
            // System.out.println("over\n");

            // up
            if (lastindex != 1 && blank_i > 0) {
                System.out.println("Going up");
                temp = new int[4][4];
                for (int i = 0; i < 4; i++)
                    for (int j = 0; j < 4; j++)
                        temp[i][j] = arr[i][j];

                int xchg = temp[blank_i][blank_j];
                temp[blank_i][blank_j] = temp[blank_i - 1][blank_j];
                temp[blank_i - 1][blank_j] = xchg;

                if (!isVisited(temp) ) {
                    // lastindex = 0;
                    // printMatrix(temp);
                    stack.push(temp);

                } else
                    System.out.println("Not v 1");

            }

            getBlankPosition(arr);
            // System.out.println("\n current stack 2:");
            // for (int[][] is : stack) {
            // printMatrix(is);
            // }
            // System.out.println("over\n");

            // down
            if (lastindex != 0 && blank_i < 3) {
                System.out.println("Going down");
                temp = new int[4][4];

                for (int i = 0; i < 4; i++)
                    for (int j = 0; j < 4; j++)
                        temp[i][j] = arr[i][j];

                int xchg = temp[blank_i][blank_j];
                temp[blank_i][blank_j] = temp[blank_i + 1][blank_j];
                temp[blank_i + 1][blank_j] = xchg;

                if (!isVisited(temp)) {
                    // lastindex = 1;
                    // printMatrix(temp);
                    stack.push(temp);
                } else
                    System.out.println("Not v 2");

            }

            getBlankPosition(arr);
            // System.out.println("\n current stack 3:");
            // for (int[][] is : stack) {
            // printMatrix(is);
            // }
            // System.out.println("over\n");

            if (lastindex != 3 && blank_j < 3) {
                // right
                System.out.println("Going right");
                temp = new int[4][4];

                for (int i = 0; i < 4; i++)
                    for (int j = 0; j < 4; j++)
                        temp[i][j] = arr[i][j];

                int xchg = temp[blank_i][blank_j];
                temp[blank_i][blank_j] = temp[blank_i][blank_j + 1];
                temp[blank_i][blank_j + 1] = xchg;

                if (!isVisited(temp)) {
                    // lastindex = 2;
                    // printMatrix(temp);
                    stack.push(temp);
                } else
                    System.out.println("Not v 3");

            }

            getBlankPosition(arr);
            // System.out.println("\n current stack 4:");
            // for (int[][] is : stack) {
            // printMatrix(is);
            // }
            // System.out.println("over\n");

            if (lastindex != 2 && blank_j > 0) {
                // left
                System.out.println("Going left");
                temp = new int[4][4];

                for (int i = 0; i < 4; i++)
                    for (int j = 0; j < 4; j++)
                        temp[i][j] = arr[i][j];

                int xchg = temp[blank_i][blank_j];
                temp[blank_i][blank_j] = temp[blank_i][blank_j - 1];
                temp[blank_i][blank_j - 1] = xchg;

                if (!isVisited(temp)) {
                    // lastindex = 3;
                    // printMatrix(temp);
                    stack.push(temp);
                } else
                    System.out.println("Not v 4");

            }

            System.out.println("\n current stack last:");
            // for (int[][] is : stack) {
            // printMatrix(is);
            // }
            // System.out.println("over\n");

        }

    }

    static boolean isVisited(int[][] currentmatrix) {

        // for (int[][] is : visitedStates) {
        // System.out.println("logging visited");
        // printMatrix(is);
        // System.out.println("over");
        // }
        // System.out.println("and current is");
        // printMatrix(currentmatrix);

        for (int[][] matrix : visitedStates) {

            if (checkEqualMatrix(currentmatrix, matrix)) {
                return true;
            }

        }
        return false;
    }

    static boolean isSolutionFound(int array[][]) {
        int i, j;
        for (i = 0; i < 4; i++)
            for (j = 0; j < 4; j++) {
                if (array[i][j] != targetmatrix[i][j])
                    return false;
            }
        return true;
    }

    static void getBlankPosition(int arr[][]) {

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (arr[i][j] == 0) {
                    blank_i = i;
                    blank_j = j;
                    return;
                }
            }
        }
    }

    static void printMatrix(int p[][]) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(p[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void copyMatrix(int to[][], int from[][]) {
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++) {
                to[i][j] = from[i][j];
            }
    }

    static String getPosition(int a) {
        if (a == 0)
            return "Up";
        else if (a == 1)
            return "Down";
        else if (a == 2)
            return "Right";
        return "Left";
    }

    static boolean checkEqualMatrix(int arr1[][], int arr2[][]) {
        int i, j;
        for (i = 0; i < 4; i++)
            for (j = 0; j < 4; j++) {
                if (arr1[i][j] != arr2[i][j])
                    return false;
            }
        return true;
    }
}