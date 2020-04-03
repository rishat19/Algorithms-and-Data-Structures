import java.util.Arrays;
import java.util.Objects;

public class MultiSet<T> {

    private T[] elements;
    private int[] counters;
    private int numberOfDifferentElements;
    private final int CAPACITY = 10;
    private final int COEFFICIENT = 2;

    public MultiSet() {
        elements = (T[]) new Object[CAPACITY];
        counters = new int[CAPACITY];
        numberOfDifferentElements = 0;
    }

    public MultiSet(T[] array) throws NullPointerException {
        if (array != null) {
            numberOfDifferentElements = 0;
            elements = (T[]) new Object[array.length];
            counters = new int[array.length];
            for (T t : array) {
                this.add(t);
            }
        }
        else {
            throw new NullPointerException("Exception: argument points to null");
        }
    }

    public MultiSet(MultiSet<T> multiSet) throws NullPointerException {
        if (multiSet != null) {
            this.numberOfDifferentElements = multiSet.numberOfDifferentElements;
            this.elements = (T[]) new Object[numberOfDifferentElements];
            this.counters = new int[numberOfDifferentElements];
            System.arraycopy(multiSet.elements, 0, this.elements, 0, numberOfDifferentElements);
            System.arraycopy(multiSet.counters, 0, this.counters, 0, numberOfDifferentElements);
        }
        else {
            throw new NullPointerException("Exception: argument points to null");
        }
    }

    public boolean add(T element) {
        for (int i = 0; i < numberOfDifferentElements; i++) {
            if (elements[i].equals(element)) {
                counters[i]++;
                return true;
            }
        }
        numberOfDifferentElements++;
        if (numberOfDifferentElements > elements.length) {
            T[] newElements = (T[]) new Object[elements.length * COEFFICIENT];
            int[] newCounters = new int[counters.length * COEFFICIENT];
            System.arraycopy(elements, 0, newElements, 0, numberOfDifferentElements - 1);
            System.arraycopy(counters, 0, newCounters, 0, numberOfDifferentElements - 1);
            newElements[numberOfDifferentElements - 1] = element;
            newCounters[numberOfDifferentElements - 1] = 1;
            elements = newElements;
            counters = newCounters;
        }
        else {
            elements[numberOfDifferentElements - 1] = element;
            counters[numberOfDifferentElements - 1] = 1;
        }
        return true;
    }

    public boolean delete(T element) {
        for (int i = 0; i < numberOfDifferentElements; i++) {
            if (element.equals(elements[i])) {
                counters[i]--;
                if (counters[i] == 0) {
                    if (i != numberOfDifferentElements - 1) {
                        elements[i] = elements[numberOfDifferentElements - 1];
                        counters[i] = counters[numberOfDifferentElements - 1];
                    }
                    numberOfDifferentElements--;
                }
                return true;
            }
        }
        return false;
    }

    public boolean has(T element) {
        for (int i = 0; i < numberOfDifferentElements; i++) {
            if (element.equals(elements[i])) {
                return true;
            }
        }
        return false;
    }

    public MultiSet<T> merge(MultiSet<T> multiSet) {
        MultiSet<T> newMultiSet = new MultiSet<>(this);
        for (int i = 0; i < multiSet.size(); i++) {
            for (int j = 0; j < multiSet.counters[i]; j++) {
                newMultiSet.add(multiSet.elements[i]);
            }
        }
        return newMultiSet;
    }

    public int size() {
        int size = 0;
        for (int i = 0; i < numberOfDifferentElements; i++) {
            size += counters[i];
        }
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MultiSet<?> multiSet = (MultiSet<?>) o;
        if (this.numberOfDifferentElements == multiSet.numberOfDifferentElements) {
            boolean[] flags = new boolean[this.numberOfDifferentElements];
            for (int i = 0; i < this.numberOfDifferentElements; i++) {
                for (int j = 0; j < this.numberOfDifferentElements; j++) {
                    if (elements[i].equals(multiSet.elements[j]) && counters[i] == multiSet.counters[j] && !flags[j]) {
                        flags[j] = true;
                        break;
                    }
                }
            }
            for (int i = 0; i < this.numberOfDifferentElements; i++) {
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
        int result = Objects.hash(numberOfDifferentElements);
        result = 31 * result + Arrays.hashCode(elements);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("{");
        if (numberOfDifferentElements == 0) {
            str.append("}");
        }
        else {
            for (int i = 0; i < numberOfDifferentElements - 1; i++) {
                str.append(elements[i].toString()).append(" (").append(counters[i]).append(")").append(", ");
            }
            str.append(elements[numberOfDifferentElements - 1].toString()).append(" (")
                    .append(counters[numberOfDifferentElements - 1]).append(")").append("}");
        }
        return str.toString();
    }

}
