import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class MissionaryCannibal {

    public static int initialMissionary, initialCannibal;
    public static Queue<State> queue = new LinkedList<>();
    public static ArrayList<State> visited = new ArrayList<>();
    public static int visitedStates = 0, counter = 0;
    public static boolean flag = false;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the number of missionaries and cannibals: ");
        initialMissionary = input.nextInt();
        initialCannibal = input.nextInt();

        // System.out.println("Enter the target number of missionaries and cannibals:
        // ");

        // targetMissionary = input.nextInt();
        // targetCannibal = input.nextInt();
        input.close();

        State startPoint = new State(initialMissionary, initialCannibal, 0, 0, 0, "");
        queue.add(startPoint);
        while (!queue.isEmpty()) {
            visitedStates++;
            State startState = queue.poll();
            visited.add(startState);
            StateSpace newState = new StateSpace(startState);
            for (State newStateSpace : newState.getNextStates()) {
                if (queue.contains(newStateSpace) || visited.contains(newStateSpace)) {
                    continue;
                }
                if (newStateSpace.isGoal()) {
                    System.out.print(
                            "\nThe path is: \n" + newStateSpace.path + "\n" + State.pathGenerator(newStateSpace));
                    System.out.println(
                            "The required arrangment can be achieved after " + (visitedStates + 1) + " move(s)");
                    System.out.println("Number of nodes traversed: " + visitedStates);
                    return;
                }
                queue.add(newStateSpace);
            }
        }
    }
}
