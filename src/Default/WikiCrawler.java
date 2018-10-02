package Default;

import java.util.ArrayList;

public class WikiCrawler {

    /**
     * The base url of the site we are using
     */
    public static final String BASE_URL = "https://en.wikipedia.org";

    //Seed is the related address of URL (within the wiki domain)
    private String seed;
    //Max is the maximum number of pages to consider
    private int max;
    //Array of strings representing keywords in a topic-list
    private String[] topics;
    //String representing the filename where web graph over discovered pages are written
    private String output;


    /**
     * Contstructs the WikiCrawler
     * @param seed - the releative address of the seed
     * @param max - the max number of pages to be crawled
     * @param topics - Array of stings representing keywords ina topic-list
     * @param output - the filename where the web graph of discovered pages are written
     */
    WikiCrawler(String seed, int max, String[] topics, String output){
        //Settinng the paramters to the instance variables
        this.seed = seed;
        this.max = max;
        this.topics = topics;
        this.output = output;

    }

    //TODO reate a graph, in BFS fashion
    //TODO make sure that we are only crawlng wiki pages
    //TODO only crawling over 200 pages

    //TODO focused crawling, We only want to visit the pages about a certain topic, only the top 200 pages


    /**
     *  Extract only relative addresses of wiki links (in form /wiki/XXXX)
     * Only extract links that appear after the first <p> or <P>
     * Must note extract any wiki link that contains "#" or ":"
     * Must return links in the array list in the exact order in the HTML
     * @param document representing entire HTML document
     * @return list of Strings consisting of links from the document.
     */
    private ArrayList<String> extractLinks(String document){

        return null;
    }

    /**
     * If focused, compute Relevance(T,a)
     *  during exploration, instead of adding to FIFO queue add to corresponding relevance to priority queue
     *      priority is relevance
     *  extract elements from the queue using extract max.
     *  After crawl, explored edges should be written to output file
     * Not focused, explore in BFS fashion
     * @param focused returns wherthr focused or not
     */
    private void crawl(boolean focused){
        
    }

}
