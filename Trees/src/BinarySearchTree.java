import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class BinarySearchTree<T extends Comparable> {

    private Node root;
    private int size;

    private class Node {
        private T data;
        private Node parent;
        private Node left;
        private Node right;
        private Node(T data, Node parent) {
            this.data = data;
            this.parent = parent;
            this.left = null;
            this.right = null;
        }
        public T getData() {
            return data;
        }
        public void setData(T data) {
            this.data = data;
        }
        public Node getParent() {
            return parent;
        }
        public void setParent(Node parent) {
            this.parent = parent;
        }
        public Node getLeft() {
            return left;
        }
        public void setLeft(Node left) {
            this.left = left;
        }
        public Node getRight() {
            return right;
        }
        public void setRight(Node right) {
            this.right = right;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equals(data, node.data) &&
                    Objects.equals(parent, node.parent) &&
                    Objects.equals(left, node.left) &&
                    Objects.equals(right, node.right);
        }
        @Override
        public int hashCode() {
            return Objects.hash(data, parent, left, right);
        }
        @Override
        public String toString() {
            return data.toString();
        }
    }

    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    public void add(T data) {
        if (data != null) {
            if (root != null) {
                add(data, root);
            } else {
                root = new Node(data, null);
                size++;
            }
        } else {
            throw new NullPointerException("The item to add points to null");
        }
    }

    private void add(T data, Node node) {
        if (data.compareTo(node.getData()) > 0) {
            if (node.getRight() == null) {
                node.setRight(new Node(data, node));
                size++;
            } else {
                add(data, node.getRight());
            }
        } else {
            if (node.getLeft() == null) {
                node.setLeft(new Node(data, node));
                size++;
            } else {
                add(data, node.getLeft());
            }
        }
    }

    public void DFS() {
        DFS(root);
    }

    private void DFS(Node node) {
        if (node!= null) {
            DFS(node.getLeft());
            System.out.print(node.data.toString() + " ");
            DFS(node.getRight());
        }
    }

    public void BFS() {
        Collection<Node> nodes = new ArrayList<>();
        nodes.add(root);
        BFS(nodes);
    }

    private void BFS(Collection<Node> nodes) {
        Collection<Node> childNodes = new ArrayList<>();
        for (Node node : nodes) {
            if (node != null) {
                System.out.print(node.getData().toString() + " ");
                if (node.getLeft() != null) {
                    childNodes.add(node.getLeft());
                }
                if (node.getRight() != null) {
                    childNodes.add(node.getRight());
                }
            }
        }
        if (childNodes.size() > 0) {
            BFS(childNodes);
        }
    }

    public int getSize() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BinarySearchTree<?> that = (BinarySearchTree<?>) o;
        return size == that.size &&
                Objects.equals(root, that.root);
    }

    @Override
    public int hashCode() {
        return Objects.hash(root, size);
    }

    @Override
    public String toString() {
        return "BinarySearchTree{" +
                "root=" + root +
                ", size=" + size +
                '}';
    }

}
