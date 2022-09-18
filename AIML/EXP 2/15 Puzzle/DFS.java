import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class DFS {

    public List<Game> dfsMethod(Game start) {

        List<Game> pathToSolution = new ArrayList<Game>();
        Stack<Game> stack = new Stack<Game>();
        List<Game> visited = new ArrayList<Game>();
        stack.push(start);
        boolean goalFound = false;
        while (!stack.isEmpty() && !goalFound) {
            Game current = stack.peek();
            visited.add(current);
            stack.pop();
            current.makeMoves();
            for (int i = 0; i < current.children.size(); i++) {
                Game currentGame = current.children.get(i);
                if (currentGame.isGoal()) {
                    goalFound = true;
                    pathToSolution.add(currentGame);
                    System.out.print("\nTotal Visited Nodes: " + (visited.size()));
                    printPath(pathToSolution, currentGame);
                } else if (!containGame(visited, currentGame)) {
                    stack.push(currentGame);
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
