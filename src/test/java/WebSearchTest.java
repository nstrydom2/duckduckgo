import static org.junit.Assert.*;

import org.bitnick.net.duckduckgo.WebSearch;
import org.bitnick.net.duckduckgo.entity.SearchResult;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;


public class WebSearchTest {
	@BeforeEach
	private void sleepBeforeNext() throws InterruptedException {
		// Sleep for 5 seconds between tests to keep requests
		// per min low to avoid ip bans
		Thread.sleep(5000);
	}

	@Test
	public void returnsListOfSearchResultObjects() {
		final WebSearch webSearchTest = WebSearch.instanceOf();

		List<SearchResult> testList = webSearchTest.search("Rebecca Frazer");
		assertNotNull(testList);
		testList.forEach(Assert::assertNotNull);
	}

	@Test
	public void returnsListOfSearchResultsNotNullAndNotEmpty() {
		final WebSearch webSearchTest = WebSearch.instanceOf();

		List<SearchResult> testList = webSearchTest.search("Rebecca Frazer");
		assertNotNull(testList);
		assertFalse(testList.isEmpty());
	}

	@Test
	public void testWebInstantAnswerSearchReturnIsNotNull() {
		final WebSearch webSearchTest = WebSearch.instanceOf();

		SearchResult result = webSearchTest.instantAnswerSearch("Nicholas Strydom");
		assertNotNull(result);
		assertNotEquals("", result.getTitle());
		assertNotEquals("", result.getDescription());
		assertNotEquals("", result.getUrl().toString());
	}
}
