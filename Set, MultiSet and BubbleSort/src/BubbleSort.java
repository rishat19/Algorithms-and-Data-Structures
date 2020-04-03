import java.util.Comparator;

public class BubbleSort<T> {

    public static <T> void sort (Comparable<T>[] data) {
        int i = 0;
        boolean t = true;
        while (t) {
            t = false;
            for (int j = 0; j < data.length - i - 1; j++) {
                if (data[j].compareTo((T) data[j + 1]) > 0) {
                    T e;
                    e = (T) data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = (Comparable<T>) e;
                    t = true;
                }
            }
            i = i + 1;
        }
    }

    public static <T> void sort (T[] data, Comparator<? super T> c) {
        int i = 0;
        boolean t = true;
        while (t) {
            t = false;
            for (int j = 0; j < data.length - i - 1; j++) {
                if (c.compare(data[j], data[j + 1]) > 0) {
                    T e;
                    e = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = e;
                    t = true;
                }
            }
            i = i + 1;
        }
    }

}
