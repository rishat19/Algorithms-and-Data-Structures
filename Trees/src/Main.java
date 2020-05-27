public class Main {

    public static void main(String[] args) {
        testBST();
        testAVL();
    }

    public static void testBST() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.add(19);
        bst.add(30);
        bst.add(93);
        bst.add(9);
        bst.add(4);
        bst.add(7);
        bst.add(-3);
        bst.add(45);
        bst.add(91);
        bst.add(3);
        bst.DFS();
        System.out.println();
        bst.BFS();
        System.out.println();
    }

    public static void testAVL() {
        AVLTree<Integer> avl = new AVLTree<>();
        avl.add(19);
        avl.add(30);
        avl.add(93);
        avl.add(9);
        avl.add(4);
        avl.add(7);
        avl.add(-3);
        avl.add(45);
        avl.add(91);
        avl.add(3);
        avl.DFS();
        System.out.println();
        avl.BFS();
        System.out.println();
        System.out.println(avl.minValue());
        System.out.println(avl.maxValue());
        avl.delete(7);
        avl.delete(91);
        avl.DFS();
        System.out.println();
        avl.BFS();
        System.out.println();
    }

}
