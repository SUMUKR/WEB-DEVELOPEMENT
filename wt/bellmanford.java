
import java.util.Scanner;

public class bellmanford {

    public int distance[];
    public int numb_vert;
    public static final int MAX_VALUE = 999;

    // Constructor to initialize the number of vertices
    public bellmanford(int numb_vert) {
        this.numb_vert = numb_vert;
        distance = new int[numb_vert + 1];
    }

    // Bellman-Ford algorithm to calculate shortest distances
    public void BellmanfordpEvaluation(int source, int adj_matrix[][]) {

        // Initialize all distances to infinity
        for (int node = 1; node <= numb_vert; node++)
            distance[node] = MAX_VALUE;

        // Distance from source to itself is zero
        distance[source] = 0;

        // Relax all edges V-1 times
        for (int node = 1; node <= numb_vert - 1; node++) {
            for (int src_node = 1; src_node <= numb_vert; src_node++) {
                for (int dest_node = 1; dest_node <= numb_vert; dest_node++) {

                    // Check whether an edge exists
                    if (adj_matrix[src_node][dest_node] != MAX_VALUE) {

                        // Update the shortest distance
                        if (distance[dest_node] >
                            distance[src_node] + adj_matrix[src_node][dest_node]) {

                            distance[dest_node] =
                                distance[src_node] + adj_matrix[src_node][dest_node];
                        }
                    }
                }
            }
        }

        // Check for a negative-weight cycle
        for (int src_node = 1; src_node <= numb_vert; src_node++) {
            for (int dest_node = 1; dest_node <= numb_vert; dest_node++) {

                if (adj_matrix[src_node][dest_node] != MAX_VALUE) {
                    if (distance[dest_node] >
                        distance[src_node] + adj_matrix[src_node][dest_node]) {

                        System.out.println(
                            "The graph contains negative edge cycle");
                    }
                }
            }
        }

        // Display the routing table
        System.out.println("Routing Table for Router " + source + " is");
        System.out.println("Destination\tDistance");

        for (int vertex = 1; vertex <= numb_vert; vertex++) {
            System.out.println(vertex + "\t\t" + distance[vertex]);
        }
    }

    public static void main(String args[]) {

        int numb_vert = 0;
        Scanner scan = new Scanner(System.in);

        // Read the number of vertices
        System.out.println("Enter the number of vertices");
        numb_vert = scan.nextInt();

        int adj_matrix[][] = new int[numb_vert + 1][numb_vert + 1];

        // Read the adjacency matrix
        System.out.println("Enter the adjacency matrix");

        for (int src_node = 1; src_node <= numb_vert; src_node++) {
            for (int dest_node = 1; dest_node <= numb_vert; dest_node++) {

                adj_matrix[src_node][dest_node] = scan.nextInt();

                // Distance from a vertex to itself is zero
                if (src_node == dest_node) {
                    adj_matrix[src_node][dest_node] = 0;
                    continue;
                }

                // Replace zero with infinity (no edge)
                if (adj_matrix[src_node][dest_node] == 0) {
                    adj_matrix[src_node][dest_node] = MAX_VALUE;
                }
            }
        }

        // Find shortest paths from every vertex
        for (int i = 1; i <= numb_vert; i++) {
            bellmanford bf = new bellmanford(numb_vert);
            bf.BellmanfordpEvaluation(i, adj_matrix);
        }

        scan.close();
    }
}
