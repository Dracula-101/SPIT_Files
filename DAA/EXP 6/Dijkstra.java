import java.util.*;

public class dijkstra {

    int source;

    // The main method is where the program starts.
    void dijkstra_solve(int[][] graph) {
        int count = graph.length;
        boolean[] visited = new boolean[count];
        int[] distance = new int[count];

        for (int i = 0; i < distance.length; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        distance[source] = 0;
        for (int k = 0; k < distance.length - 1; k++) {

            int minVertex = findMin(distance, visited);
            visited[minVertex] = true;
            // explore the neighbours
            for (int i = 0; i < distance.length; i++) {
                if (graph[minVertex][i] != 0 && distance[minVertex] != Integer.MAX_VALUE) {
                    // checking if the there exists an edge between the two vertices, the neighbour
                    // should not be visited
                    // adding the weight of the edge to the distance of the min vertex
                    int newDistance = distance[minVertex] + graph[minVertex][i]; // Relaxation

                    if (newDistance < distance[i]) {
                        // updating the distance of the vertex if the value is lesser than the
                        // previous value of the same vertex
                        distance[i] = newDistance;
                    }
                }
            }
        }
        System.out.println("\nOutput\n(Vertex-> Distance):");
        for (int i = 0; i < distance.length; i++) {
            if (distance[i] == Integer.MAX_VALUE || distance[i] == 0) {
                continue;
            }
            System.out.println(i + "\t" + distance[i]);
        }
    }

    // finding the minimum distance vertex
    static int findMin(int[] distance, boolean[] visited) {
        int minVertex = -1; // initializing the minimum vertex to -1
        for (int i = 1; i < distance.length; i++) {
            // if the vertex is not visited and the distance is lesser than the min vertex
            if ((minVertex == -1 || distance[i] < distance[minVertex]) && !visited[i]) {
                minVertex = i;
            }
        }
        return minVertex; // returning the minimum vertex
    }

    public static void main(String[] args) throws Exception {
        try (// Driver code
                Scanner sc = new Scanner(System.in)) {
            System.out.println("----------------Dijkstra's Algorithm----------------");
            System.out.println("\nInput(TestCases-> Vertices-> Edges-> Each edge with weights)\n");
            int testCases = sc.nextInt();
            dijkstra T = new dijkstra();
            int negativeChecker = 0;
            for (int i = 0; i < testCases; i++) {
                int v = sc.nextInt(); // vertices
                int e = sc.nextInt(); // edges
                int[][] graph = new int[1024][1024]; // adjacency matrix
                int src = sc.nextInt();
                T.source = src;
                int dest = sc.nextInt();
                int cost = sc.nextInt(); // cost of the edge // set to store the vertices
                // if cost is negative, then the edge is not added
                graph[src][dest] = cost;
                if (cost < 0) {
                    negativeChecker = 1;
                    System.out.print("\nNegative edge not Added");
                    continue;
                }
                for (int j = 0; j < e - 1; j++) {
                    int p = sc.nextInt(); // source
                    int q = sc.nextInt(); // destination
                    cost = sc.nextInt(); // cost of the edge
                    if (cost < 0) {
                        negativeChecker = 1;
                        System.out.print("\nNegative edge not allowed");
                        break;
                    }

                    graph[p][q] = cost;
                }
                if (negativeChecker != 1)
                    T.dijkstra_solve(graph);
            }
            sc.close();
        }
    }
}
