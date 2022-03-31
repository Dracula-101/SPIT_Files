import java.util.*;

public class shortestpath {
    // The main method is where the program starts.
    static void dijkstra(int[][] graph, int source) {
        boolean[] visited = new boolean[300];
        int[] distance = new int[300];

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
        System.out.println("\nOutput->");
        System.out.println("Vertex\t\tDistance from Source");
        for (int i = 0; i < distance.length; i++) {
            if (distance[i] == Integer.MAX_VALUE) {
                continue;
            }
            System.out.println(i + "\t\t" + distance[i]);
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

    void display(int[][] graph, int vertices) {
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {
        // Driver code
        Scanner sc = new Scanner(System.in);
        System.out.println("     ||  Dijkstra Algorithm  ||");
        System.out.println("\nInput->");
        int test = sc.nextInt();
        for (int i = 0; i < test; i++) {
            int flag = 1;
            int v = sc.nextInt(); // vertices
            int e = sc.nextInt(); // edges
            int[][] graph = new int[300][300]; // adjacency matrix
            int src = sc.nextInt();
            int dest = sc.nextInt();
            int cost = sc.nextInt(); // cost of the edge
            Set<Integer> set = new HashSet<Integer>(); // set to store the vertices
            graph[src][dest] = cost;
            set.add(src);
            // if cost is negative, then the edge is not added
            if (cost < 0) {
                System.out.println("limitating negative weight");
                continue;
            }
            for (int j = 0; j < e - 1; j++) {
                int p = sc.nextInt(); // source
                int q = sc.nextInt(); // destination
                cost = sc.nextInt(); // cost of the edge
                if (cost < 0) {
                    flag = 0;
                    System.out.println("imitating negative weight");
                    break;
                }

                graph[p][q] = cost;
            }
            if (flag == 0) {
                continue;
            }
            dijkstra(graph, src);
        }
        sc.close();
    }
}