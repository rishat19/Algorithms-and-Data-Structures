import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] m = new int[3][4];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                m[i][j] = scanner.nextInt();
            }
        }
        GraphCode graph = new GraphCode(m);
        graph.insert(4, 1);
        graph.insert(2, 4);
        graph.insert(5, 5);
        graph.delete(3, 2);
        graph.modify(4);
        graph.modify(4);
        graph.printIncidenceMatrix();
    }
}
