import java.util.Arrays;
import java.util.Objects;

public class LinkedList<T> {

    private Node head;
    private int size;

    private class Node {
        private T data;
        private Node next;
        private Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public LinkedList() {
        head = null;
        size = 0;
    }

    public LinkedList(T[] array) throws NullPointerException {
        this();
        if (array != null) {
            for (int i = array.length - 1; i >= 0; i--) {
                this.addFirst(array[i]);
            }
        }
        else {
            throw new NullPointerException("Exception: argument points to null");
        }
    }

    public LinkedList(LinkedList<T> list) throws NullPointerException {
        this();
        if (list != null) {
            if (list.head != null) {
                head = new Node(list.head.data);
                size++;
                Node node = head;
                Node listNode = list.head;
                while (listNode.next != null) {
                    node.next = new Node(listNode.next.data);
                    node = node.next;
                    listNode = listNode.next;
                    size++;
                }
            }
        }
        else {
            throw new NullPointerException("Exception: argument points to null");
        }
    }

    public boolean add(T element) {
        if (head == null) {
            head = new Node(element);
        }
        else {
            Node node;
            node = head;
            while (node.next != null) {
                node = node.next;
            }
            node.next = new Node(element);
        }
        size++;
        return true;
    }

    public boolean addFirst(T element) {
        Node node = new Node(element);
        node.next = head;
        head = node;
        size++;
        return true;
    }

    public boolean addAfter(T element, int index) throws IndexOutOfBoundsException {
        if (index >= 0 && index < size) {
            Node newNode = new Node(element);
            Node node;
            node = head;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            newNode.next = node.next;
            node.next = newNode;
            size++;
            return true;
        }
        else {
            throw new IndexOutOfBoundsException("Invalid index: list index out of bounds");
        }
    }

    public int size() {
        return size;
    }

    public T get(int index) throws IndexOutOfBoundsException {
        if (index >= 0 && index < size) {
            Node node;
            node = head;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node.data;
        }
        else {
            throw new IndexOutOfBoundsException("Invalid index: list index out of bounds");
        }
    }

    public T getLast() {
        return this.get(size - 1);
    }

    public boolean remove(T element) {
        Node node = head;
        if (node == null) {
            return false;
        }
        if (node.data.equals(element)) {
            head = node.next;
            size--;
            return true;
        }
        while (node.next != null) {
            if (node.next.data.equals(element)) {
                node.next = node.next.next;
                size--;
                return true;
            }
            node = node.next;
        }
        return false;
    }

    public boolean remove(int index) throws IndexOutOfBoundsException {
        if (index >= 0 && index < size) {
            Node node;
            node = head;
            if (index == 0) {
                head = node.next;
                size--;
                return true;
            }
            for (int i = 0; i < index - 1; i++) {
                node = node.next;
            }
            node.next = node.next.next;
            size--;
            return true;
        }
        else {
            throw new IndexOutOfBoundsException("Invalid index: list index out of bounds");
        }
    }

    public LinkedList<T> merge(LinkedList<T> list) {
        if (list == null || list.size() == 0) {
            return new LinkedList<>(this);
        }
        else {
            LinkedList<T> newList = new LinkedList<>();
            if (head != null) {
                newList.head = new Node(head.data);
                newList.size++;
                Node newListNode = newList.head;
                Node node = head;
                while (node.next != null) {
                    newListNode.next = new Node(node.next.data);
                    newListNode = newListNode.next;
                    node = node.next;
                    newList.size++;
                }
                newListNode.next = new Node(list.head.data);
                newListNode = newListNode.next;
                newList.size++;
                node = list.head;
                while (node.next != null) {
                    newListNode.next = new Node(node.next.data);
                    newListNode = newListNode.next;
                    node = node.next;
                    newList.size++;
                }
                return newList;
            }
            return new LinkedList<>(list);
        }

    }

    public boolean contains(T element) {
        Node node = head;
        while (node.next != null) {
            if (node.data.equals(element)) {
                return true;
            }
            node = node.next;
        }
        return node.data.equals(element);
    }

    public int indexOf(T element) {
        Node node = head;
        int index = 0;
        while (node.next != null) {
            if (node.data.equals(element)) {
                return index;
            }
            node = node.next;
            index++;
        }
        if (node.data.equals(element)) {
            return index;
        }
        return -1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkedList<?> that = (LinkedList<?>) o;
        if (size == that.size) {
            Node node = head;
            Node thatNode = (Node) that.head;
            for (int i = 0; i < size; i++) {
                if (!node.data.equals(thatNode.data)) {
                    return false;
                }
                node = node.next;
                thatNode = thatNode.next;
            }
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(head, size);
        if (size != 0) {
            T[] elements = (T[]) new Object[size];
            Node node = head;
            for (int i = 0; i < size; i++) {
                elements[i] = node.data;
                node = node.next;
            }
            result = 31 * result + Arrays.hashCode(elements);
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("{");
        if (size == 0) {
            str.append("}");
        }
        else {
            Node node = head;
            for (int i = 0; i < size - 1; i++) {
                str.append(node.data.toString()).append(", ");
                node = node.next;
            }
            str.append(node.data.toString()).append("}");
        }
        return str.toString();
    }

}
