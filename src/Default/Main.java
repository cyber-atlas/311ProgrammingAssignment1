package Default;


import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {

    public static void must(boolean condition) {
        if (!condition) throw new RuntimeException("broke");
    }

    /*public static void testExtractLinks() {
        WikiCrawler w = new WikiCrawler("/wiki", 0, new String[]{}, "");
        ArrayList<String> links = w.extractLinks("<a href='/wiki/Luigi'></a><p><a href='/wiki/Mario'></a></p>");
        must(links.size() == 1);
        must(links.get(0).equals("/wiki/Mario"));
    }*/

    public static void testGetHtml() throws FileNotFoundException, InterruptedException {
        String s = "/wiki/bagpipe";
        //String[] arr = {"bob" , "ross"};
        String[] arr = {};
        WikiCrawler w = new WikiCrawler(s, 6, arr, "output.txt");
        String bob = w.getHTML(s);
//        System.out.println(w.getHTML(s));
//        System.out.println(w.stripper(w.getHTML(s)));
        //System.out.println(w.extractLinks(bob));
        ArrayList<String> extract = w.extractLinks(bob);

        w.crawl(true);
    }

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        //testExtractLinks();
        testGetHtml();
    }
}
