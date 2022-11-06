import java.util.*;

public class IDASTAR {
    PriorityQueue<Node> queue = new PriorityQueue<>();
    int numNodesExpanded = 0;
    boolean goalFound = false;

    PriorityQueue<Integer> intqueue;

    // main method
    public static void main(String[] args) {
        IDASTAR theIDASTAR;
        int[] puzzle = {
                0, 0, 1, 0,
                1, 0, 0, 1,
                1, 1, 0, 0,
                0, 0, 1, 0
        };
        // Right Down Right Down Right Down
        // declaring root node
        Node root;
        theIDASTAR = new IDASTAR();
        // initializing root node
        root = new Node(puzzle, puzzle.length, null, 0, "Start");

        // calling the IDA* method
        System.out.println("\nIDA Star Method For Rat Maze:");

        // setting the root node cost
        theIDASTAR.idManhattan(root);
    }// end main()

    public void idManhattan(Node root) {
        // initializing the queue
        intqueue = new PriorityQueue<Integer>(11, new intComparator());
        int limit = 0;
        // adding the root to the queue
        intqueue.add(root.fcost);

        // while the queue is not empty
        while (!goalFound) {
            if (!intqueue.isEmpty()) {
                // removing the first element from the queue
                limit = intqueue.poll();
            }

            // calling the iterative deepening search
            idStarMethod(limit, root);

        }

    }

    public void idStarMethod(int limit, Node root) {

        queue = new PriorityQueue<Node>(11, new comparatorForMisplaced());
        intqueue = new PriorityQueue<Integer>(11, new intComparator());

        // push root node into the priority queue to initialize the search
        queue.add(root);

        // while the queue is not empty
        while (!queue.isEmpty()) {

            // remove the first element from the queue
            Node currentNode = queue.remove();

            // check to see if the currentNode is the goal
            if (currentNode.reachedGoal()) {
                System.out.println("GOAL REACHED!");
                currentNode.printMoves();
                printNumNodesExpanded();
                goalFound = true;
                return;
            }

            currentNode.expandNode(currentNode);
            numNodesExpanded++;

            // check each child of currentNode and do stuff with it
            for (Node child : currentNode.children) {

                // checks if child's f is less than or equal to the depth limit. if it is, then
                // it can be added to the queue if not already in it
                if (child.fcost <= limit) {
                    queue.add(child);
                }

                // if child's f is greater than the depth limit, then it is added to the
                // intqueue
                // to be used in the next iteration of the search
                else {
                    intqueue.add(child.fcost);
                }

            }
        }
    }

    // method to print number of nodes expanded
    public void printNumNodesExpanded() {
        System.out.println("Number of nodes expanded: " + numNodesExpanded);
    }
}

class Node {
    public List<Node> children = new ArrayList<Node>();
    public int[] puzzle;
    public int columns;
    public int currentPos;
    Node parent;
    String direction;
    int nodeCost = 0;
    // data members for misplaced tiles method
    int hMisplaced = 0;
    int fcost = 0;

    // default constructor. when a node is created, its puzzle is set
    public Node(int[] puzzleParameter, int length, Node parent, int pos, String direction) {

        puzzle = new int[length];
        columns = (int) Math.sqrt(puzzle.length);
        this.parent = parent;
        this.currentPos = pos;
        this.direction = direction;

        // setting misplaced tiles data members
        hMisplaced = manhattanDist();
        fcost = nodeCost + hMisplaced;

    }

    // method to move right. adds new child to children list
    public void moveRight(Node parent) {
        // rat in a maze move right
        // check if the rat can move right
        if ((currentPos + 1) % columns != 0 && puzzle[currentPos + 1] != 1) {
            int[] puzzleClone = puzzle.clone();
            int temp = puzzleClone[currentPos];
            puzzleClone[currentPos] = puzzleClone[currentPos + 1];
            puzzleClone[currentPos + 1] = temp;
            Node child = new Node(puzzleClone, puzzleClone.length, parent, currentPos + 1, "Right");
            child.nodeCost = this.nodeCost + 1;

            // special f data member for misplaced f heuristic
            child.fcost = child.nodeCost + child.hMisplaced;
            children.add(child);

        }

    }

    // method to move left. adds new child to children list
    public void moveLeft(Node parent) {
        if (currentPos % columns != 0 && puzzle[currentPos - 1] != 1) {
            int[] puzzleClone = puzzle.clone();
            int temp = puzzleClone[currentPos];
            puzzleClone[currentPos] = puzzleClone[currentPos - 1];
            puzzleClone[currentPos - 1] = temp;

            Node child = new Node(puzzleClone, puzzleClone.length, parent, currentPos - 1, "Left");

            // setting the node cost
            child.nodeCost = this.nodeCost + 1;
            child.fcost = child.nodeCost + child.hMisplaced;
            // adding children to the children list
            children.add(child);

        }
    }

    // method to move up. adds new child to children list
    public void moveUp(Node parent) {
        if (currentPos - columns >= 0 && puzzle[currentPos - columns] != 1) {
            int[] puzzleClone = puzzle.clone();
            int temp = puzzleClone[currentPos];
            puzzleClone[currentPos] = puzzleClone[currentPos - columns];
            puzzleClone[currentPos - columns] = temp;

            Node child = new Node(puzzleClone, puzzleClone.length, parent, currentPos - columns, "Up");
            // setting the node cost
            child.nodeCost = this.nodeCost + 1;
            child.fcost = child.nodeCost + child.hMisplaced;

            // children list
            children.add(child);

        }
    }

    // method to move down. adds new child to children list
    public void moveDown(Node parent) {
        if (currentPos + columns < puzzle.length && puzzle[currentPos + columns] != 1) {
            int[] puzzleClone = puzzle.clone();
            int temp = puzzleClone[currentPos];
            puzzleClone[currentPos] = puzzleClone[currentPos + columns];
            puzzleClone[currentPos + columns] = temp;

            Node child = new Node(puzzleClone, puzzleClone.length, parent, currentPos + columns, "Down");

            // setting the node cost
            child.nodeCost = this.nodeCost + 1;
            child.fcost = child.nodeCost + child.hMisplaced;

            // adding children to the children list
            children.add(child);

        }
    }

    // method to check if the node is the goal
    public boolean reachedGoal() {
        // rat in a maze goal reached condition
        if (currentPos == puzzle.length - 1) {
            return true;
        }
        return false;
    }

    // method to expand parent node, adds new child nodes to the children data
    // member
    public void expandNode(Node node) {

        // rat in a maze
        node.moveRight(node);
        node.moveLeft(node);
        node.moveUp(node);
        node.moveDown(node);

    }

    // prints out puzzle. used for debugging
    public void printBoard() {
        System.out.print("|\n---------------------------\n");
        for (int i = 0; i < puzzle.length; i++) {
            System.out.printf("|  %2d  ", puzzle[i]);
            if (i % 4 == 3) {
                System.out.print("|\n---------------------------\n");

            }
        }
    }

    // prints out the moves to reach goal
    public void printMoves() { // prints the moves string
        if (parent != null) {
            parent.printMoves();
        }
        System.out.print("\n" + direction);
        // printBoard();
    }

    // method to calculate the manhattan distance
    public int manhattanDist() {
        // rat in a maze
        int row = currentPos / columns;
        int col = currentPos % columns;
        int goalRow = (puzzle.length - 1) / columns;
        int goalCol = (puzzle.length - 1) % columns;
        int dist = Math.abs(row - goalRow) + Math.abs(col - goalCol);
        return dist;
    }
}// end of Node class

class comparatorForMisplaced implements Comparator<Node> {
    public int compare(Node n1, Node n2) {

        return n1.fcost - n2.fcost;
    }
}

class intComparator implements Comparator<Integer> {
    public int compare(Integer n1, Integer n2) {

        return n1 - n2;
    }
}