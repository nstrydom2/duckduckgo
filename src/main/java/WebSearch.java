import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class WebSearch {
	private WebSearch() { }

	public Document getPage(String query) throws Exception {
		final String DUCKDUCKGO_URL = "https://duckduckgo.com/html?q=";

		return Jsoup.connect(DUCKDUCKGO_URL + query).get();
	}
	
	public List<String> search(String query) throws Exception {
		final String CLASSNAME = "result__a";

		HashSet<String> tempSet = new HashSet<String>();

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

	@Deprecated
	public JSONObject instantAnswerSearch(String query) throws Exception {
		// Get request to api.duckduckgo.com
//		driver.get(String.format("https://api.duckduckgo.com/?q=%s&format=json", query));
//
//		// Debug response
//		System.out.println(driver.getPageSource());
//
//		// Grab json object from response
//		JSONObject jsonObject = new JSONObject(Jsoup.parse(driver.getPageSource()).text());
//
//		// Close phantomjs Driver
//		driver.close();
//
		//return jsonObject;
		return null;
	}

	public static WebSearch instanceOf() {
		return new WebSearch();
	}
}
