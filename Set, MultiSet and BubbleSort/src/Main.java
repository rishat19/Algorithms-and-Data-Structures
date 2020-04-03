import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        setTest();
        System.out.println();
        multiSetTest();
        System.out.println();
        bubbleSortTest();
    }

    public static void setTest() {
        Integer[] array = new Integer[] {4, 7, 10, 13, 10, 7, 10, 9, 11, 3, 72, 9, 19};
        Set<Integer> set = new Set<>(array);
        System.out.println(set);
        System.out.println(set.size());
        System.out.println(set.has(11));
        System.out.println(set.has(1));
        System.out.println(set.add(93));
        System.out.println(set.add(94));
        System.out.println(set.add(11));
        System.out.println(set);
        System.out.println(set.size());
        System.out.println(set.delete(94));
        System.out.println(set.delete(10));
        System.out.println(set.delete(1));
        System.out.println(set);
        System.out.println(set.size());
        Integer[] array2 = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Set<Integer> set2 = new Set<>(array2);
        System.out.println(set.merge(set2));
        Set<Integer> set3 = new Set<>(set);
        System.out.println(set3);
        System.out.println(set3.size());
        System.out.println(new Set<>());
        System.out.println(set.equals(set2));
        System.out.println(set.equals(set3));
        Integer[] array3 = new Integer[] {3, 19, 7, 93, 72, 4, 9, 11, 13};
        System.out.println(set.equals(new Set<>(array3)));
    }

    public static void multiSetTest() {
        Integer[] array = new Integer[] {4, 7, 10, 13, 10, 7, 10, 9, 11, 3, 72, 9, 19};
        MultiSet<Integer> multiSet = new MultiSet<>(array);
        System.out.println(multiSet);
        System.out.println(multiSet.size());
        System.out.println(multiSet.has(11));
        System.out.println(multiSet.has(1));
        System.out.println(multiSet.add(93));
        System.out.println(multiSet.add(94));
        System.out.println(multiSet.add(11));
        System.out.println(multiSet);
        System.out.println(multiSet.size());
        System.out.println(multiSet.delete(94));
        System.out.println(multiSet.delete(10));
        System.out.println(multiSet.delete(13));
        System.out.println(multiSet.delete(1));
        System.out.println(multiSet);
        System.out.println(multiSet.size());
        Integer[] array2 = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
        MultiSet<Integer> multiSet2 = new MultiSet<>(array2);
        System.out.println(multiSet.merge(multiSet2));
        MultiSet<Integer> multiSet3 = new MultiSet<>(multiSet);
        System.out.println(multiSet3);
        System.out.println(multiSet3.size());
        System.out.println(new MultiSet<>());
        System.out.println(multiSet.equals(multiSet2));
        System.out.println(multiSet.equals(multiSet3));
        Integer[] array3 = new Integer[] {10, 3, 9, 11, 19, 7, 10, 93, 72, 4, 9, 7, 11};
        System.out.println(multiSet.equals(new MultiSet<>(array3)));
    }

    public static void bubbleSortTest() {
        Integer[] array1 = new Integer[] {4, 7, 10, 13, 10, 7, 10, 9, 11, 3, 72, 9, 19};
        String[] array2 = new String[] {"ra", "Ru", "t", "abc", "cde", "CD", "FI", "x", "WWW", "sin", "Cos"};
        BubbleSort.sort(array1);
        System.out.println(Arrays.toString(array1));
        BubbleSort.sort(array2, String::compareTo);
        System.out.println(Arrays.toString(array2));
    }

}
