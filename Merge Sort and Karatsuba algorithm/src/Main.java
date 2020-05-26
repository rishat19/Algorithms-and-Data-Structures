import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        testMergeSortRecursive();
        testMergeSortIterative();
        testKaratsubaMultiplication();
    }

    public static void testMergeSortRecursive() {
        Integer[] array = new Integer[] {35, 19, 93, 0, 1, 9, 42, 30, 4, 5};
        MergeSort.mergeSortRecursive(array, 2, array.length - 2);
        System.out.println(Arrays.toString(array));
        MergeSort.mergeSortRecursive(array, 0, array.length);
        System.out.println(Arrays.toString(array));
    }

    public static void testMergeSortIterative() {
        Integer[] array = new Integer[] {35, 19, 93, 0, 1, 9, 42, 30, 4, 5};
        MergeSort.mergeSortIterative(array, 2, array.length - 2);
        System.out.println(Arrays.toString(array));
        MergeSort.mergeSortIterative(array, 0, array.length);
        System.out.println(Arrays.toString(array));
    }

    public static void testKaratsubaMultiplication() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int a = random.nextInt(10000);
            int b = random.nextInt(10000);
            System.out.println(a * b + " " + KaratsubaMultiplication.multiply(a, b));
        }
    }

}
