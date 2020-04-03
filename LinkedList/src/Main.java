public class Main {

    public static void main(String[] args) {
        linkedListTest();
    }

    public static void linkedListTest() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(3);
        list.add(5);
        list.add(6);
        list.add(7);
        System.out.println(list);
        list.addFirst(2);
        list.addFirst(1);
        System.out.println(list);
        list.addAfter(4, 2);
        System.out.println(list);
        System.out.println(list.size());
        System.out.println(list.get(4));
        System.out.println(list.getLast());
        System.out.println(list.remove((Integer) 8));
        System.out.println(list.remove((Integer) 2));
        System.out.println(list.remove((Integer) 1));
        System.out.println(list.remove((Integer) 7));
        System.out.println(list);
        list.remove(2);
        list.remove(0);
        System.out.println(list);
        Integer[] array = new Integer[] {11, 19, 30, 35};
        LinkedList<Integer> list2 = new LinkedList<>(array);
        System.out.println(list.merge(list2));
        System.out.println(list2.contains(7));
        System.out.println(list2.contains(11));
        System.out.println(list2.contains(19));
        System.out.println(list2.contains(35));
        System.out.println(list2.indexOf(7));
        System.out.println(list2.indexOf(11));
        System.out.println(list2.indexOf(19));
        System.out.println(list2.indexOf(35));
        System.out.println(list2.isEmpty());
        list.remove(0);
        list.remove(0);
        System.out.println(list.isEmpty());
        LinkedList<Integer> list3 = new LinkedList<>();
        System.out.println(list.equals(list3));
        list = new LinkedList<>(list2);
        System.out.println(list.equals(list2));
        list3.addFirst(5);
        System.out.println(list3);
        System.out.println(list.equals(list3));
        System.out.println(list.hashCode());
    }

}
