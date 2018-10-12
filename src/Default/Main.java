package Default;


import java.util.ArrayList;

public class Main {

    public static void must(boolean condition) {
        if (!condition) throw new RuntimeException("broke");
    }

    public static void testExtractLinks() {
        WikiCrawler w = new WikiCrawler("", 0, new String[]{}, "");
        ArrayList<String> links = w.extractLinks("<a href='/wiki/Luigi'></a><p><a href='/wiki/Mario'></a></p>");
        must(links.size() == 1);
        must(links.get(0).equals("/wiki/Mario"));
    }

    public static void main(String[] args) {
        testExtractLinks();

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
        for(int i = 0; i < t.priorityArray().length; i++){
            System.out.println(t.getKey(i));
        }

        System.out.println();
        t.remove(5);
        t.decrementPriority(0,5033);
        for(int i = 0; i < t.priorityArray().length; i++){
            System.out.println(t.getKey(i));
        }
    }
}
