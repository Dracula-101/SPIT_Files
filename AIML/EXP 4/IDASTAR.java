import java.util.*;

public class IDASTAR {
    PriorityQueue<Node> queue = new PriorityQueue<>();
    int numNodesExpanded = 0;
    boolean foundMisplaced = false;

    PriorityQueue<Integer> intqueue;

    // main method
    public static void main(String[] args) {
        IDASTAR theIDASTAR;
        int[] puzzle = {
                1, 8, 2, 0, 4, 3, 7, 6, 5
        };

        // declaring root node
        Node root;
        theIDASTAR = new IDASTAR();
        // initializing root node
        root = new Node(puzzle, puzzle.length, null, "Start");

        // calling the IDA* method
        System.out.println("\nIDA Star Method:");

        // doing the search (misplaced tiles style)
        theIDASTAR.idsMisplaced(root);
    }// end main()

    public void idsMisplaced(Node root) {
        // initializing the queue
        intqueue = new PriorityQueue<Integer>(11, new intComparator());
        int limit = 0;
        // adding the root to the queue
        intqueue.add(root.fMisplaced);

        // while the queue is not empty
        while (!foundMisplaced) {
            if (!intqueue.isEmpty()) {
                // removing the first element from the queue
                limit = intqueue.poll();
            }

            // calling the iterative deepening search
            idastarMisplaced(limit, root);

        }

    }

    public void idastarMisplaced(int limit, Node root) {

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
                foundMisplaced = true;
                return;
            }

            currentNode.expandNode(currentNode);
            numNodesExpanded++;

            // check each child of currentNode and do stuff with it
            for (Node child : currentNode.children) {

                // checks if child's f is less than or equal to the depth limit. if it is, then
                // it can be added to the queue if not already in it
                if (child.fMisplaced <= limit) {
                    queue.add(child);
                }

                // if child's f is greater than the depth limit, then it is added to the
                // intqueue
                // to be used in the next iteration of the search
                else {
                    intqueue.add(child.fMisplaced);
                }

            }
        }
    }

    // method to print number of nodes expanded
    public void printNumNodesExpanded() {
        System.out.println("Number of nodes expanded: " + numNodesExpanded);
    }
}
