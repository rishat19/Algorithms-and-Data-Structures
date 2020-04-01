import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

//Класс GraphCode, хранящий список ребер для графа. Элемент – ребро, содержащее два номера своих вершин.

public class GraphCode {

    private ArrayList<Edge> edges;
    private int maxVertexNumber;

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

    public GraphCode() {
        edges = new ArrayList<>();
        maxVertexNumber = 0;
    }

    //конструктор  построение графа по матрице инцидентности
    public GraphCode(int[][] incidenceMatrix) throws IllegalArgumentException, NullPointerException {
        this();
        readIncidenceMatrix(incidenceMatrix);
    }

    // построение матрицы инцидентности
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

    //вставка некоторого ребра (i,j)
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

    // удаление ребра (i,j) из списка
    public void delete(int beginningOfEdge, int endOfEdge) {
        if (beginningOfEdge > 0 && endOfEdge > 0 &&
                beginningOfEdge <= maxVertexNumber && endOfEdge <= maxVertexNumber) {
            edges.remove(new Edge(beginningOfEdge, endOfEdge));
        }
    }

    // возврат списка ребер, инцидентных вершине i
    public GraphCode getEdgesWithNode(int i) {
        GraphCode graph = new GraphCode();
        if (i > 0 && i <= maxVertexNumber) {
            Collections.copy(graph.edges, edges);
            graph.maxVertexNumber = maxVertexNumber;
            for (Edge edge : edges) {
                if (i != edge.beginningOfEdge && i != edge.endOfEdge) {
                    graph.delete(edge.beginningOfEdge, edge.endOfEdge);
                }
            }
        }
        return graph;
    }

    //модифицировать список в связи с удалением вершины i из графа (удалить все ребра, инцидентные удаленной вершине)
    public void modify(int i) {
        if (i > 0 && i <= maxVertexNumber) {
            for (int j = edges.size() - 1; j >= 0; j--) {
                if (i == edges.get(j).beginningOfEdge || i == edges.get(j).endOfEdge) {
                    edges.remove(j);
                }
                else {
                    if (edges.get(j).beginningOfEdge > i) {
                        edges.get(j).beginningOfEdge--;
                    }
                    if (edges.get(j).endOfEdge > i) {
                        edges.get(j).endOfEdge--;
                    }
                }
            }
            maxVertexNumber--;
        }
    }

    //возвратить список вершин, полустепень исхода которых более m.
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

    //возвратить список вершин, полустепень захода которых более m.
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

    public void printIncidenceMatrix() {
        int[][] incidenceMatrix = this.getIncidenceMatrix();
        for (int i = 0; i < maxVertexNumber; i++) {
            for (int j = 0; j < edges.size(); j++) {
                System.out.printf("%" + 2 + "d", incidenceMatrix[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public int[][] get() {
        int[][] edgesOfGraph = new int[edges.size()][2];
        for (int i = 0; i < edges.size(); i++) {
            edgesOfGraph[i][0] = edges.get(i).beginningOfEdge;
            edgesOfGraph[i][1] = edges.get(i).endOfEdge;
        }
        return edgesOfGraph;
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(edges, maxVertexNumber);
    }

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