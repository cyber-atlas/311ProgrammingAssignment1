package Default;

import javax.management.relation.RelationException;
import java.io.*;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Scanner;
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
    //Hashtable to check the
    private Hashtable<String, Boolean> visitedHash = new Hashtable<>();


    /**
     * Contstructs the WikiCrawler
     *
     * @param seed   - the releative address of the seed
     * @param max    - the max number of pages to be crawled
     * @param topics - Array of stings representing keywords ina topic-list
     * @param output - the filename where the web graph of discovered pages are written
     */
    WikiCrawler(String seed, int max, String[] topics, String output) {
        //Settinng the paramters to the instance variables
        this.seed = seed;
        this.max = max;
        this.topics = topics;
        this.output = output;


    }

    //TODO create a graph, in BFS fashion
    //TODO make sure that we are only crawling wiki pages
    //TODO only crawling over 200 pages

    //TODO focused crawling, We only want to visit the pages about a certain topic, only the top 200 pages


    //TODO fix this so that it is not using regexes. Worry about this after the rest of the code is implemented

    /**
     * Extract only relative addresses of wiki links (in form /wiki/XXXX)
     * Only extract links that appear after the first <p> or <P>
     * Must note extract any wiki link that contains "#" or ":"
     * Must return links in the array list in the exact order in the HTML
     *
     * @param document representing entire HTML document
     * @return list of Strings consisting of links from the document.
     */
    public ArrayList<String> extractLinks(String document) {

        //We only care about links after the first <p> so we strip everything before it
        document = stripper(document);
        //Arraylist of links
        ArrayList<String> links = new ArrayList<>();
        //Hashset to hold the links we added to ArrayList
        HashSet<String> addedLinks = new HashSet<>();
        /**First quotes are the string for the regex.
         * Escape the quotes in the href
         * Make sure it starts with wiki/
         * [^#:] matches every character except or # or : after wiki/
         */
        String regex = "\"/wiki/[^\"#:]+\"";
        //Compile the regex to check for matches
        Pattern pat = Pattern.compile(regex);
        //Matches the string we are passing in against the compiled regex
        Matcher match = pat.matcher(document);

        //while there is another match
        while (match.find()) {
            //returns the substring
//            links.add(match.group().substring(1, match.group().length() - 1));
            String tempLink =(match.group().substring(1, match.group().length() - 1));
            //If link is not in hashset, then it's not in arraylist. Add it to ArrayList
            //This check removes duplicate links
            if(!(addedLinks.contains(tempLink))){
                links.add(tempLink);
                addedLinks.add(tempLink);
            }
        }
        System.out.println(links.toString());
        return links;
    }

    /**
     * If focused, compute Relevance(T,a)
     * during exploration, instead of adding to FIFO queue add to corresponding relevance to priority queue
     * priority is relevance
     * extract elements from the queue using extract max.
     * After crawl, explored edges should be written to output file
     * Not focused, explore in BFS fashion
     *
     * @param focused returns wherthr focused or not
     */
    //TODO need to check the seed page for topics. Also need to add it to the visitedHashmap
    public void crawl(boolean focused) throws FileNotFoundException, InterruptedException {

        //TODO keep track of visited page
        //TODO keep track of the nubmer of times crawl is called.
        //TODO pop the page from the Q, then crawl
        //TODO use a while lop buther


        int iterations = 0;
        int count =0;

        PriorityQ pq = new PriorityQ();
        Fifo fifoQ = new Fifo();

        //The PrintWriter we will be later using
        PrintWriter outFile = new PrintWriter(output);
        outFile.println(max);

        //If not focused used queue and add the seed
        if(focused){
            pq.add(seed,0);
        }
        //If not focused initialize the fifo
        else{
            fifoQ.Enqueue(seed);
        }

        while (iterations < max) {

            if (iterations %  20 == 0){
                Thread.sleep(3000);
            }

            //If we are doing a focused crawl, get the page we should be on from Proirity Queue, else get from BFS Q
            //TODO fix if empty
            String pageLink;
            if (focused){
                pageLink = pq.extractMax();
            }
            else {
                if(fifoQ.getSize() == 0){return;}
                pageLink = fifoQ.Dequeue();
            }

            String currentPage = getHTML(pageLink);
//            System.out.println(currentPage);

            ArrayList<String> links = extractLinks(currentPage);

            System.out.println("links array" + links.toArray());
            for (String link : links) {
                System.out.println(link+" :links");
                System.out.println(visitedHash);
                if (visitedHash.contains(link)) {
                    //If the link has already been visited, and contains all topics, add to graph
                    if (visitedHash.get(link)) {
                        outFile.println(pageLink + " " + link);
                        System.out.println(link);
                        continue;
                    } else if (!(visitedHash.get(link))) {
                        continue;
                    }
                }


                String document = getHTML(link);
                count++;
                if (count %  20 == 0){
                    Thread.sleep(3000);
                    count =0;
                }

                int Relevance = 0;

                HashSet<String> topicsFound = new HashSet<String>();
                //Turn into an array of strings from the document splitting at spaces
                String[] stringArr = document.split(" ");
                //Loops through the array of strings (Entire document)
                for (String string : stringArr) {
                    //Checks if the string is in the topics hash set
                    for (String topic : topics) {
                        //Sets our start index to 0 the first time we use it
                        int i = 0;
                        //Checks if the topic is in the string, starting at index i)
                        while (string.indexOf(topic, i) != -1) {
                            //index of returns an index. Add the that to i, add to relevances
                            Relevance += 1;
                            //Add topic to hashset
                            topicsFound.add(string);
                            //Change i so that we start at the previous index and after the topic word
                            i = string.indexOf(topic) + topic.length();
                        }
                    }
                }

                //Check if all of the topics are present check if the hashset is the same length as the topics list
                if (topicsFound.size() != topics.length) {
                    System.out.println(link + " | "+topicsFound.size()+" | " + topics.length);
                    visitedHash.put(link, false);
                    //Continue should make it got to the next iteration of the while loop
                    continue;
                }

                System.out.println(link + " | "+topicsFound.size()+" | " + topics.length);

                //Only gets here if the all of the topics are present in the hashset
                if (focused) {
                    pq.add(link, Relevance);
                }

                if (!focused) {
                    fifoQ.Enqueue(link);
                }

                //Add the link to the visited Hashset, becuase it is in a Queue and that means has all topics
                visitedHash.put(link, true);

                //Print the curent link and the link that has everyting needed to the graph
                outFile.println(pageLink + " " + link);

            }
            iterations++;
        }

        outFile.close();


    }


    /**
     * Takes the website seed and reads in the HTML of the page
     *
     * @param seed of the page that we are looking at
     * @return string with the HTML from a song
     */
    //TODO how does one handle the HTML to string being null?
    public String getHTML(String seed) {
        //Stringbuilder that we will use to hold the web page
        StringBuilder HTML = new StringBuilder();
        //It is not time to read until we find the first occurance of <p>
        Boolean timeToRead = false;


        //Pass the URL into an input stream, use a buffered reader to deal with it
        try {
            //The full path of the page that we are visiting
            URL oururl = new URL(BASE_URL + seed);
            //Reads the HTML in as an InputStream
            InputStream in = oururl.openStream();
            //Reads the input stream
            InputStreamReader isr = new InputStreamReader(in);
            //Puts the input stream reader into thd buffered stream reader
            BufferedReader reader = new BufferedReader(isr);

            //Variable to hold the next line of the string as we read it, before we add it to HTML String
            String nextLine;
            //Gets the next line of HTML until there is no more left
            while ((nextLine = reader.readLine()) != null) {
                //Make sure nothing before the first <p> is passed into the string
                //timeToRead will be false because be we had not reached the first <p>, assuming lowercase
                if (!timeToRead && (nextLine.toLowerCase().contains("<p>"))) {
                    //Gets the index of the first <p> making sure in lowercase for simplicity
                    int x = nextLine.toLowerCase().indexOf("<p>");
                    //Adds everything after the <p> to the StringBuilder
                    HTML.append(nextLine.substring(x));
                    //Set timeToRead to true
                    timeToRead = true;
                }
                //timeToRead is only true after the first <p>, so the file not added to our String until after
                if (timeToRead) {
                    //Adds the next line of text to the String
                    HTML.append(nextLine);
                    //Adds a new line character because wee are only getting line contents
                    HTML.append("\n");
                }
            }
            //Returns the String of the String Builder
            return HTML.toString();
        } catch (IOException e) {
            e.printStackTrace();
            //returns null in case that we are not able to get the page we want
            return null;
        }
    }

    //helper method that strips anything before <p> tag from String

    /**
     * Helper method that strips anything before <p> tag from String
     *
     * @param input HTML
     * @return Everything after the first <p>
     */
    public String stripper(String input) {
        StringBuilder retString = new StringBuilder();
        String nextup;
        boolean reading = false;
        Scanner scan = new Scanner(input);

        while (scan.hasNextLine()) {
            nextup = scan.nextLine();
            //To deal with P
            if (!reading && (nextup.toLowerCase().contains("<p>"))) {
                reading = true;
                //adds the substing of everything after <p> when it is found
                retString.append(nextup.substring(nextup.toLowerCase().indexOf("<p>")));
                retString.append("\n");
            }
            //TODO probably could just break after we strip the stuff before the first <p> Return substring starting with it
            //If we are reading, add the next line of texdt
            if (reading) {
                retString.append(nextup);
                retString.append("\n");
            }
        }

        return retString.toString();
    }


    /**
     * Iterates through all of the topics in the list
     * adds them to the Hashtable with value 0
     *
     * @return Hashtable with all the topics and the number of times they were visted
     */
    private Hashtable retTopicsHashtab() {

        Hashtable topicsHash = new Hashtable();
        for (String s : topics) {
            topicsHash.put(s, 0);
        }

        return topicsHash;
    }

    /**
     * Check the given hashtable against our global list of topics
     * If the value in the hashtable is 0, that means it was not in our string, return false
     *
     * @param hash the hashtable that we are parsing through
     * @return true if all of the topics were above 0 (meaning found at least 1x)
     */
    private boolean containsAllTopics(Hashtable<String, Integer> hash) {
        //Loops through the list of topics and checks their value in the hashtable
        for (int i = 0; i < topics.length; i++) {
            if (hash.get(topics[i]) == 0) {
                return false;
            }
        }
        return true;
    }


}
