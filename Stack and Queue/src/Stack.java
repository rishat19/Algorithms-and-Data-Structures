import java.util.Arrays;
import java.util.Objects;

public class Stack<T> {

    private T[] elements;
    private int size;
    private final int CAPACITY = 10;
    private final int COEFFICIENT = 2;

    public Stack() {
        elements = (T[]) new Object[this.CAPACITY];
        size = 0;
    }

    public Stack(T[] array) throws NullPointerException {
        if (array != null) {
            size = array.length;
            elements = (T[]) new Object[size];
            System.arraycopy(array, 0, this.elements, 0, size);
        }
        else {
            throw new NullPointerException("Exception: argument points to null");
        }
    }

    public Stack(Stack<T> stack) throws NullPointerException {
        if (stack != null) {
            size = stack.size;
            elements = (T[]) new Object[size];
            System.arraycopy(stack.elements, 0, this.elements, 0, size);
        }
        else {
            throw new NullPointerException("Exception: argument points to null");
        }
    }

    public void add(T element) {
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
    }

    public T pop() {
        if (size == 0) {
            return null;
        }
        size--;
        return elements[size];
    }

    public T peek() {
        if (size == 0) {
            return null;
        }
        return elements[size - 1];
    }

    public int size() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stack<?> stack = (Stack<?>) o;
        if (size != stack.size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!elements[i].equals(stack.elements[i])) {
                return false;
            }
        }
        return true;
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
        return new String(str);
    }

}
