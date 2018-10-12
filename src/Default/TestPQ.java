package Default;

public class TestPQ {

    public static void main(String[] args) {
        // write your code here
        PriorityQ t = new PriorityQ();
        t.add("John Young", 16);
        t.add("Alex Stevenson", 50);
        t.add("hi", 83);
        t.add("telephone", 99);
        t.add("122", 122);
        t.add("This is max", 5034);
        t.add("hi", 44);
        t.add("99", 99);
        //t.extractMax();
       /*for(int i = 0; i < t.priorityArray().length; i++){
           System.out.println(t.priorityArray()[i]);
       }*/
        for(int i = 0; i < t.priorityArray().length; i++){
            System.out.println(t.getKey(i));
        }

        System.out.println();
        t.remove(0);
       // t.decrementPriority(0,5033);
        System.out.println("Max is: " + t.extractMax());
        System.out.println("Max is: " + t.extractMax());
        for(int i = 0; i < t.priorityArray().length; i++){
            System.out.println(t.getKey(i));
        }
    }
}
