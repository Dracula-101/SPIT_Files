import java.util.ArrayList;
import java.util.Scanner;

public class MissionaryCannibal {

    public static int initialMissionary = 3, initialCannibal = 3;
    public static ArrayList<State> visited = new ArrayList<>();
    public static int visitedStates = 0, counter = 0;
    public static boolean flag = false;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the number of missionaries and cannibals: ");
        initialMissionary = input.nextInt();
        initialCannibal = input.nextInt();
        input.close();

        State startPoint = new State(initialMissionary, initialCannibal, 0, 0, 0, "");
        // make a iterative solution for missionary cannibal
        visited.add(startPoint);
        findSoln(startPoint);

    }

    static void findSoln(State state) {

        if (state.cannibalLeft == 0 && state.missionaryLeft == 0) {
            System.out.print(state.path);
            System.out.print("\n___  ___            \\___/ MMM  CCC");
            System.out.println("\n\nAll Cannibals and Missionaries are saved");
            System.exit(0);
        }
        StateSpace stateSpace = new StateSpace(state);
        for (State newState : stateSpace.getNextStates()) {
            if (checkForVisited(newState)) {
                visited.add(newState);
                findSoln(newState);
            }
        }
    }

    static boolean checkForVisited(State state) {
        for (State visitedState : visited) {
            if (visitedState.missionaryLeft == state.missionaryLeft && visitedState.cannibalLeft == state.cannibalLeft
                    && visitedState.boat == state.boat && visitedState.missionaryRight == state.missionaryRight
                    && visitedState.cannibalRight == state.cannibalRight) {
                return false;
            }
        }
        return true;
    }

}
