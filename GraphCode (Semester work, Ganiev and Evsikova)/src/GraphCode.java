import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

/**
 * The GraphCode class stores a list of edges of a directed graph with
 * loops and multiple edges. An element of the list is an edge containing
 * two numbers of its vertices. Vertex numbers take values from {@code 1}
 * to {@code maxVertexNumber}. The first number is the number of the
 * vertex from which the edge leaves. The second number is the number of
 * the vertex into which the edge enters. The GraphCode class allows to
 * work with the incidence matrix of a graph, insert and remove edges,
 * delete vertices, and perform some other operations.
 */
public class GraphCode {

    private ArrayList<Edge> edges;
    private int maxVertexNumber;

    /**
     * The inner private class Edge stores the oriented edge of the graph
     * as a pair of two integer values: {@code beginningOfEdge} and {@code
     * endOfEdge}. The first value is the number of the vertex, which is
     * the beginning of the oriented edge. The second value is the vertex
     * number, which is the end of the oriented edge.
     */
    private class Edge {
        private int beginningOfEdge;
        private int endOfEdge;
        private Edge(int beginningOfEdge, int endOfEdge) {
            this.beginningOfEdge = beginningOfEdge;
            this.endOfEdge = endOfEdge;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            return beginningOfEdge == edge.beginningOfEdge &&
                    endOfEdge == edge.endOfEdge;
        }
        @Override
        public int hashCode() {
            return Objects.hash(beginningOfEdge, endOfEdge);
        }
        @Override
        public String toString() {
            return "(" + beginningOfEdge + " -> " + endOfEdge + ")";
        }
    }

    /**
     * Constructs an empty list of oriented edges of a graph. By default,
     * the graph also has no vertices.
     */
    public GraphCode() {
        edges = new ArrayList<>();
        maxVertexNumber = 0;
    }

    /**
     * Constructs a list of oriented edges of a graph using a given graph
     * incidence matrix. The incidence matrix of a directed graph is a
     * n × m matrix B where n and m are the number of vertices and edges
     * respectively, such that B(i,j) = 1 if the edge e(j) leaves vertex
     * v(i), B(i,j) = -1 if it enters vertex v(i), B(i,j) = 2 if it leaves
     * vertex v(i) and enters vertex v(i) at the same time and 0 otherwise.
     *
     * @param incidenceMatrix the incidence matrix of a directed graph.
     * @throws IllegalArgumentException if the elements of the {@code
     *                                  incidenceMatrix} are equal to
     *                                  values other than -1, 0, 1 or 2;
     *                                  if the rows of the {@code
     *                                  incidenceMatrix} differ in length;
     *                                  if the {@code incidenceMatrix}
     *                                  column does not contain exactly
     *                                  one 1 and exactly one -1 or does
     *                                  not contain exactly one 2.
     * @throws NullPointerException     if the {@code incidenceMatrix} is
     *                                  null.
     */
    public GraphCode(int[][] incidenceMatrix) throws IllegalArgumentException, NullPointerException {
        this();
        readIncidenceMatrix(incidenceMatrix);
    }

    /**
     * Returns the incidence matrix of a directed graph. The incidence
     * matrix is constructed from a given list of edges of the directed
     * graph. The incidence matrix of a directed graph is a n × m matrix B
     * where n and m are the number of vertices and edges respectively,
     * such that B(i,j) = 1 if the edge e(j) leaves vertex v(i), B(i,j) =
     * -1 if it enters vertex v(i), B(i,j) = 2 if it leaves vertex v(i)
     * and enters vertex v(i) at the same time and 0 otherwise. If the
     * list of edges is empty, then the method returns {@code null}.
     *
     * @return the incidence matrix of a directed graph as a
     * two-dimensional array of integers or {@code null} if the list of
     * edges of the graph is empty.
     */
    public int[][] getIncidenceMatrix() {
        if (maxVertexNumber == 0) {
            return null;
        }
        int[][] incidenceMatrix = new int[maxVertexNumber][edges.size()];
        for (int i = 0; i < edges.size(); i++) {
            int beginningOfEdge = edges.get(i).beginningOfEdge;
            int endOfEdge = edges.get(i).endOfEdge;
            if (beginningOfEdge == endOfEdge) {
                incidenceMatrix[beginningOfEdge - 1][i] = 2;
            }
            else {
                incidenceMatrix[beginningOfEdge - 1][i] = 1;
                incidenceMatrix[endOfEdge - 1][i] = -1;
            }
        }
        return incidenceMatrix;
    }

    /**
     * Inserts a new oriented edge {@code (beginningOfEdge, endOfEdge)}
     * into the list of edges of the directed graph. The vertices in the
     * oriented graph have numbers from {@code 1} to {@code
     * maxVertexNumber} (highest number of existing vertices), so the
     * inserted edge must have the numbers of its beginning and end from
     * {@code 1} to {@code maxVertexNumber + 1}  if it is incident to the
     * existing vertices of the graph or {@code (maxVertexNumber + 1,
     * maxVertexNumber + 2)}, {@code (maxVertexNumber + 2, maxVertexNumber
     * + 1)} if it is not incident to the existing ones vertices of the
     * graph.
     *
     * @param beginningOfEdge   vertex number, which is the beginning of
     *                          the inserted oriented edge.
     * @param endOfEdge         vertex number, which is the end of the
     *                          inserted oriented edge.
     * @throws IllegalArgumentException if the beginning or end of the
     *                                  inserted edge of the directed
     *                                  graph has numbers outside the
     *                                  range from {@code 1} to {@code
     *                                  maxVertexNumber + 1} and are not
     *                                  equal to {@code (maxVertexNumber +
     *                                  1, maxVertexNumber + 2)} or {@code
     *                                  (maxVertexNumber + 2,
     *                                  maxVertexNumber + 1)}.
     */
    public void insert(int beginningOfEdge, int endOfEdge) throws IllegalArgumentException {
        if (beginningOfEdge < 1 || endOfEdge < 1 ||
                beginningOfEdge > maxVertexNumber + 2 || endOfEdge > maxVertexNumber + 2 ||
                (beginningOfEdge == maxVertexNumber + 2 && endOfEdge == maxVertexNumber + 2)) {
            if (maxVertexNumber == 0) {
                throw new IllegalArgumentException("The graph has no vertices yet. " +
                        "You can insert only the edges: 1-1, 1-2 or 2-1");
            }
            else {
                throw new IllegalArgumentException("The graph has vertices with numbers from " + 1 + " to " +
                        maxVertexNumber + ". You can insert edges connecting vertices with numbers from 1 to " +
                        (maxVertexNumber + 1) + " or edges: " + (maxVertexNumber + 1) + "-" + (maxVertexNumber + 2) +
                        ", " + (maxVertexNumber + 2) + "-" + (maxVertexNumber + 1));
            }
        }
        else {
            if (Integer.max(beginningOfEdge, endOfEdge) > maxVertexNumber) {
                maxVertexNumber = Integer.max(beginningOfEdge, endOfEdge);
            }
            edges.add(new Edge(beginningOfEdge, endOfEdge));
        }
    }

    /**
     * Removes a directed edge {@code (beginningOfEdge, endOfEdge)} from
     * the list of edges of a directed graph. If this edge of the graph
     * does not exist in the list of edges or incorrect arguments are
     * entered, the delete method does not perform any actions. Vertices
     * that are the beginning or end of an edge of a graph are not deleted
     * when it is deleted.
     *
     * @param beginningOfEdge   vertex number, which is the beginning of
     *                          the deleted oriented edge.
     * @param endOfEdge         vertex number, which is the end of the
     *                          deleted oriented edge.
     */
    public void delete(int beginningOfEdge, int endOfEdge) {
        if (beginningOfEdge > 0 && endOfEdge > 0 &&
                beginningOfEdge <= maxVertexNumber && endOfEdge <= maxVertexNumber) {
            edges.remove(new Edge(beginningOfEdge, endOfEdge));
        }
    }

    /**
     * Returns a list of oriented edges incident to the {@code vertex} of
     * the directed graph. The list of oriented edges is returned in the
     * form of a new {@code GraphCode} storing this list. If there are no
     * edges of the graph incident to the {@code vertex} or if the {@code
     * vertex} does not exist in the graph, then the {@code GraphCode}
     * with an empty list of edges is returned.
     *
     * @param vertex    vertex of the directed graph for which the list of
     *                  edges incident to it is returned.
     * @return new {@code GraphCode} storing a list of edges of the
     * directed graph incident to the {@code vertex} or storing an empty
     * list if there are no edges of the graph incident to the {@code
     * vertex}, or if the {@code vertex} does not exist in the graph.
     */
    public GraphCode getEdgesWithNode(int vertex) {
        GraphCode graph = new GraphCode();
        if (vertex > 0 && vertex <= maxVertexNumber) {
            Collections.copy(graph.edges, edges);
            graph.maxVertexNumber = maxVertexNumber;
            for (Edge edge : edges) {
                if (vertex != edge.beginningOfEdge && vertex != edge.endOfEdge) {
                    graph.delete(edge.beginningOfEdge, edge.endOfEdge);
                }
            }
        }
        return graph;
    }

    /**
     * Modifies the list of edges of a directed graph by removing a {@code
     * vertex} from the graph. Removes from the list of edges all edges
     * incident to the {@code vertex}. If the {@code vertex} does not
     * exist in the directed graph, then the modification method does not
     * perform any actions.
     *
     * @param vertex    vertex that is removed from the directed graph
     *                  with all edges that are incident to it.
     */
    public void modify(int vertex) {
        if (vertex > 0 && vertex <= maxVertexNumber) {
            for (int i = edges.size() - 1; i >= 0; i--) {
                if (vertex == edges.get(i).beginningOfEdge || vertex == edges.get(i).endOfEdge) {
                    edges.remove(i);
                }
                else {
                    if (edges.get(i).beginningOfEdge > vertex) {
                        edges.get(i).beginningOfEdge--;
                    }
                    if (edges.get(i).endOfEdge > vertex) {
                        edges.get(i).endOfEdge--;
                    }
                }
            }
            maxVertexNumber--;
        }
    }

    /**
     * Returns a list of vertices of a directed graph, outdegree which is
     * greater than {@code m}. If the list of edges of the directed graph
     * is empty, then the method returns {@code null}.
     *
     * @param m integer, in the returned list of vertices added vertices
     *          of a directed graph, if outdegree greater than this
     *          integer.
     * @return a list of vertices of a directed graph, outdegree which is
     * greater than {@code m}, or {@code null} if the list of edges of the
     * directed graph is empty.
     */
    public ArrayList<Integer> outdegreeShow(int m) {
        if (edges.isEmpty()) {
            return null;
        }
        ArrayList<Integer> vertices = new ArrayList<>();
        if (m < 0) {
            for (int i = 1; i <= maxVertexNumber; i++) {
                vertices.add(i);
            }
            return vertices;
        }
        int[] outdegrees = new int[maxVertexNumber];
        for (Edge edge : edges) {
            outdegrees[edge.beginningOfEdge - 1]++;
        }
        for (int i = 0; i < maxVertexNumber; i++) {
            if (outdegrees[i] > m) {
                vertices.add(i + 1);
            }
        }
        return vertices;
    }

    /**
     * Returns a list of vertices of a directed graph, indegree which is
     * greater than {@code m}. If the list of edges of the directed graph
     * is empty, then the method returns {@code null}.
     *
     * @param m integer, in the returned list of vertices added vertices
     *          of a directed graph, if indegree greater than this
     *          integer.
     * @return a list of vertices of a directed graph, indegree which is
     * greater than {@code m}, or {@code null} if the list of edges of the
     * directed graph is empty.
     */
    public ArrayList<Integer> indegreeShow(int m) {
        if (edges.isEmpty()) {
            return null;
        }
        ArrayList<Integer> vertices = new ArrayList<>();
        if (m < 0) {
            for (int i = 1; i <= maxVertexNumber; i++) {
                vertices.add(i);
            }
            return vertices;
        }
        int[] indegrees = new int[maxVertexNumber];
        for (Edge edge : edges) {
            indegrees[edge.endOfEdge - 1]++;
        }
        for (int i = 0; i < maxVertexNumber; i++) {
            if (indegrees[i] > m) {
                vertices.add(i + 1);
            }
        }
        return vertices;
    }

    /**
     * Private method for creating a list of oriented edges of a graph
     * using a given graph incidence matrix. The method is used in the
     * constructor and can be used in other class methods. The incidence
     * matrix of a directed graph is a n × m matrix B where n and m are
     * the number of vertices and edges respectively, such that B(i,j) = 1
     * if the edge e(j) leaves vertex v(i), B(i,j) = -1 if it enters
     * vertex v(i), B(i,j) = 2 if it leaves vertex v(i) and enters vertex
     * v(i) at the same time and 0 otherwise.
     *
     * @param incidenceMatrix the incidence matrix of a directed graph.
     * @throws IllegalArgumentException if the elements of the {@code
     *                                  incidenceMatrix} are equal to
     *                                  values other than -1, 0, 1 or 2;
     *                                  if the rows of the {@code
     *                                  incidenceMatrix} differ in length;
     *                                  if the {@code incidenceMatrix}
     *                                  column does not contain exactly
     *                                  one 1 and exactly one -1 or does
     *                                  not contain exactly one 2.
     * @throws NullPointerException     if the {@code incidenceMatrix} is
     *                                  null.
     */
    private void readIncidenceMatrix(int[][] incidenceMatrix) throws IllegalArgumentException, NullPointerException {
        if (incidenceMatrix != null) {
            int numberOfVertices = incidenceMatrix.length;
            int numberOfEdges = incidenceMatrix[0].length;
            for (int i = 1; i < numberOfVertices; i++) {
                if (incidenceMatrix[i].length != numberOfEdges) {
                    throw new IllegalArgumentException("Incorrect incidence matrix");
                }
            }
            for (int j = 0; j < numberOfEdges; j++) {
                int beginningOfEdge = 0;
                int endOfEdge = 0;
                for (int i = 0; i < numberOfVertices; i++) {
                    if (incidenceMatrix[i][j] == 2) {
                        if (beginningOfEdge != 0) {
                            throw new IllegalArgumentException("Incorrect incidence matrix");
                        }
                        beginningOfEdge = i + 1;
                        endOfEdge = i + 1;
                    }
                    else if (incidenceMatrix[i][j] == 1) {
                        if (beginningOfEdge != 0) {
                            throw new IllegalArgumentException("Incorrect incidence matrix");
                        }
                        beginningOfEdge = i + 1;
                    }
                    else if (incidenceMatrix[i][j] == -1) {
                        if (endOfEdge != 0) {
                            throw new IllegalArgumentException("Incorrect incidence matrix");
                        }
                        endOfEdge = i + 1;
                    }
                    else if (incidenceMatrix[i][j] != 0) {
                        throw new IllegalArgumentException("Incorrect incidence matrix");
                    }
                }
                if (beginningOfEdge != 0 && endOfEdge != 0) {
                    if (Integer.max(beginningOfEdge, endOfEdge) > maxVertexNumber) {
                        maxVertexNumber = Integer.max(beginningOfEdge, endOfEdge);
                    }
                    this.insert(beginningOfEdge, endOfEdge);
                }
                else if (!(beginningOfEdge == 0 && endOfEdge == 0)) {
                    throw new IllegalArgumentException("Incorrect incidence matrix");
                }
            }
        }
        else {
            throw new NullPointerException("Argument points to null");
        }
    }

    /**
     * Prints the incidence matrix of a directed graph. The method does
     * not print anything if the list of edges of the directed graph is
     * empty. The incidence matrix of a directed graph is a n × m matrix B
     * where n and m are the number of vertices and edges respectively,
     * such that B(i,j) = 1 if the edge e(j) leaves vertex v(i), B(i,j) =
     * -1 if it enters vertex v(i), B(i,j) = 2 if it leaves vertex v(i)
     * and enters vertex v(i) at the same time and 0 otherwise.
     */
    public void printIncidenceMatrix() {
        int[][] incidenceMatrix = this.getIncidenceMatrix();
        if (incidenceMatrix != null) {
            for (int i = 0; i < maxVertexNumber; i++) {
                for (int j = 0; j < edges.size(); j++) {
                    System.out.printf("%" + 2 + "d", incidenceMatrix[i][j]);
                    System.out.print(" ");
                }
                System.out.println();
            }
        }
    }

    /**
     * Returns a list of edges of a directed graph as a two-dimensional
     * array of integers. Each row of a two-dimensional array contains two
     * integers, the first is the number of the vertex of the graph, which
     * is the beginning of the oriented edge, the second is the number of
     * the vertices of the graph, which is the end of the oriented graph.
     * If the list of edges of the directed graph is empty, then the
     * method returns {@code null}.
     *
     * @return a list of edges of an oriented graph as a two-dimensional
     * array of integers or {@code null} if the list of edges of an
     * oriented graph is empty.
     */
    public int[][] get() {
        if (maxVertexNumber == 0) {
            return null;
        }
        int[][] edgesOfGraph = new int[edges.size()][2];
        for (int i = 0; i < edges.size(); i++) {
            edgesOfGraph[i][0] = edges.get(i).beginningOfEdge;
            edgesOfGraph[i][1] = edges.get(i).endOfEdge;
        }
        return edgesOfGraph;
    }

    /**
     * Compares the specified object with this {@code GraphCode} for
     * equality. Returns <tt>true</tt> if the given object is also a
     * {@code GraphCode}, lists of edges of two Graph Codes are the same
     * size, the numbers of vertices in two Graph Codes are the same, and
     * every oriented edge of the given {@code GraphCode} is contained in
     * this {@code GraphCode}.
     *
     * @param o object to be compared for equality with this {@code
     *          GraphCode}.
     * @return <tt>true</tt> if the specified object is equal to this
     * {@code GraphCode}.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GraphCode graphCode = (GraphCode) o;
        if (maxVertexNumber == graphCode.maxVertexNumber && edges.size() == graphCode.edges.size()) {
            boolean[] flags = new boolean[edges.size()];
            for (int i = 0; i < edges.size(); i++) {
                for (int j = 0; j < edges.size(); j++) {
                    if (edges.get(i).equals(graphCode.edges.get(j)) && !flags[j]) {
                        flags[j] = true;
                        break;
                    }
                }
            }
            for (int i = 0; i < edges.size(); i++) {
                if (!flags[i]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Returns the hash code value for this {@code GraphCode}. The hash
     * code of a {@code GraphCode} is defined to be the sum of the hash
     * codes of the edges in the {@code GraphCode} and hash code of the
     * vertex of the directed graph with the highest number. The hash
     * code of a <tt>null</tt> element is defined to be zero.
     *
     * @return the hash code value for this {@code GraphCode}.
     */
    @Override
    public int hashCode() {
        return Objects.hash(edges, maxVertexNumber);
    }

    /**
     * Returns a string representation of this {@code GraphCode}. The
     * string representation consists of a list of all edges of a directed
     * graph, enclosed in curly brackets (<tt>"{}"</tt>).
     *
     * @return a string representation of this {@code GraphCode}.
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Graph edges = {");
        for (int i = 0; i < edges.size() - 1; i++) {
            str.append(edges.get(i));
            str.append(", ");
        }
        str.append(edges.get(edges.size()- 1));
        str.append('}');
        return str.toString();
    }

}