import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] m = new int[3][];
        m[0] = new int[3];
        //m[1] = new int[3];
        m[1] = new int[3];
        m[2] = new int[3];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                m[i][j] = scanner.nextInt();
            }
        }
        GraphCode graph = new GraphCode(m);
    }
}
