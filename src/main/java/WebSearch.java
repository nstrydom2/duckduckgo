import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

public class WebSearch {
	//public DesiredCapabilities capabilities = new DesiredCapabilities();
	//private WebDriver driver = null;
	
	public WebSearch() {
		//System.setProperty("phantomjs.binary.path", "/home/ghost/eclipse-workspace/duckduckgo/phantomjs/bin/phantomjs");
		
		//this.driver = new PhantomJSDriver(capabilities);
	}

	public Document getPage(String query) throws Exception {
		final String DUCKDUCKGO_URL = "https://duckduckgo.com/html?q=";

		return Jsoup.connect(DUCKDUCKGO_URL + query).get();
	}
	
	public ArrayList<URL> search(String query) throws Exception {
		final String DUCKDUCKGO_URL = "https://duckduckgo.com/html?q=";

		ArrayList<String> results = new ArrayList<String>();
		Document doc = this.getPage(query);

		//driver.get(String.format("https://duckduckgo.com/html?q=%s", query));

		//List<WebElement> elements = driver.findElements(By.tagName("a"));

		try {
			System.out.println(doc.body());

			System.out.println();
			// Find all titles, urls, and descriptions
//			for (WebElement element : elements) {
//
//				//System.out.println(element.getAttribute("href"));
//				results.add(element.getAttribute("href"));
//			}
//
//			results = new ArrayList(new HashSet(results));
//
//			for (String result : results) {
//				System.out.println(result);
//			}
//
//			// Close driver and stop phantomjs
//			driver.close();

			return null;
		} catch (Exception ex) {
			ex.printStackTrace();

			return null;
		}
	}

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
}
