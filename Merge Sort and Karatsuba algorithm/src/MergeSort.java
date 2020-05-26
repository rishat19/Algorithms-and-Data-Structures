public class MergeSort<T> {

    public static <T> void mergeSortRecursive(Comparable<T>[] data, int from, int to) {
        if (data == null) {
            throw new NullPointerException("Array points to null");
        }
        else if (from < 0 && to > data.length) {
            throw new IllegalArgumentException("Incorrect array bounds");
        }
        else {
            if (from + 1 >= to) {
                return;
            }
            int mid = (from + to) / 2;
            mergeSortRecursive(data, from, mid);
            mergeSortRecursive(data, mid, to);
            merge(data, from, mid, to);
        }
    }

    public static <T> void mergeSortIterative(Comparable<T>[] data, int from, int to) {
        if (data == null) {
            throw new NullPointerException("Array points to null");
        }
        else if (from < 0 && to > data.length) {
            throw new IllegalArgumentException("Incorrect array bounds");
        }
        else {
            for (int i = 1; i < to - from; i *= 2) {
                for (int j = from; j < to - i; j += (2 * i)) {
                    merge(data, j, j + i, Integer.min(j + 2 * i, to));
                }
            }
        }
    }

    private static <T> void merge(Comparable<T>[] data, int from, int mid, int to) {
        int i = 0;
        int j = 0;
        T[] array = (T[]) new Object[to - from];
        while (from + i < mid && mid + j < to) {
            if (data[from + i].compareTo((T) data[mid + j]) < 0) {
                array[i + j] = (T) data[from + i];
                i++;
            }
            else {
                array[i + j] = (T) data[mid + j];
                j++;
            }
        }
        while (from + i < mid) {
            array[i + j] = (T) data[from + i];
            i++;
        }
        while (mid + j < to) {
            array[i + j] = (T) data[mid + j];
            j++;
        }
        System.arraycopy(array, 0, data, from, array.length);
    }

}
