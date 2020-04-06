import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] m = new int[scanner.nextInt()][scanner.nextInt()];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                m[i][j] = scanner.nextInt();
            }
        }
        //constructorTimeTest(m);
        //getIncidenceMatrixTimeTest(m);
        //insertTimeTest(m);
        //deleteTimeTest(m);
        //getEdgesWithNodeTimeTest(m);
        //modifyTimeTest(m);
        //outdegreeShowTimeTest(m);
        //printIncidenceMatrixTimeTest(m);
        //getTimeTest(m);
        //equalsTimeTest(m);
        toStringTimeTest(m);
    }

    public static void constructorTimeTest(int[][] m) {
        GraphCode graph;
        for (int i = 0; i < 7; i++) {
            long time1 = System.nanoTime();
            graph = new GraphCode(m);
            long time2 = System.nanoTime();
            System.out.print(time2 - time1 + " ");
        }
    }

    public static void getIncidenceMatrixTimeTest(int[][] m) {
        GraphCode graph = new GraphCode(m);
        for (int i = 0; i < 7; i++) {
            long time1 = System.nanoTime();
            graph.getIncidenceMatrix();
            long time2 = System.nanoTime();
            System.out.print(time2 - time1 + " ");
        }
    }

    public static void insertTimeTest(int[][] m) {
        Scanner scanner = new Scanner(System.in);
        GraphCode graph = new GraphCode(m);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        for (int i = 0; i < 7; i++) {
            long time1 = System.nanoTime();
            graph.insert(a, b);
            long time2 = System.nanoTime();
            System.out.print(time2 - time1 + " ");
        }
    }

    public static void deleteTimeTest(int[][] m) {
        Scanner scanner = new Scanner(System.in);
        GraphCode graph = new GraphCode(m);
        for (int i = 0; i < 7; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            long time1 = System.nanoTime();
            graph.delete(a, b);
            long time2 = System.nanoTime();
            System.out.print(time2 - time1 + " ");
        }
    }

    public static void getEdgesWithNodeTimeTest(int[][] m) {
        GraphCode graph = new GraphCode(m);
        for (int i = 0; i < 7; i++) {
            long time1 = System.nanoTime();
            graph.getEdgesWithNode(2);
            long time2 = System.nanoTime();
            System.out.print(time2 - time1 + " ");
        }

    }

    public static void modifyTimeTest(int[][] m) {
        GraphCode graph = new GraphCode(m);
        for (int i = 0; i < 7; i++) {
            long time1 = System.nanoTime();
            graph.modify(2);
            long time2 = System.nanoTime();
            System.out.print(time2 - time1 + " ");
            graph = new GraphCode(m);
        }
    }

    public static void outdegreeShowTimeTest(int[][] m) {
        GraphCode graph = new GraphCode(m);
        for (int i = 0; i < 7; i++) {
            long time1 = System.nanoTime();
            graph.outdegreeShow(1);
            long time2 = System.nanoTime();
            System.out.print(time2 - time1 + " ");
        }
    }

    public static void printIncidenceMatrixTimeTest(int[][] m) {
        GraphCode graph = new GraphCode(m);
        for (int i = 0; i < 7; i++) {
            long time1 = System.nanoTime();
            graph.printIncidenceMatrix();
            long time2 = System.nanoTime();
            System.out.println(time2 - time1);
        }
    }

    public static void getTimeTest(int[][] m) {
        GraphCode graph = new GraphCode(m);
        for (int i = 0; i < 7; i++) {
            long time1 = System.nanoTime();
            graph.get();
            long time2 = System.nanoTime();
            System.out.print(time2 - time1 + " ");
        }
    }

    public static void equalsTimeTest(int[][] m) {
        GraphCode graph1 = new GraphCode(m);
        GraphCode graph2 = new GraphCode(m);
        for (int i = 0; i < 7; i++) {
            long time1 = System.nanoTime();
            graph1.equals(graph2);
            long time2 = System.nanoTime();
            System.out.print(time2 - time1 + " ");
        }
    }

    public static void toStringTimeTest(int[][] m) {
        GraphCode graph = new GraphCode(m);
        for (int i = 0; i < 8; i++) {
            long time1 = System.nanoTime();
            graph.toString();
            long time2 = System.nanoTime();
            System.out.print(time2 - time1 + " ");
        }
    }

}
