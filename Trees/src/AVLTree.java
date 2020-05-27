import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class AVLTree<T extends Comparable> {

    private Node root;
    private int size;

    private class Node {
        private T data;
        private int height;
        private Node left;
        private Node right;
        private Node(T data) {
            this.data = data;
            this.height = 1;
            this.left = null;
            this.right = null;
        }
        public T getData() {
            return data;
        }
        public void setData(T data) {
            this.data = data;
        }
        public int getHeight() {
            return height;
        }
        public void setHeight(int height) {
            this.height = height;
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
            return height == node.height &&
                    Objects.equals(data, node.data) &&
                    Objects.equals(left, node.left) &&
                    Objects.equals(right, node.right);
        }
        @Override
        public int hashCode() {
            return Objects.hash(data, height, left, right);
        }
        @Override
        public String toString() {
            return data.toString();
        }
    }

    public AVLTree() {
        root = null;
        size = 0;
    }

    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        return node.getHeight();
    }

    private Node rightRotate(Node node) {
        Node l = node.getLeft();
        Node lr = l.getRight();
        l.setRight(node);
        node.setLeft(lr);
        node.setHeight(Integer.max(height(node.getLeft()), height(node.getRight())) + 1);
        l.setHeight(Integer.max(height(l.getLeft()), height(l.getRight())) + 1);
        return l;
    }

    private Node leftRotate(Node node) {
        Node r = node.getRight();
        Node rl = r.getLeft();
        r.setLeft(node);
        node.setRight(rl);
        node.setHeight(Integer.max(height(node.getLeft()), height(node.getRight())) + 1);
        r.setHeight(Integer.max(height(r.getLeft()), height(r.getRight())) + 1);
        return r;
    }

    private int getBalance(Node node) {
        if (node == null) {
            return 0;
        }
        return height(node.getLeft()) - height(node.getRight());
    }

    public void add(T data) {
        root = add(root, data);
    }

    private Node add(Node node, T data) {
        if (node == null) {
            size++;
            return (new Node(data));
        }
        if (data.compareTo(node.getData()) <= 0) {
            node.setLeft(add(node.getLeft(), data));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRight(add(node.getRight(), data));
        } else {
            return node;
        }
        node.setHeight(Integer.max(height(node.getLeft()), height(node.getRight())) + 1);
        int balance = getBalance(node);
        if (balance > 1 && data.compareTo(node.getLeft().getData()) <= 0)  {
            return rightRotate(node);
        }
        if (balance < -1 && data.compareTo(node.getRight().getData()) > 0) {
            return leftRotate(node);
        }
        if (balance > 1 && data.compareTo(node.getLeft().getData()) > 0) {
            node.setLeft(leftRotate(node.getLeft()));
            return rightRotate(node);
        }
        if (balance < -1 && data.compareTo(node.getRight().getData()) <= 0) {
            node.setRight(rightRotate(node.getRight()));
            return leftRotate(node);
        }
        return node;
    }

    public T minValue() {
        return minValueNode(root).getData();
    }

    private Node minValueNode(Node node) {
        Node current = node;
        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current;
    }

    public T maxValue() {
        return maxValueNode(root).getData();
    }

    private Node maxValueNode(Node node) {
        Node current = node;
        while (current.getRight() != null) {
            current = current.getRight();
        }
        return current;
    }

    public void delete(T data) {
        root = delete(root, data);
    }

    private Node delete(Node node, T data) {
        if (node == null) {
            return null;
        }
        if (data.compareTo(node.getData()) < 0) {
            node.setLeft(delete(node.getLeft(), data));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRight(delete(node.getRight(), data));
        } else {
            if ((node.getLeft() == null) || (node.getRight() == null)) {
                Node temp;
                if (node.getLeft() == null) {
                    temp = node.getRight();
                } else {
                    temp = node.getLeft();
                }
                if (temp == null) {
                    temp = node;
                    node = null;
                } else {
                    node = temp;
                }
            } else {
                Node temp = minValueNode(node.getRight());
                node.setData(temp.getData());
                node.setRight(delete(node.getRight(), temp.getData()));
            }
        }
        if (node == null) {
            return null;
        }
        node.setHeight(Integer.max(height(node.getLeft()), height(node.getRight())) + 1);
        int balance = getBalance(node);
        if (balance > 1 && getBalance(node.getLeft()) >= 0) {
            return rightRotate(node);
        }
        if (balance > 1 && getBalance(node.getLeft()) < 0) {
            node.setLeft(leftRotate(node.getLeft()));
            return rightRotate(node);
        }
        if (balance < -1 && getBalance(node.getRight()) <= 0) {
            return leftRotate(node);
        }
        if (balance < -1 && getBalance(node.getRight()) > 0) {
            node.setRight(rightRotate(node.getRight()));
            return leftRotate(node);
        }
        return node;
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
        AVLTree<?> avlTree = (AVLTree<?>) o;
        return size == avlTree.size &&
                Objects.equals(root, avlTree.root);
    }

    @Override
    public int hashCode() {
        return Objects.hash(root, size);
    }

    @Override
    public String toString() {
        return "AVLTree{" +
                "root=" + root +
                ", size=" + size +
                '}';
    }

}
