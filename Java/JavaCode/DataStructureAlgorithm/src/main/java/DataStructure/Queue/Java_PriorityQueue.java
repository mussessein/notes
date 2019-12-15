package DataStructure.Queue;
import java.util.PriorityQueue;

public class Java_PriorityQueue {

    public static class People{
        int salary;
        public People(int salary){
            this.salary=salary;
        }
    }
    public static void main(String[] args) {
        PriorityQueue<People> priorityQueue = new PriorityQueue<People>(
                (p1,p2)-> p1.salary-p2.salary// 默认最小堆，最大堆：p2-p1
        );
        People p1 = new People(1000);
        People p2 = new People(2000);
        People p3 = new People(3000);
        priorityQueue.add(p1);
        priorityQueue.add(p3);
        priorityQueue.add(p2);

        while (!priorityQueue.isEmpty()){
            System.out.println(priorityQueue.poll().salary);
        }
    }

}
