package org.bitnick.net.duckduckgo;

import org.bitnick.net.duckduckgo.entity.SearchResult;
import org.jsoup.Connection.Response;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Class for creating an object to make queries to
 * duckduckgo in a Java API
 *
 * @author Nicholas Strydom
 * @version 0.1.2
 */
public class WebSearch {

    /**
     * GET method to retrieve web page with results.
     *
     * @param query Search query for duckduckgo
     * @return Document
     * @throws Exception
     */
    public static Document getPage(String query) throws Exception {
        return WebSearch.getPage(query, 0);
    }

    /**
     * GET method to retrieve web page with results.
     *
     * @param query Search query for duckduckgo
     * @return
     * @throws Exception
     */
    private static Document getPage(String query, int retryCount) throws Exception {
        final String DUCKDUCKGO_HOST = "http://html.duckduckgo.com";
        final String SEARCH_ENDPOINT = "/html";
        final String QUERY_PARAMETER = "q=" + URLEncoder.encode(query.toLowerCase(),
                StandardCharsets.UTF_8);
        final String SEARCH_URI = String.format("%s%s?%s", DUCKDUCKGO_HOST,
                SEARCH_ENDPOINT, QUERY_PARAMETER);

        try {
            Response response = Jsoup.connect(SEARCH_URI)
                    .timeout(12000)
                    .execute();
            if (response.statusCode() != 200)
                throw new HttpStatusException("Status code != 200", response.statusCode(),
                        response.url().toString());

            Document doc = response.parse();
            if (doc == null)
                throw new IOException("Html document was null");

            return doc;
        } catch (HttpStatusException hex) {
            if (retryCount < 3)
                return WebSearch.getPage(query, retryCount + 1);

            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Runs the query through the html page using 'getPage()' method
     * above. Scrapes url results
     *
     * @param query Search query for duckduckgo
     * @return
     */
    public List<SearchResult> search(String query) {
        final String RESULT_TITLE_CLASSNAME = "result__a";
        final String DESC_CLASSNAME = "result__snippet";

        try {
            Document doc = WebSearch.getPage(query);
            if (doc == null)
                throw new IOException("Unable to load Html document.");

            Elements elements = doc.getElementsByClass(RESULT_TITLE_CLASSNAME);
            List<SearchResult> results = elements.stream()
                    .map(element -> {
                        try {
                            SearchResult result = new SearchResult();
                            result.setTitle(element.text());
                            result.setUrl(new URL(element.attr("href")));
                            result.setDescription(doc.getElementsByClass(DESC_CLASSNAME).stream()
                                    .filter(e -> e.attr("href").equals(result.getUrl().toString()))
                                    .findFirst()
                                    .get()
                                    .text());
                            return result;
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                            return null;
                        }
                    }).collect(toList());

            return results;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Instant answer solution, completely inefficient but easy
     * and not redundant
     *
     * @param query Search query for duckduckgo
     * @return
     */
    public SearchResult instantAnswerSearch(String query) {
        return this.search(query).get(0);
    }

    public static WebSearch instanceOf() {
        return new WebSearch();
    }
}
