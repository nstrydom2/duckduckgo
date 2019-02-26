/**
 * Class for creating an object to make queries to
 * duckduckgo in a Java API
 *
 * @author Nicholas Strydom
 * @version 0.1.5
 */

import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class WebSearch implements AutoCloseable {
	private WebSearch() { }

	/**
	 * GET method to retrieve web page with results.
	 *
	 * @param query Search query for duckduckgo
	 * @return
	 * @throws Exception
	 */
	public Document getPage(String query) throws Exception {
		final String DUCKDUCKGO_URL = "https://duckduckgo.com/html?q=";

		return Jsoup.connect(DUCKDUCKGO_URL + query).get();
	}

	/**
	 * Runs the query through the html page using 'getPage()' method
	 * above. Scrapes url results
	 *
	 * @param query Search query for duckduckgo
	 * @return
	 */
	public List<String> search(String query) {
		final String CLASSNAME = "result__a";

		Set<String> tempSet = new TreeSet<String>();

		try {
			Document doc = this.getPage(query);

			doc.getElementsByClass(CLASSNAME).forEach(element -> {
				tempSet.add(element.attr("href"));
			});

			return new ArrayList(tempSet);
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
	public String instantAnswerSearch(String query) {
		return this.search(query).get(0);
	}

	public static WebSearch instanceOf() {
		return new WebSearch();
	}

	@Override
	public void close() { }
}
