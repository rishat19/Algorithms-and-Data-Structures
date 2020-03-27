public class Main {

    public static void main(String[] args) {
        stackTest();
        System.out.println();
        queueTest();
    }

    public static void stackTest() {
        Integer[] array = new Integer[] {4, 7, 10, 13, 16, 7, 10, 12, 11, 3, 72, 9, 19};
        Stack<Integer> stack = new Stack<>(array);
        System.out.println(stack);
        System.out.println(stack.peek());
        System.out.println(stack.size());
        stack.add(19);
        System.out.println(stack);
        System.out.println(stack.pop());
        stack.pop();
        stack.pop();
        System.out.println(stack);
        System.out.println(stack.size());
        Stack<Integer> stack1 = new Stack<>(stack);
        Stack<Integer> stack2 = new Stack<>();
        System.out.println(stack1);
        System.out.println(stack.equals(stack1));
        System.out.println(stack2);
        System.out.println(stack.equals(stack2));
    }

    public static void queueTest() {
        Integer[] array = new Integer[] {4, 7, 13, 12, 11, 3, 72, 9, 19};
        Queue<Integer> queue = new Queue<>(array);
        System.out.println(queue);
        System.out.println(queue.peek());
        System.out.println(queue.size());
        queue.pop();
        System.out.println(queue.pop());
        queue.pop();
        System.out.println(queue);
        queue.add(93);
        queue.add(2);
        System.out.println(queue);
        Queue<Integer> queue1 = new Queue<>();
        System.out.println(queue1);
        System.out.println(queue1.pop());
        System.out.println(queue1.peek());
        queue1.add(19);
        System.out.println(queue1);
        System.out.println(queue1.size());
        System.out.println(queue1.pop());
        System.out.println(queue1);
        System.out.println(queue1.size());
        Queue<Integer> queue2 = new Queue<>(queue);
        System.out.println(queue2);
        System.out.println(queue.equals(queue2));
        System.out.println(queue.equals(queue1));
    }

}
