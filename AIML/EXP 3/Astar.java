import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Astar {
    private int goal[] = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0 };
    PriorityQueue<Node> queue = new PriorityQueue<Node>(4, new NodeComparator());
    public static int nodeCount;
    public static int heuristic_static;

    // Constructor to initialize the queue
    public Astar(Node root) {
        System.out.print("\nStarting A* with heuristic: ");

        nodeCount = 0;

        // Add the root node to the queue
        if (isGoal(root))
            return;

        addToQueue(root);
        // Running the loop until the queue is empty and finding the goal node
        while (queue.size() > 0) {
            Node u = dequeueMin();

            for (Node v : generateChildren(u)) {
                if (!isVisited(v)) {
                    // If the node is the goal node, print the path and return
                    if (isGoal(v))
                        return;
                    // Else add the node to the queue
                    addToQueue(v);
                }
            }
        }
        System.out.println("Solution not found.");
    }

    // put Node into frontier
    public void addToQueue(Node node) {
        queue.add(node);
    }

    // remove Node from frontier
    public Node dequeueMin() {
        return queue.poll(); // extract min
    }

    public ArrayList<Node> generateChildren(Node parent) {
        // create children for up down left right
        Node U = parent.moveUp();
        Node D = parent.moveDown();
        Node L = parent.moveLeft();
        Node R = parent.moveRight();

        ArrayList<Node> children = new ArrayList<>(4);
        // Add the created nodes to the arrayList (as long as they're not null)
        if (U != null)
            children.add(U);
        if (D != null)
            children.add(D);
        if (L != null)
            children.add(L);
        if (R != null)
            children.add(R);

        return children;
    }

    // check if Node is goal state
    public void success(Node solutionNode) {
        printMoves(solutionNode);
        System.out.print("\nMoves: " + solutionNode.getDirection());
        printBoard(solutionNode);
        System.out.print("\n--------------------------------------------\n");
        printNumNodes(solutionNode);
    }

    // check if Node is visited or not
    public boolean isVisited(Node node) {
        for (Node n : queue) {
            if (Arrays.equals(n.getBoard(), node.getBoard()))
                return true;
        }
        return false;
    }

    // check if Node and current board are the same
    public boolean isGoal(Node v) {
        if (Arrays.equals(v.getBoard(), goal)) {
            success(v);
            return true;
        }
        return false;
    }

    // calculate and print move sequence
    public void printMoves(Node node) {
        for (int i = 1; i < getTotalNodes(node); i++) {
            System.out.print("\n\nMoves " + getNodeInIndex(i, node).getDirection());
            printBoard(getNodeInIndex(i, node));
        }
    }

    // get total number of nodes in depth
    int getTotalNodes(Node node) {
        int total = 0;
        while (node.getParent() != null) {
            total++;
            node = node.getParent();
        }
        return total;
    }

    // get node in index
    Node getNodeInIndex(int index, Node node) {
        Node temp = node;
        int totalNode = getTotalNodes(node);
        for (int i = 0; i < totalNode - index; i++) {
            temp = temp.parent;
        }
        return temp;
    }

    // print how many nodes were expanded (created)
    public void printNumNodes(Node node) {
        System.out.println("Number of Nodes expanded: " + nodeCount);
    }

    // print board
    static public void printBoard(Node node) {
        System.out.print("\n-----------------------------\n");
        for (int i = 0; i < node.board.length; i++) {
            System.out.printf("|  %2d  ", node.board[i]);
            if (i % 4 == 3) {
                System.out.print("|\n-----------------------------\n");

            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Starting 15 puzzle....");

        int initialBoard[] = new int[] {
                1, 2, 3, 4, 5, 6, 0, 8, 9, 10, 7, 11, 13, 14, 15, 12
        };

        System.out.println("\nInitial Board: ");
        //make the start node for the puzzle
        Node start = new Node(initialBoard, null, "#");
        printBoard(start);
        //run the A* algorithm
        new Astar(start);
    }

}