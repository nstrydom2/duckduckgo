import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.bitnick.net.duckduckgo.WebSearch;
import org.bitnick.net.duckduckgo.entity.SearchResult;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;


public class WebSearchTest {
	@BeforeEach
	private void sleepBeforeNext() throws InterruptedException {
		// Sleep for 3 seconds between tests to keep requests
		// per min low to avoid ip bans
		Thread.sleep(3000);
	}

	@Test
	public void returnsListOfSearchResultsNotNullAndNotEmpty() throws Exception {
		Path resourceDirectory = Paths.get("src","test","resources");

		WebSearch mockedWebSearch = Mockito.spy(WebSearch.instanceOf());

		Path testHtmlPath = Path.of(resourceDirectory.toFile().getAbsolutePath() + "\\Test.html");
		File testHtmlFile = new File(String.valueOf(testHtmlPath));

		Document doc = Jsoup.parse(testHtmlFile, "UTF-8");
		Mockito.when(mockedWebSearch.getPage(anyString())).thenReturn(doc);

		List<SearchResult> testList = mockedWebSearch.search("anonfile api");
		assertThat(testList)
				.isNotNull()
				.isInstanceOf(List.class)
				.isNotEmpty()
				.doesNotContainNull()
				.hasSizeBetween(1, 12);
	}

	@Test
	public void returnsInstantAnswerSearchReturnIsNotNull() throws Exception {
		Path resourceDirectory = Paths.get("src","test","resources");

		WebSearch mockedWebSearch = Mockito.spy(WebSearch.instanceOf());

		Path testHtmlPath = Path.of(resourceDirectory.toFile().getAbsolutePath() + "\\Test.html");
		File testHtmlFile = new File(String.valueOf(testHtmlPath));

		Document doc = Jsoup.parse(testHtmlFile, "UTF-8");
		Mockito.when(mockedWebSearch.getPage(anyString())).thenReturn(doc);

		SearchResult result = mockedWebSearch.instantAnswerSearch("anonfile api");
		assertThat(result)
				.isNotNull()
				.isInstanceOf(SearchResult.class);
	}
}
