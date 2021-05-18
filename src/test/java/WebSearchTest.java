import static org.junit.Assert.*;

import org.bitnick.net.duckduckgo.WebSearch;
import org.bitnick.net.duckduckgo.entity.SearchResult;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.assertj.core.api.Assertions.*;

import java.util.List;


public class WebSearchTest {
	@BeforeEach
	private void sleepBeforeNext() throws InterruptedException {
		// Sleep for 3 seconds between tests to keep requests
		// per min low to avoid ip bans
		Thread.sleep(3000);
	}

	@Test
	public void returnsListOfSearchResultsNotNullAndNotEmpty() {
		final WebSearch webSearchTest = WebSearch.instanceOf();

		List<SearchResult> testList = webSearchTest.search("anonfile api");
		assertThat(testList)
				.isNotNull()
				.isInstanceOf(List.class)
				.isNotEmpty()
				.doesNotContainNull()
				.hasSizeBetween(20, 30);
	}

	@Test
	public void returnsInstantAnswerSearchReturnIsNotNull() {
		final WebSearch webSearchTest = WebSearch.instanceOf();

		SearchResult result = webSearchTest.instantAnswerSearch("anonfile api");
		assertThat(result).isNotNull();
	}
}
