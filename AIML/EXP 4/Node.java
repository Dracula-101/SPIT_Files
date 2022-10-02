import java.util.*;

public class Node {
    public List<Node> children = new ArrayList<Node>();
    public int[] puzzle;
    public int indexOfZero;
    public int columns;
    Node parent;
    String direction;
    int nodeCost = 0;
    int goalCost = 0;
    int estimatedCost = 0;

    //data members for misplaced tiles method
    int hMisplaced = 0;
    int fMisplaced = 0;

    //default constructor. when a node is created, its puzzle is set
    public Node(int[] puzzleParameter, int length, Node parent, String direction) {

        puzzle = new int[length];
        columns = (int)Math.sqrt(puzzle.length);
        setPuzzle(puzzleParameter);
        this.parent = parent;
        this.direction = direction;
        estimatedCost = nodeCost + goalCost;

        //setting misplaced tiles data members
        hMisplaced = getMisplacedTiles();
        fMisplaced = nodeCost + hMisplaced;

    }


    //method to set puzzle data member to the puzzleParameter
    public void setPuzzle(int[] puzzleParameter) {
        for (int i = 0; i < puzzle.length; i ++) {
            puzzle[i] = puzzleParameter[i];

            if (puzzle[i] == 0) { //finds the index of 0 in the puzzle
                indexOfZero = i;
            }
        }

    }

    //method to move right. adds new child to children list
    public void moveRight(Node parent) {

        if (indexOfZero % columns < columns - 1) {
            int[] puzzleClone = puzzle.clone();
            puzzleClone[indexOfZero] = puzzleClone[indexOfZero + 1];
            puzzleClone[indexOfZero + 1] = 0;


            Node child = new Node(puzzleClone, puzzle.length, parent, "Moved Right");
            child.nodeCost = this.nodeCost + 1;
            child.estimatedCost = child.nodeCost + child.goalCost;

            //special f data member for misplaced f heuristic
            child.fMisplaced = child.nodeCost + child.hMisplaced;
            children.add(child);

        }

    }

    //method to move left. adds new child to children list
    public void moveLeft(Node parent) {
        if (indexOfZero % columns != 0) {
            int[] puzzleClone = puzzle.clone();
            puzzleClone[indexOfZero] = puzzleClone[indexOfZero - 1];
            puzzleClone[indexOfZero - 1] = 0;
            //Node created with the new puzzle
            Node child = new Node(puzzleClone, puzzle.length, parent, "Moved Left");

            //setting the node cost
            child.nodeCost = this.nodeCost + 1;
            child.estimatedCost = child.nodeCost + child.goalCost;
            child.fMisplaced = child.nodeCost + child.hMisplaced;
            //adding children to the children list
            children.add(child);

        }
    }

    //method to move up. adds new child to children list
    public void moveUp(Node parent) {
        if (indexOfZero >= columns) {
            int[] puzzleClone = puzzle.clone();
            puzzleClone[indexOfZero] = puzzleClone[indexOfZero - columns];
            puzzleClone[indexOfZero - columns] = 0;

            //Node created with the new puzzle
            Node child = new Node(puzzleClone, puzzle.length, parent, "Moved Up");

            //setting the node cost
            child.nodeCost = this.nodeCost + 1;
            child.estimatedCost = child.nodeCost + child.goalCost;
            child.fMisplaced = child.nodeCost + child.hMisplaced;
            
            //children list
            children.add(child);

        }
    }

    //method to move down. adds new child to children list
    public void moveDown(Node parent) {
        if (indexOfZero < columns * (columns - 1)) {
            int[] puzzleClone = puzzle.clone();
            puzzleClone[indexOfZero] = puzzleClone[indexOfZero + columns];
            puzzleClone[indexOfZero + columns] = 0;

            //Node created with the new puzzle
            Node child = new Node(puzzleClone, puzzle.length, parent, "Moved Down");

            //setting the node cost
            child.nodeCost = this.nodeCost + 1;
            child.estimatedCost = child.nodeCost + child.goalCost;
            child.fMisplaced = child.nodeCost + child.hMisplaced;

            //adding children to the children list
            children.add(child);

        }
    }

    //method to check if the node is the goal
    public boolean reachedGoal() {
        if (puzzle[puzzle.length-1] != 0) { //if the last index is not 0, then puzzle is not solved
            return false;
        }

        int lesserValue = puzzle[0]; //get the int value at the first index to compare to next index

        //goal is reached if the array is in order. if array is not in order, return false
        for (int i = 1; i < puzzle.length -1; i++) {
            if (lesserValue > puzzle[i]) {
                return false;
            }
            lesserValue = puzzle[i];
        }

        return true; //goal reached
    }

    //method to expand parent node, adds new child nodes to the children data member
    public void expandNode(Node node) {


        //expands in order: up, down, left, right
        moveUp(node);
        moveDown(node);
        moveLeft(node);
        moveRight(node);

    }

    //prints out puzzle. used for debugging
    public void printBoard() {
        System.out.print("\n----------------------\n");
        for (int i = 0; i < puzzle.length; i++) {
            System.out.printf("|  %2d  ", puzzle[i]);
            if (i % 3 == 2) {
                System.out.print("|\n----------------------\n");

            }
        }
    }

    //prints out the moves to reach goal
    public void printMoves() { // prints the moves string
        if (parent != null) {
            parent.printMoves();
        }
        System.out.print("\n" + direction);
        printBoard();
    }

    //method to find the number of tiles away a number is on the puzzle.
    public int findNumTilesAway(int index, int numberAtIndex) {
        return Math.abs((numberAtIndex - 1) % columns - index % columns) + Math.abs((numberAtIndex- 1)/ columns - index/ columns);
    }

    // method to get misplaced tiles heuristic of node
    public int getMisplacedTiles() {
        int accumulator = 0;
        for (int i = 0; i < puzzle.length; i++) {
            if (puzzle[i] != i + 1 && puzzle[i] != 0) { //if the number at current index does not belong there
                accumulator++;
            }
        }
        return accumulator;
    }
}// end of Node class

class myComparator implements Comparator<Node> {
    public int compare(Node n1, Node n2) {

        return n1.estimatedCost - n2.estimatedCost;
    }

}

class comparatorForMisplaced implements Comparator<Node> {
    public int compare(Node n1, Node n2) {

        return n1.fMisplaced - n2.fMisplaced;
    }
}

class intComparator implements Comparator<Integer> {
    public int compare(Integer n1, Integer n2) {

        return n1 - n2;
    }
}