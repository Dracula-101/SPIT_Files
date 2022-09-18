import java.util.ArrayList;
import java.util.Scanner;

class Node {
    String uniqueId;
    int leftCap, rightCap;
    String path;

    public Node(int x, int y, String path) {
        this.leftCap = x;
        this.rightCap = y;
        this.uniqueId = x + "," + y;
        this.path = path;
    }

    static Node emptyA(Node jug) {
        return new Node(0, jug.rightCap, WaterJug.generatePath(jug));
    }

    static Node emptyB(Node jug) {
        return new Node(jug.leftCap, 0, WaterJug.generatePath(jug));
    }

    static Node fillA(Node jug) {
        return new Node(WaterJug.jugA, jug.rightCap, WaterJug.generatePath(jug));
    }

    static Node fillB(Node jug) {
        return new Node(jug.leftCap, WaterJug.jugB, WaterJug.generatePath(jug));
    }

    static Node fillAToB(Node jug) {
        int x = jug.leftCap;
        int y = jug.rightCap;
        int z = WaterJug.jugB;
        if (x + y <= z) {
            return new Node(0, x + y, WaterJug.generatePath(jug));
        } else {
            return new Node(x - (z - y), z, WaterJug.generatePath(jug));
        }
    }

    static Node fillBToA(Node jug) {
        int x = jug.leftCap;
        int y = jug.rightCap;
        int z = WaterJug.jugA;
        if (x + y <= z) {
            return new Node(x + y, 0, WaterJug.generatePath(jug));
        } else {
            return new Node(z, y - (z - x), WaterJug.generatePath(jug));
        }
    }

    // static Node pourTillFullAToB(Node jug) {
    // int amount = jug.leftCap;
    // if (jug.rightCap + amount > WaterJug.jugB) {
    // amount = WaterJug.jugB - jug.rightCap;
    // }
    // return new Node(jug.leftCap - amount, jug.rightCap + amount);
    // }

    // static Node pourTillFullBToA(Node jug) {
    // int amount = jug.rightCap;
    // if (jug.leftCap + amount > WaterJug.jugA) {
    // amount = WaterJug.jugA - jug.leftCap;
    // }
    // return new Node(jug.leftCap + amount, jug.rightCap - amount);
    // }

    // // pour until A is empty
    // static Node pourFullAToB(Node jug) {
    // int amount = jug.leftCap;
    // return new Node(0, jug.rightCap + amount);
    // }

    // // pour until B is empty
    // static Node pourFullBToA(Node jug) {
    // int amount = jug.rightCap;
    // return new Node(jug.leftCap + amount, 0);
    // }

    boolean isValid(Node jug) {
        if ((jug.leftCap >= 0 && jug.rightCap >= 0) && (jug.leftCap <= WaterJug.jugA && jug.rightCap <= WaterJug.jugB)
                && (jug.leftCap + jug.rightCap) <= (WaterJug.jugA + WaterJug.jugB) && (jug.leftCap != 0
                        || jug.rightCap != 0)) {
            return true;
        } else {
            return false;
        }
    }

}

public class WaterJug {
    public static int jugA = 4, jugB = 3, targetA = 2, targetB = 0;
    // public static Queue<Node> queue = new LinkedList<>();
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
        sc.close();
        Node jug = new Node(0, 0, "");
        visited.add(jug);
        findSoln(jug);

    }

    static void findSoln(Node jug) {

        if ((jug.leftCap == targetA && jug.rightCap == targetB)
                || (jug.leftCap == targetB && jug.rightCap == targetA)) {
            System.out.println("The path is: " + generatePath(jug));
            System.out.println("Number of nodes traversed: " + visitedNodes);
            System.exit(0);
        }
        NodeSpace nodeSpace = new NodeSpace(jug);
        for (Node node : nodeSpace.getNextNodes()) {
            if (checkForVisited(node)) {
                visited.add(node);
                visitedNodes++;
                findSoln(node);
            }
        }
    }

    static String generatePath(Node node) {
        String path = "[ " + node.leftCap + "," + node.rightCap + " ] ";
        return node.path + path;
    }

    static boolean checkForEmptyJug(Node node, int x, int y) {
        if (node.leftCap >= x && node.rightCap >= y) {

            if ((node.leftCap - x + node.rightCap - y) == 0)
                return true;
        }
        return false;
    }

    static boolean checkForVisited(Node jug) {
        for (Node node : visited) {
            if (node.uniqueId.equals(jug.uniqueId) && node.leftCap == jug.leftCap && node.rightCap == jug.rightCap) {
                return false;
            }
        }
        return true;
    }

}
