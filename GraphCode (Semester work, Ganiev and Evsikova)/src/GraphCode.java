import java.util.ArrayList;

//Класс GraphCode, хранящий список ребер для графа. Элемент – ребро, содержащее два номера своих вершин.

public class GraphCode {

    private ArrayList<Edge> edges;

    private class Edge {
        private int beginningOfEdge;
        private int endOfEdge;
        private Edge() {};
        public Edge(int beginningOfEdge, int endOfEdge) {
            this.beginningOfEdge = beginningOfEdge;
            this.endOfEdge = endOfEdge;
        }
        public int[] get() {
            return new int[] {beginningOfEdge, endOfEdge};
        }
    }

    public GraphCode() {
        edges = new ArrayList<>();
    }

    //конструктор  построение графа по матрице инцидентности
    public GraphCode(int[][] incidenceMatrix) throws IllegalArgumentException, NullPointerException {
        if (incidenceMatrix != null) {
            //количество вершин = количеству строк матрицы
            int numberOfVertices = incidenceMatrix.length;
            //количество рёбер = количество столбцов матрицы, берём количество столбцов в первой строке,
            //так как она точно есть
            int numberOfEdges = incidenceMatrix[0].length;
            //сравниваем остальные строки по длине с первой, если не совпадают, то говорим, что матрицы некорректная
            for (int i = 1; i < numberOfVertices; i++) {
                if (incidenceMatrix[i].length != numberOfEdges) {
                    throw new IllegalArgumentException("Incorrect incidence matrix");
                }
            }
            //обходим матрицы по столбцам, в каждом стобце должна быть ровно одна 1 и ровно одна -1
            //или же ровно одна 2, остальные нули. Если не так, то говорим, что матрица некорректная
            for (int j = 0; j < numberOfEdges; j++) {
                //перед тем как пойти по столбцу, инициализируем начало и конец ребра невозможными значениями
                //вершины графа нумеруем 0, 1, 2, 3 и так далее, поэтому -1 - невозможное значение
                int beginningOfEdge = -1;
                int endOfEdge = -1;
                //начинаю обход по столбцу
                for (int i = 0; i < numberOfVertices; i++) {
                    //обработка петли
                    if (incidenceMatrix[i][j] == 2) {
                       //если значение менялось, то ребро в этом столбце уже и было считано ранее
                        if (beginningOfEdge != -1) {
                            throw new IllegalArgumentException("Incorrect incidence matrix");
                        }
                        //данная вершина является и началом и концом ребра
                        beginningOfEdge = i;
                        endOfEdge = i;
                    }
                    //обработка начала ребра
                    else if (incidenceMatrix[i][j] == 1) {
                        //если значение менялось, то начало ребра уже было считано ранее
                        if (beginningOfEdge != -1) {
                            throw new IllegalArgumentException("Incorrect incidence matrix");
                        }
                        beginningOfEdge = i;
                    }
                    //обработка конца ребра
                    else if (incidenceMatrix[i][j] == -1) {
                        //если значение менялось, то конец ребра уже был считан ранее
                        if (endOfEdge != -1) {
                            throw new IllegalArgumentException("Incorrect incidence matrix");
                        }
                        endOfEdge = i;
                    }
                    //если в таблице встречаются числа, отличные от 0, 1, -1, то матрица некорректна
                    else if (incidenceMatrix[i][j] != 0) {
                        throw new IllegalArgumentException("Incorrect incidence matrix");
                    }
                }
                //если ребро считано, вносим его в список
                if (beginningOfEdge != -1 && endOfEdge != -1) {
                    this.insert(beginningOfEdge, endOfEdge);
                }
                //если считано только начало ребра (конец не считан) или наоборот, то матрица некорректна
                else if (!(beginningOfEdge == -1 && endOfEdge == -1)) {
                    throw new IllegalArgumentException("Incorrect incidence matrix");
                }
            }
        }
        else {
            throw new NullPointerException("Argument points to null");
        }
    }

    // построение матрицы инцидентности
    public int [][] getIncidenceMatrix() {
        return null;
    }

    //вставка некоторого ребра (i,j)
    public void insert(int i, int j) {

    }

    // удаление ребра (i,j) из списка
    public void delete(int i, int j) {

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
