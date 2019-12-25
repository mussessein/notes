package DataStructure.LinkedList;

public class Test {
    /*
    ²âÊÔ
    */
    public static void main(String[] args) {

        LinkedList<Integer> linkedList =new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.addLast(i);
            System.out.println(linkedList);
        }
        linkedList.add(2,666);
        System.out.println(linkedList);
        
        linkedList.removeFirst();
        linkedList.removeLast();
        System.out.println(linkedList);
    }
}
