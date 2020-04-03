import java.util.Arrays;
import java.util.Objects;

public class Set<T> {

    private T[] elements;
    private int size;
    private final int CAPACITY = 10;
    private final int COEFFICIENT = 2;

    public Set() {
        elements = (T[]) new Object[CAPACITY];
        size = 0;
    }

    public Set(T[] array) throws NullPointerException {
        if (array != null) {
            size = 0;
            elements = (T[]) new Object[array.length];
            for (T t : array) {
                this.add(t);
            }
        }
        else {
            throw new NullPointerException("Exception: argument points to null");
        }
    }

    public Set(Set<T> set) throws NullPointerException {
        if (set != null) {
            this.size = set.size;
            this.elements = (T[]) new Object[size];
            System.arraycopy(set.elements, 0, this.elements, 0, size);
        }
        else {
            throw new NullPointerException("Exception: argument points to null");
        }
    }

    public boolean add(T element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(elements[i])) {
                return false;
            }
        }
        size++;
        if (size > elements.length) {
            T[] newElements = (T[]) new Object[elements.length * COEFFICIENT];
            System.arraycopy(elements, 0, newElements, 0, size - 1);
            newElements[size - 1] = element;
            elements = newElements;
        }
        else {
            elements[size - 1] = element;
        }
        return true;
    }

    public boolean delete(T element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(elements[i])) {
                if (i != size - 1) {
                    elements[i] = elements[size - 1];
                }
                size--;
                return true;
            }
        }
        return false;
    }

    public boolean has(T element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(elements[i])) {
                return true;
            }
        }
        return false;
    }

    public Set<T> merge(Set<T> set) {
        Set<T> newSet = new Set<>(this);
        for (int i = 0; i < set.size(); i++) {
            newSet.add(set.elements[i]);
        }
        return newSet;
    }

    public int size() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Set<?> set = (Set<?>) o;
        if (this.size == set.size) {
            boolean[] flags = new boolean[this.size];
            for (int i = 0; i < this.size; i++) {
                for (int j = 0; j < this.size; j++) {
                    if (elements[i].equals(set.elements[j]) && !flags[j]) {
                        flags[j] = true;
                        break;
                    }
                }
            }
            for (int i = 0; i < this.size; i++) {
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
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(elements);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("{");
        if (size == 0) {
            str.append("}");
        }
        else {
            for (int i = 0; i < size - 1; i++) {
                str.append(elements[i].toString()).append(", ");
            }
            str.append(elements[size - 1].toString()).append("}");
        }
        return str.toString();
    }

}
