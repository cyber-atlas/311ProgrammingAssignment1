package Default;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        /**First quotes are the string for the regex.
         * Escape the quotes in the href
         * Make sure it starts with wiki/
         * [^#:] matches every character except or # or : after wiki/
         */
        String regex = "\"wiki/[^#:]\"";

        //Compile the regex to check for matches
        Pattern pat = Pattern.compile(regex);
        //Matches the string we are passing in against the compiled regex
        Matcher match = pat.matcher(document);

        while (match.find()){


            //TODO comntinue here
        }



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


    /**
     * Takes the website seed and reads in the HTML of the page
     * @param seed of the page that we are looking at
     * @return string with the HTML from a song
     */
    private String getHTML(String seed){
        //Stringbuilder that we will use to hold the web page
        StringBuilder HTML = new StringBuilder();


        //Pass the URL into an input stream, use a buffered reader to deal with it
        try {
            //The full path of the page that we are visiting
            URL oururl = new URL(BASE_URL + seed );
            //Reads the HTML in as an InputStream
            InputStream in = oururl.openStream();
            //Reads the input stream
            InputStreamReader isr = new InputStreamReader(in);
            //Puts the input stream reader into thd buffered stream reader
            BufferedReader reader =  new BufferedReader(isr);

            //Variable to hold the next line of the string as we read it, before we add it to HTML String
            String nextline;
            //Gets the next line of HTML until there is no more left
            while((nextline = reader.readLine())!= null){
                //Adds the next line of text to the String
                HTML.append(nextline);
                //Adds a new line character because wee are only getting line contents
                HTML.append("\n");
            }

            return HTML.toString();
        } catch (IOException e) {
            e.printStackTrace();
            //returns null in case that we are not able to get the page we want
            return null;
        }
    }

}
