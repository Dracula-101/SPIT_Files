import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BFS {
    static int visitedNodes = 0;

    public List<Game> bfsMethod(Game start) {
        List<Game> pathToSolution = new ArrayList<Game>();
        List<Game> queue = new LinkedList<Game>();
        List<Game> visited = new ArrayList<Game>();

        queue.add(start);
        boolean goalFound = false;
        while (!queue.isEmpty() && !goalFound) {
            Game current = queue.get(0);
            visited.add(current);
            queue.remove(0);
            ++visitedNodes;
            current.makeMoves();
            // current.printPuzzle(current.puzzle);
            for (int i = 0; i < current.children.size(); i++) {
                Game currentGame = current.children.get(i);
                if (currentGame.isGoal()) {
                    goalFound = true;
                    pathToSolution.add(currentGame);
                    System.out.print("\nTotal Visited Nodes: " + (visitedNodes));
                    printPath(pathToSolution, currentGame);
                }
                if (!containGame(visited, currentGame)) {
                    queue.add(currentGame);
                }
            }
        }
        return pathToSolution;
    }

    public void printPath(List<Game> path, Game game) {
        System.out.print("\nPath to solution: ");
        Game current = game;
        path.add(current);
        while (current != null) {
            current = current.parent;
            path.add(current);
        }
    }

    public static boolean containGame(List<Game> allGames, Game thisGame) {
        boolean contains = false;

        for (Game game : allGames) {
            if (game.isVisited(thisGame.puzzle)) {
                contains = true;
            }

        }
        return contains;
    }

}
