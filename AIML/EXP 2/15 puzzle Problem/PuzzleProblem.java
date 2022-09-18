import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

class Game {
    static int boardNum = 4;
    int[][] startBoard = new int[boardNum][boardNum];

    void makeRandomBoard() {
        int[] temp = { 1, 2, 3, 5, 4, 6, 7, 8, 9, 11, 10, 12, 15, 13, 14, 0 };
        // for (int i = 0; i < boardNum * boardNum; i++) {
        // temp[i] = i;
        // }
        // for (int i = 0; i < boardNum * boardNum; i++) {
        // int rand = (int) (Math.random() * boardNum * boardNum);
        // int temp2 = temp[i];
        // temp[i] = temp[rand];
        // temp[rand] = temp2;
        // }
        for (int i = 0; i < boardNum; i++) {
            for (int j = 0; j < boardNum; j++) {
                startBoard[i][j] = temp[i * boardNum + j];
            }
        }
    }

    static void printBoard(int[][] board) {
        System.out.print("\n-----------------------------\n");

        for (int i = 0; i < boardNum; i++) {
            for (int j = 0; j < boardNum; j++) {
                System.out.printf("|  %2d  ", board[i][j]);
                if (j == boardNum - 1)
                    System.out.print("|");
            }
            System.out.print("\n-----------------------------\n");

        }
    }

    boolean isGoal() {
        for (int i = 0; i < boardNum; i++) {
            for (int j = 0; j < boardNum; j++) {
                if (startBoard[i][j] != (i * boardNum + j))
                    return false;
            }
        }
        return true;
    }
}

public class PuzzleProblem {

    public static void main(String[] args) {
        Game puzzle = new Game();
        puzzle.makeRandomBoard();
        Game.printBoard(puzzle.startBoard);

        Scanner sc = new Scanner(System.in);
        Computer computer = new Computer(puzzle.startBoard, "");
        Player player = new Player(0, 0);

        // while (true) {
        // if (puzzle.computerTurn) {
        // System.out.print("\nComputer's turn");
        // computer.makeMove();
        // puzzle.computerTurn = false;
        // } else {
        // System.out.print("\nPlayer's turn");
        // System.out.print("\nEnter a number to move: ");
        // int move = sc.nextInt();
        // player.makeMove(move);
        // puzzle.computerTurn = true;
        // }
        // }
        computer.BFS(computer);

    }
}

class Computer extends Game {
    int x;
    int y;
    String lastMove;
    // already visited nodes
    static ArrayList<Computer> visited = new ArrayList<Computer>();

    Computer(int[][] board, String lastMove) {
        this.startBoard = board;
        this.lastMove = lastMove;
        this.x = findX(startBoard);
        this.y = findY(startBoard);
    }

    void BFS(Computer root) {
        Queue<Computer> queue = new LinkedList<Computer>();
        queue.add(root);
        while (!queue.isEmpty()) {
            // System.out.print("\nBefore: ");
            // printQueue(queue);
            // System.out.print("\n----------------------------------");
            final Computer temp = queue.poll();
            if (temp.isGoal()) {
                System.out.println("Goal found");
                break;
            }
            visited.add(temp);
            ArrayList<Computer> children = getChildren(temp);
            for (Computer child : children) {
                if (!visited.contains(child)) {
                    System.out.print("\nAdded to queue");
                    printBoard(child.startBoard);
                    // System.out.print("\nVisited ");
                    // printArraylistString(visited);
                    visited.add(child);
                    queue.add(child);
                }
            }
            // System.out.print("\nAfter: ");
            // printQueue(queue);
            // System.out.print("\n==================================");
        }
    }

    // void DFS(Computer root) {
    // Stack<Computer> stack = new Stack<Computer>();
    // stack.push(root);
    // while (!stack.isEmpty()) {
    // Computer temp = stack.pop();
    // if (temp.isGoal()) {
    // System.out.println("Goal found");
    // break;
    // }
    // visited.add(temp);
    // ArrayList<Computer> children = temp.getChildren(temp);
    // for (Computer child : children) {
    // if (!visited.contains(child)) {
    // // System.out.print("\nAdded to stack");
    // // printBoard(child.startBoard);
    // // System.out.print("\nVisited ");
    // // printArraylistString(visited);
    // visited.add(child);
    // stack.push(child);
    // }
    // }
    // }
    // }

    boolean checkBoard(int[][] finalBoard) {
        for (Computer board : visited) {
            // check if two dimensions arrya are equal
            if (java.util.Arrays.deepEquals(board.startBoard, finalBoard)) {
                return false;
            }
        }
        return true;
    }

    void printArrayList(ArrayList<Computer> list) {
        for (Computer computer : list) {
            printBoard(computer.startBoard);
        }
    }

    void printArraylistString(ArrayList<String> list) {
        for (String string : list) {
            System.out.print(string + ", ");
        }
    }

    void printQueue(Queue<Computer> queue) {
        System.out.print("\nQueue: ");
        for (Computer computer : queue) {
            printBoard(computer.startBoard);
        }
    }

    String convertBoard(int[][] board) {
        String temp = "";
        for (int i = 0; i < boardNum; i++) {
            for (int j = 0; j < boardNum; j++) {
                temp += board[i][j] + ",";
            }
        }
        return temp;
    }

    ArrayList<Computer> getChildren(Computer parent) {

        ArrayList<Computer> children = new ArrayList<Computer>();
        // go up
        if (temp.x > 0 && temp.lastMove != "D") {
            Computer child = new Computer(temp.startBoard, "U");
            swap(child.startBoard, child.x, child.y, child.x - 1, child.y);
            child.findXY(child.startBoard);
            System.out.print("\nUp");
            // if (checkForCloserGoal(temp.startBoard, child.startBoard) &&
            // !checkVisited(temp, child))
            children.add(child);
        }
        // go down
        if (temp.x < boardNum - 1 && temp.lastMove != "U") {
            Computer child = new Computer(temp.startBoard, "D");
            swap(child.startBoard, child.x, child.y, child.x + 1, child.y);
            child.findXY(child.startBoard);
            System.out.print("\nDown");
            // if (checkForCloserGoal(temp.startBoard, child.startBoard) &&
            // !checkVisited(temp, child))
            children.add(child);
        }
        // go left
        if (temp.y > 0 && temp.lastMove != "R") {
            Computer child = new Computer(temp.startBoard, "L");
            swap(child.startBoard, child.x, child.y, child.x, child.y - 1);
            child.findXY(child.startBoard);
            // if (checkForCloserGoal(temp.startBoard, child.startBoard) &&
            // !checkVisited(temp, child)) {
            System.out.print("\nLeft");
            children.add(child);

        }
        // go right
        if (temp.y < boardNum - 1 && temp.lastMove != "L") {
            Computer child = new Computer(temp.startBoard, "R");
            swap(child.startBoard, child.x, child.y, child.x, child.y + 1);
            child.findXY(child.startBoard);
            // if (checkForCloserGoal(temp.startBoard, child.startBoard) &&
            // !checkVisited(temp, child)) {
            System.out.print("\nRight");
            children.add(child);

        }
        // for (Computer child : children) {
        // child.x = findX(child.startBoard);
        // child.y = findY(child.startBoard);
        // }

        // if (temp.makeMove("U"))
        // children.add(temp);
        // if (temp.makeMove("D"))
        // children.add(temp);
        // if (temp.makeMove("L"))
        // children.add(temp);
        // if (temp.makeMove("R"))
        // children.add(temp);

        return children;
    }

    void findXY(int[][] board) {
        for (int i = 0; i < boardNum; i++) {
            for (int j = 0; j < boardNum; j++) {
                if (board[i][j] == 0) {
                    x = i;
                    y = j;
                    return;
                }
            }
        }
    }

    int findX(int[][] board) {
        for (int i = 0; i < boardNum; i++) {
            for (int j = 0; j < boardNum; j++) {
                if (board[i][j] == 0) {
                    return i;
                }
            }
        }
        return -1;
    }

    int findY(int[][] board) {
        for (int i = 0; i < boardNum; i++) {
            for (int j = 0; j < boardNum; j++) {
                if (board[i][j] == 0) {
                    return j;
                }
            }
        }
        return -1;
    }

    void swap(int[][] board, int x1, int y1, int x2, int y2) {
        if (checkforSwap(board, x1, y1, x2, y2))
            return;
        int temp = board[x1][y1];
        board[x1][y1] = board[x2][y2];
        board[x2][y2] = temp;
    }

    boolean checkforSwap(int[][] board, int x1, int y1, int x2, int y2) {
        if (x1 < 0 || x1 >= boardNum || y1 < 0 || y1 >= boardNum || x2 < 0 || x2 >= boardNum || y2 < 0
                || y2 >= boardNum) {
            System.out.println("Invalid move");
            return true;
        }
        return false;
    }

    boolean checkForCloserGoal(int[][] startingBoard, int[][] finalBoard) {
        // check for inversions
        int inversions = 0;
        for (int i = 0; i < boardNum; i++) {
            for (int j = 0; j < boardNum; j++) {
                if (startingBoard[i][j] == 0)
                    continue;
                for (int k = i; k < boardNum; k++) {
                    for (int l = j; l < boardNum; l++) {
                        if (startingBoard[k][l] == 0)
                            continue;
                        if (startingBoard[i][j] > startingBoard[k][l])
                            inversions++;
                    }
                }
            }
        }

        int finalInversion = 0;
        for (int i = 0; i < boardNum; i++) {
            for (int j = 0; j < boardNum; j++) {
                if (finalBoard[i][j] == 0)
                    continue;
                for (int k = i; k < boardNum; k++) {
                    for (int l = j; l < boardNum; l++) {
                        if (finalBoard[k][l] == 0)
                            continue;
                        if (finalBoard[i][j] > finalBoard[k][l])
                            finalInversion++;
                    }
                }
            }
        }

        if (finalInversion <= inversions)
            return true;
        return false;
    }

    boolean checkVisited(Computer computer, Computer newComputer) {
        if (computer.lastMove != newComputer.lastMove)
            return true;
        return false;
    }

    boolean moveUp() {
        if (x > 0) {
            swap(startBoard, x, y, x - 1, y);
            x--;
            return true;
        }
        return false;
    }

    boolean moveDown() {
        if (x < boardNum - 1) {
            swap(startBoard, x, y, x + 1, y);
            x++;
            return true;
        }
        return false;
    }

    boolean moveLeft() {
        if (y > 0) {
            swap(startBoard, x, y, x, y - 1);
            y--;
            return true;
        }
        return false;
    }

    boolean moveRight() {
        if (y < boardNum - 1) {
            swap(startBoard, x, y, x, y + 1);
            y++;
            return true;
        }
        return false;
    }

    boolean makeMove(String move) {
        switch (move) {
            case "U":
                if (moveUp())
                    return true;
            case "D":
                if (moveDown())
                    return true;
            case "L":
                if (moveLeft())
                    return true;
            case "R":
                if (moveRight())
                    return true;
        }
        return false;
    }

}

class Player extends Game {
    int x;
    int y;
    Player parent;

    Player(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    void moveUp() {
        if (x > 0) {
            startBoard[x][y] = startBoard[x - 1][y];
            startBoard[x - 1][y] = 0;
            x--;
        }
    }

    void moveDown() {
        if (x < boardNum - 1) {
            startBoard[x][y] = startBoard[x + 1][y];
            startBoard[x + 1][y] = 0;
            x++;
        }
    }

    void moveLeft() {
        if (y > 0) {
            startBoard[x][y] = startBoard[x][y - 1];
            startBoard[x][y - 1] = 0;
            y--;
        }
    }

    void moveRight() {
        if (y < boardNum - 1) {
            startBoard[x][y] = startBoard[x][y + 1];
            startBoard[x][y + 1] = 0;
            y++;
        }
    }

    void makeMove(int move) {
        switch (move) {
            case 0:
                moveUp();
                break;
            case 1:
                moveDown();
                break;
            case 2:
                moveLeft();
                break;
            case 3:
                moveRight();
                break;
        }
    }

}
