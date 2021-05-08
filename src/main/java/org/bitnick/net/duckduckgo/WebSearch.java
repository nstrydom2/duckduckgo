package org.bitnick.net.duckduckgo;

/**
 * Class for creating an object to make queries to
 * duckduckgo in a Java API
 *
 * @author Nicholas Strydom
 * @version 0.1.5
 */
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

import org.bitnick.net.duckduckgo.entity.SearchResult;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class WebSearch implements AutoCloseable {

	/**
	 * GET method to retrieve web page with results.
	 *
	 * @param query Search query for duckduckgo
	 * @return Document
	 * @throws Exception
	 */
	public Document getPage(String query) throws Exception {
		return getPage(query, 0);
	}

	/**
	 * GET method to retrieve web page with results.
	 *
	 * @param query Search query for duckduckgo
	 * @return
	 * @throws Exception
	 */
	public Document getPage(String query, int retryCount) throws Exception {
		final String DUCKDUCKGO_URL = "https://html.duckduckgo.com/html?q=";
		final String SEARCH_QUERY = URLEncoder.encode(query.toLowerCase(),
				StandardCharsets.UTF_8);

		try {
			Document doc = Jsoup.connect(DUCKDUCKGO_URL + SEARCH_QUERY).get();
			if (doc == null)
				throw new NullPointerException();

			return doc;
		} catch (NullPointerException ex) {
			if (retryCount < 3) {
				Thread.sleep(3000);
				return getPage(query, retryCount+1);
			}

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
		List<SearchResult> results = new ArrayList<>();

		try {
			Document doc = this.getPage(query);
			doc.getElementsByClass(RESULT_TITLE_CLASSNAME).forEach(element -> {
				SearchResult result = new SearchResult();
				result.setTitle(element.text());

				try {
					result.setUrl(new URL(element.attr("href")));
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}

				doc.getElementsByClass(DESC_CLASSNAME).forEach(e -> {
					if (e.attr("href").equals(result.getUrl().toString()))
						result.setDescription(e.text());
				});

				results.add(result);
			});

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

	@Override
	public void close() { }
}
