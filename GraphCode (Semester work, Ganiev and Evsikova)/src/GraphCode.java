import java.util.ArrayList;
import java.util.Objects;

//Класс GraphCode, хранящий список ребер для графа. Элемент – ребро, содержащее два номера своих вершин.

public class GraphCode {

    private ArrayList<Edge> edges;
    private int maxVertexNumber;

    private class Edge {
        private int beginningOfEdge;
        private int endOfEdge;
        public Edge(int beginningOfEdge, int endOfEdge) {
            this.beginningOfEdge = beginningOfEdge;
            this.endOfEdge = endOfEdge;
        }
        public int getBeginningOfEdge() {
            return beginningOfEdge;
        }
        public int getEndOfEdge() {
            return endOfEdge;
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
    }

    public GraphCode() {
        edges = new ArrayList<>();
        maxVertexNumber = 0;
    }

    //конструктор  построение графа по матрице инцидентности
    public GraphCode(int[][] incidenceMatrix) throws IllegalArgumentException, NullPointerException {
        this();
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

    // построение матрицы инцидентности
    public int[][] getIncidenceMatrix() {
        if (maxVertexNumber == 0) {
            return null;
        }
        int[][] incidenceMatrix = new int[maxVertexNumber][edges.size()];
        for (int i = 0; i < edges.size(); i++) {
            int beginningOfEdge = edges.get(i).getBeginningOfEdge();
            int endOfEdge = edges.get(i).getEndOfEdge();
            if (beginningOfEdge == endOfEdge) {
                incidenceMatrix[beginningOfEdge - 1][i] = 2;
                incidenceMatrix[endOfEdge - 1][i] = 2;
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
            edges.add(new Edge(beginningOfEdge, endOfEdge));
        }
    }

    // удаление ребра (i,j) из списка
    public void delete(int beginningOfEdge, int endOfEdge) {
        if (beginningOfEdge > 0 && endOfEdge > 0 && beginningOfEdge <= maxVertexNumber && endOfEdge <= maxVertexNumber) {
            edges.remove(new Edge(beginningOfEdge, endOfEdge));
        }
    }

    // возврат списка ребер, инцидентных вершине i
    public GraphCode getEdgesWithNode(int i) {
        return null;
    }

    //модифицировать список в связи с удалением вершины i из графа (удалить все ребра, инцидентные удаленной вершине)
    public void modify(int i) {

    }

    //возвратить список вершин, степень инцидентности которых более m.
    public ArrayList<Integer> show(int m) {
        return null;
    }

}
