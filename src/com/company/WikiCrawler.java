package com.company;

import java.util.ArrayList;

public class WikiCrawler {

    //Seed is the related address of URL (within the wiki domain)
    private String seed;
    //Max is the maximum number of pages to consider
    private int max;
    //Array of strings representing keywords in a topic-list
    private String[] topics;
    //String representing the filename where web graph over discovered pages are written
    private String output;


    WikiCrawler(String seed, int max, String[] topics, String output){

    }

    //TODO create a graph, in BFS fashion
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

    private void crawl(boolean focused){
        
    }

}
