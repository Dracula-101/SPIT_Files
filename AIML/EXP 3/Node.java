import java.util.Comparator;

public class Node {
    public Node parent; // each node has a parent except root
    public int[] board = new int[16];
    public int depth;
    public int childCount = 0;
    public String direction;
    public int nodeID;
    public int cost;
    public int heuristic;

    public Node(int[] in_board, Node in_parent, String in_direction) {

        parent = in_parent;
        if (parent == null) {
            depth = 0; // root
        } else {
            depth = parent.depth + 1;
            parent.childCount++;
        }
        // copy board
        board = in_board;
        // set direction
        direction = in_direction;
        // set nodeID
        nodeID = Astar.nodeCount++;
        // set heuristic
        heuristic = misplacedTiles();
        // set cost
        cost = depth + heuristic;
    }

    // getters:
    public int getF() {
        return cost;
    }

    public int[] getBoard() {
        return board;
    }

    public Node getParent() {
        return parent;
    }

    public String getDirection() {
        return direction;
    }

    public Node getChild() {
        return this;
    }

    public int misplacedTiles() {

        int goalPosArray[] = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 }; // holds positions of
                                                                                                 // goal board 1..0
        int boardPosArray[] = new int[16]; // holds positions of current board
        int misplacedTiles = 0; // init

        for (int i = 0; i < 15; i++) {
            boardPosArray[i] = getPosition(i + 1, board);
        }
        boardPosArray[15] = getPosition(0, board);

        int boardVal;
        int goalVal;
        for (int i = 0; i < 16; i++) {
            boardVal = boardPosArray[i];
            goalVal = goalPosArray[i];
            if (boardVal != goalVal) {
                misplacedTiles++;
            }
        }
        // return number of misplaced tiles
        return misplacedTiles;
    }

    public int getPosition(int digit, int[] pattern) {
        for (int i = 0; i < 16; i++) {
            if (pattern[i] == digit) {
                return i;
            }
        }
        // finds the position of 0 in board
        return -1;
    }

    public Node moveLeft() {
        int[] pattern = board.clone(); // new board
        if (pattern[0] == 0 || pattern[4] == 0 || pattern[8] == 0 || pattern[12] == 0) {
            return null;
        } else {
            int zeroPosition = getPosition(0, pattern);
            pattern[zeroPosition] = pattern[zeroPosition - 1];
            pattern[zeroPosition - 1] = 0;
            return new Node(pattern, this, "Left-> ");
        }
    }

    // move 0 right if possible
    public Node moveRight() {
        int[] pattern = board.clone(); // new board
        if (pattern[3] == 0 || pattern[7] == 0 || pattern[11] == 0 || pattern[15] == 0) {
            // System.out.println("Cannot move right! Blank is in right column.");
            return null;
        } else { // move right
            int zeroPosition = getPosition(0, pattern);
            pattern[zeroPosition] = pattern[zeroPosition + 1]; // swap (slide)
            pattern[zeroPosition + 1] = 0;
            return new Node(pattern, this, "Right-> "); // create new node
        }
    }

    // move 0 up if possible
    public Node moveUp() {
        int[] pattern = board.clone(); // new board
        if (pattern[0] == 0 || pattern[1] == 0 || pattern[2] == 0 || pattern[3] == 0) {
            // System.out.println("Cannot move up! Blank is in top row.");
            return null;
        } else { // move up
            int zeroPosition = getPosition(0, pattern);
            pattern[zeroPosition] = pattern[zeroPosition - 4]; // swap (slide)
            pattern[zeroPosition - 4] = 0;
            return new Node(pattern, this, "Up-> "); // create new node
        }
    }

    // move 0 down if possible
    public Node moveDown() {
        int[] pattern = board.clone(); // new board
        if (pattern[12] == 0 || pattern[13] == 0 || pattern[14] == 0 || pattern[15] == 0) {
            // System.out.println("Cannot move down! Blank is in bottom row.");
            return null;
        } else { // move down
            int zeroPosition = getPosition(0, pattern);
            pattern[zeroPosition] = pattern[zeroPosition + 4]; // swap (slide)
            pattern[zeroPosition + 4] = 0;
            return new Node(pattern, this, "Down->"); // create new node
        }
    }

    // print just the board
    static public void printBoard(int[] puzzle) {
        System.out.print("\n-----------------------------\n");
        for (int i = 0; i < puzzle.length; i++) {
            System.out.printf("|  %2d  ", puzzle[i]);
            if (i % 4 == 3) {
                System.out.print("|\n-----------------------------\n");

            }
        }
    }
}

class NodeComparator implements Comparator<Node> {

    // Compare Two Nodes based on their f value: Comparator function for priority
    // queue

    @Override
    public int compare(Node o1, Node o2) {
        return o1.getF() - o2.getF();
    }
}