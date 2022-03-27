import java.util.*;

class HuffmanNode {
    // Huffman node class
    int data;
    char character;

    // constructor
    HuffmanNode left, right;

    HuffmanNode() {
        this.left = null;
        this.right = null;
    }

    // constructor
    HuffmanNode(char ch, int data) {
        this.left = null;
        this.right = null;
        this.data = data;
        this.character = ch;
    }

}

class MyComparator implements Comparator<HuffmanNode> {
    // Comparator class
    public int compare(HuffmanNode x, HuffmanNode y) {

        return x.data - y.data;
    }
}

public class Huffman {
    // Huffman class

    char charArray[];
    int charFreq[];
    int characters;
    // User input
    Scanner input = new Scanner(System.in);
    // Huffman tree
    PriorityQueue<HuffmanNode> queue;
    // hashmap to store the encodings
    HashMap<Character, String> map = new HashMap<Character, String>();

    // User input method
    public void userInput() {
        System.out.print("\nEnter the number of characters to be read: ");
        characters = input.nextInt();

        // Priority queue to store the Huffman nodes
        queue = new PriorityQueue<HuffmanNode>(characters, new MyComparator());

        charArray = new char[characters];
        charFreq = new int[characters];

        System.out.print("\nEnter the characters below\n->");
        for (int i = 0; i < characters; i++) {
            charArray[i] = input.next().charAt(0);
        }

        System.out.print("\nEnter the Frequency of the Characters\n");
        for (int i = 0; i < characters; i++) {
            System.out.print("-> '" + charArray[i] + "' : ");
            charFreq[i] = input.nextInt();
        }

    }

    public void printArrays() {
        System.out.println();
        System.out.print("\n|   Characters\t|" + "   Frequency\t|\n");
        System.out.print("-----------------------------------\n");
        for (int i = 0; i < characters; i++) {
            System.out.print("\n|        " + charArray[i] + "\t|" + "          " + charFreq[i] + "\t|");
        }

    }

    public void setup(HashMap<Character, Integer> values) {
        // setup method
        characters = values.size();
        charArray = new char[characters];
        charFreq = new int[characters];

        queue = new PriorityQueue<HuffmanNode>(characters, new MyComparator());

        for (int i = 0; i < values.size(); i++) {
            charArray[i] = (char) values.keySet().toArray()[i];
            charFreq[i] = (int) values.values().toArray()[i];
        }
        printArrays();
    }

    public HuffmanNode makeTree() {
        // makeTree method
        System.out.print("\nStarted Making the Huffman Tree\n");
        for (int i = 0; i < characters; i++) {
            // Adding the nodes to the queue
            HuffmanNode hNode = new HuffmanNode(charArray[i], charFreq[i]);
            queue.add(hNode);
        }
        HuffmanNode root = null;
        int counter = 1;
        // Making the tree
        while (queue.size() > 1) {
            System.out.print("\n-----------------------------------\n");
            System.out.print("\nStep " + counter);
            HuffmanNode x = queue.peek();
            queue.poll();

            HuffmanNode y = queue.peek();
            queue.poll();

            HuffmanNode f = new HuffmanNode();
            // Combining the nodes
            f.data = x.data + y.data;
            f.character = '+';
            // Adding the combined node to the queue
            f.left = x;
            f.right = y;
            root = f;
            printNode(root, x, y);
            queue.add(f);
            counter++;
        }

        return root;
    }

    public void printNode(HuffmanNode root, HuffmanNode x, HuffmanNode y) {
        System.out.print("\n\nParent Node: " + " " + root.data);
        System.out.print("\n| |");
        System.out.printf("\n| \\_Left Child:\t|   " + x.character + "\t |   " + x.data + " \t|");
        System.out.print("\n|");
        System.out.printf("\n\\_Right Child:\t|   " + y.character + "\t |   " + y.data + " \t|");
    }

    public void printTree(HuffmanNode root, String characters) {
        // System.out.println(root+" "+characters);
        if (root.left == null && root.right == null && Character.isLetter(root.character)) {
            map.put(root.character, characters);
            return;
        }
        // Printing the left child
        printTree(root.left, characters + "0");
        printTree(root.right, characters + "1");

    }

    public void displayMap() {
        // Displaying the map
        System.out.println("\n\nThe Encoding is: ");
        for (Map.Entry m : map.entrySet())
            System.out.println(m.getKey() + " " + m.getValue());

    }
}