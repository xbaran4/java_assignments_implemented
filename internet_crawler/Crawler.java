package cz.muni.fi.pb162.hw02.impl;

import cz.muni.fi.pb162.hw02.SimpleHttpClient;
import cz.muni.fi.pb162.hw02.crawler.SmartCrawler;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * This class represents internet URL crawler.
 *
 * @author Pavol Baran
 */
public class Crawler implements SmartCrawler {

    /**
     * Scans a given URL and parses out all links from the HTML document.
     *
     * @param url URL to be crawled.
     * @return a list of URLs contained by given URL.
     */
    @Override
    public List<String> crawl(String url){
        SimpleHttpClient client = new SimpleHttpClient();
        List<String> links = new LinkedList<>();
        try {
            String content = client.get(url);
            while (content.contains("<a href=")) {
                content = content.substring(content.indexOf("<a href=") + 9);
                links.add(content.substring(0, content.indexOf("\"")));
                content = content.substring(content.indexOf("\""));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.unmodifiableList(links);
    }

    /**
     * Scans given URL but also follows scanned links and build a map of URL and links contained by it.
     *
     * @param url URL to be crawled.
     * @return a map of URL and links contained by it.
     */
    @Override
    public Map<String, List<String>> crawlAll(String url) {
        Map<String, List<String>> index = new HashMap<>();
        Queue<String> linksToBeVisited = new LinkedList<>();
        linksToBeVisited.add(url);
        while (!linksToBeVisited.isEmpty()) {
            if (!index.containsKey(linksToBeVisited.element())) {
                index.put(linksToBeVisited.element(), crawl(linksToBeVisited.element()));
                linksToBeVisited.addAll(crawl(linksToBeVisited.element()));
            }
            linksToBeVisited.remove();
        }
        return Collections.unmodifiableMap(index);
    }

    /**
     * Starts crawling at given URL but builds a reverse index.
     *
     * @param url URL to be crawled.
     * @return a map of urls and their references.
     */
    @Override
    public Map<String, List<String>> crawlReverse(String url) {
        return reverseIndex(crawlAll(url));
    }

    /**
     * Builds a reverse index of URLs and links contained by it.
     *
     * @param index a map of URL and links contained by it.
     * @return a map of urls and their references.
     */
    @Override
    public Map<String, List<String>> reverseIndex(Map<String, List<String>> index) {
        Map<String, List<String>> reversedIndex = new HashMap<>();
        for (String key:index.keySet()) {
            reversedIndex.put(key, new LinkedList<String>());
        }
        for (Map.Entry<String, List<String>> entry : index.entrySet())  {
            for (String link :entry.getValue()) {
                reversedIndex.putIfAbsent(link, new LinkedList<>());
                reversedIndex.get(link).add(entry.getKey());
            }
        }
        return Collections.unmodifiableMap(reversedIndex);
    }
}
