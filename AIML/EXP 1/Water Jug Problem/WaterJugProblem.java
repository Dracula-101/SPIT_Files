import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Node {
    int leftCap, rightCap;
    String path;

    public Node(int x, int y, String z) {
        this.leftCap = x;
        this.rightCap = y;
        this.path = z;
    }
}

public class WaterJugProblem {
    public static int jugA, jugB, targetA, targetB;
    public static Queue<Node> queue = new LinkedList<>();
    public static ArrayList<Node> visited = new ArrayList<>();
    public static int visitedNodes = 0, counter = 0;
    public static boolean flag = false;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the Capacity of Jug A: ");
        jugA = sc.nextInt();
        System.out.print("Enter the Capacity of Jug B: ");
        jugB = sc.nextInt();
        System.out.print("Enter the Target Capacity of Jug A: ");
        targetA = sc.nextInt();
        System.out.print("Enter the Target Capacity of Jug B: ");
        targetB = sc.nextInt();
        Node root = new Node(0, 0, "");
        queue.add(root);
        Node jug;

        while (!queue.isEmpty()) {
            visitedNodes++;
            jug = queue.poll();
            if (jug.leftCap == targetA && jug.rightCap == targetB) {
                // System.out.println("The required capacity can be achieved after " +
                // (visitedNodes + 1) + " move(s)");
                // System.out.println("Number of nodes traversed: " + visitedNodes);
                System.out.println("The path is: " + generatePath(jug));
                break;
            }

            Node temp = jug;
            // Fill jug A
            if (jug.leftCap < targetA) {
                temp = new Node(jugA, jug.rightCap, generatePath(jug));
                if (!visited.contains(temp)) {
                    queue.add(temp);
                    visited.add(temp);
                }
            }
            // Fill Jug B
            if (jug.rightCap < targetB) {
                temp = new Node(jug.leftCap, jugB, generatePath(jug));
                if (!visited.contains(temp)) {
                    queue.add(temp);
                    visited.add(temp);
                }
            }
            // Empty Jug A
            if (jug.leftCap > 0) {
                temp = new Node(0, jug.rightCap, generatePath(jug));
                queue.add(temp);
                visited.add(temp);
            }

            // Empty Jug B

            if (jug.rightCap > 0) {
                temp = new Node(jug.leftCap, 0, generatePath(jug));
                queue.add(temp);
                visited.add(temp);
            }

            // Pour from Jug A to Jug B until its full
            if (jug.leftCap > 0 && (jug.leftCap + jug.rightCap) >= jugB) {
                temp = new Node(jug.leftCap - (jugB - jug.rightCap), jugB, generatePath(jug));
                if (!visited.contains(temp)) {
                    queue.add(temp);
                    visited.add(temp);
                }
            }

            // Pour from Jug B to Jug A until its full
            if (jug.rightCap > 0 && (jug.leftCap + jug.rightCap) >= jugA) {
                temp = new Node(jugA, jug.rightCap - (jugA - jug.leftCap), generatePath(jug));
                if (!visited.contains(temp)) {
                    queue.add(temp);
                    visited.add(temp);
                }
            }
            // Puor all water from 1st to 2nd
            if (jug.leftCap > 0 && (jug.leftCap + jug.rightCap) <= jugB) {
                temp = new Node(0, jug.leftCap + jug.rightCap, generatePath(jug));
                if (!visited.contains(temp)) {
                    queue.add(temp);
                    visited.add(temp);
                }
            }

            // Puor all water from 2nd to 1st
            if (jug.rightCap > 0 && (jug.leftCap + jug.rightCap) <= jugA) {
                temp = new Node(jug.leftCap + jug.rightCap, 0, generatePath(jug));
                if (!visited.contains(temp)) {
                    queue.add(temp);
                    visited.add(temp);
                }
            }

        }
        if (!flag)
            System.out.print("\nCannot achieve the required capacity");
        sc.close();
    }

    static String generatePath(Node node) {
        String path = "[ " + node.leftCap + "," + node.rightCap + " ] ";
        return node.path + path;
    }

    static void printVisitedNodes() {
        System.out.println("Visited Nodes: ");
        for (Node node : visited) {
            System.out.print(" [ " + node.leftCap + " " + node.rightCap + " ], ");
        }
    }
}
