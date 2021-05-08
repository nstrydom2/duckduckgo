import static org.junit.Assert.*;

import org.bitnick.net.duckduckgo.WebSearch;
import org.bitnick.net.duckduckgo.entity.SearchResult;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

public class WebSearchTest {
	@Ignore
	@Test
	public void returnsJsoupGetResult() throws Exception {
		final WebSearch webSearchTest = WebSearch.instanceOf();

		assertNotNull(webSearchTest.getPage("test"));
	}

	@Test
	public void printsElementsIAmLookingFor() throws Exception {
		try (WebSearch webSearchTest = WebSearch.instanceOf()) {
			List<SearchResult> testList = webSearchTest.search("Rebecca Frazer");
			assertNotNull(testList);
			assertFalse(testList.isEmpty());
		}
	}

	@Test
	public void successfulTryWithResourcesAutoCloseable() throws Exception {
		try (WebSearch webSearchTest = WebSearch.instanceOf()) {
			webSearchTest.search("F1 racing").forEach(r -> {
				System.out.println(r.getTitle());
				System.out.println(r.getDescription());
				System.out.println(r.getUrl().toString());
			});
		}

	}

	@Test
	public void testWebInstantAnswerSearchReturnIsNotNull() throws Exception {
		final WebSearch webSearchTest = WebSearch.instanceOf();

		SearchResult result = webSearchTest.instantAnswerSearch("Nicholas Strydom");
		assertNotNull(result);
		assertFalse(result.getTitle().equals(""));
		assertFalse(result.getDescription().equals(""));
		assertFalse(result.getUrl().toString().equals(""));
	}
}
