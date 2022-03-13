import java.util.HashMap;

public class Driver {

    public static void main(String[] args) {

        Huffman HM = new Huffman();
        // user INput
        System.out.print("\n1.Character Input\n2.String Input\nEnter your choice: ");
        int choice = HM.input.nextInt();
        switch (choice) {
            case 1:
                HM.userInput();
                HM.printArrays();
                HuffmanNode root1 = HM.makeTree();
                HM.printTree(root1, "");
                HM.displayMap();
                break;
            case 2:
                System.out.print("\nEnter the String to be encoded(without space)");
                System.out.print("\n->");
                String str = HM.input.next();
                HashMap<Character, Integer> chars = new HashMap<Character, Integer>();

                for (int i = 0; i < str.length(); i++) {
                    // System.out.print("\n" + str.charAt(i));
                    if (Character.isLetter(str.toLowerCase().charAt(i)) && !chars.containsKey(str.charAt(i))) {
                        chars.put(str.toLowerCase().charAt(i), 1);
                    } else if (chars.containsKey(str.charAt(i))) {
                        int value = chars.get(str.charAt(i));
                        value++;
                        chars.replace(str.charAt(i), value);
                    }
                }
                System.out.print("\nAll the Characters in the String are: ");
                HM.setup(chars);
                HuffmanNode root2 = HM.makeTree();
                HM.printTree(root2, "");
                HM.displayMap();
                break;
            default:
                break;
        }
        // HM.printQueue();
    }
}