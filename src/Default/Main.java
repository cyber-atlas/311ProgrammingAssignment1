package Default;


public class Main {

    public static void main(String[] args) {
	// write your code here
        PriorityQ t = new PriorityQ();
        t.add("John Young", 16);
        t.add("Alex Stevenson", 50);
        t.add("hi", 83);
        t.add("telephone", 99);
        t.add("John Young", 122);
        t.add("This is max", 5034);
        t.add("hi", 44);
        t.add("Removed", 99);
        //t.extractMax();
       /*for(int i = 0; i < t.priorityArray().length; i++){
           System.out.println(t.priorityArray()[i]);
       }*/
        for(int i = 1; i < t.A.size() + 1; i++){
            System.out.println(t.getKey(i));
        }

        System.out.println();
        t.remove(2);
        t.decrementPriority(1,5033);
        for(int i = 1; i < t.A.size() + 1; i++){
            System.out.println(t.getValue(i));
        }
    }
}
