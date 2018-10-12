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
    }
}
