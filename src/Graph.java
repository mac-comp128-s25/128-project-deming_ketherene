import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Graph {

    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;
    private int E;
    private int[][] matrix;

    public Graph(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative");
        this.V = V;
        this.E = 0;

        matrix = new int[V][V];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < i; j++) {
                matrix[i][j] = 0;
            }
        }
    }

    public Graph(Scanner in) {
        if (in == null) throw new IllegalArgumentException("argument is null");
        try {
            this.V = in.nextInt();
            if (V < 0) throw new IllegalArgumentException("number of vertices in a Graph must be nonnegative");

            matrix = new int[V][V];
            for (int v = 0; v < V; v++) {
                for(int v2 = 0; v2 < V; v2++) {
                    matrix[v][v2] = 0;
                }
            }

            int E = in.nextInt();
            if (E < 0) throw new IllegalArgumentException("number of edges in a Graph must be nonnegative");
            for (int i = 0; i < E; i++) {
                int v = in.nextInt();
                int w = in.nextInt();
                validateVertex(v);
                validateVertex(w);
                addEdge(v, w);
            }
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("invalid input format in Graph constructor", e);
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }

    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);

        if (matrix[v][w] == 0) {
            matrix[v][w] = 1;
            matrix[w][v] = 1;
            E++;
        }

    }

    public List<Integer> adj(int v) {
        validateVertex(v);
        List<Integer> adjacentVertices = new ArrayList<>();

        for (int i = 0; i < matrix[v].length; i++) {
            if (matrix[v][i] == 1) {
                adjacentVertices.add(i);
            }
        }
        return adjacentVertices;
    }

    public int degree(int v) {
        int count = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (i == v) {
                for (int j = 0; j < i; j++) {
                    if (matrix[i][j] == 1) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w = 0; w < V; w++) {
                if (matrix[v][w] == 1) {
                    s.append(w + " ");
                }
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}
