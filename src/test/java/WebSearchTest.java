import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import junit.framework.TestCase;

import java.util.List;

public class WebSearchTest {
	@Ignore
	@Test
	public void returnsJsoupGetResult() throws Exception {
		final WebSearch webSearchTest = WebSearch.instanceOf();

		assertNotNull(webSearchTest.getPage("test"));
	}

	@Ignore
	@Test
	public void printsElementsIAmLookingFor() throws Exception {
		final WebSearch webSearchTest = WebSearch.instanceOf();

		List<String> testList = webSearchTest.search("Rebecca Frazer");

		testList.forEach(System.out::println);

		assertNotNull(testList);
	}

	@Test
	public void successfulTryWithResourcesAutoCloseable() throws Exception {
		try (WebSearch webSearchTest = WebSearch.instanceOf()) {
			webSearchTest.search("F1 racing").forEach(System.out::println);
		}

	}
	
	@Ignore
	@Test
	public void testWebInstantAnswerSearchReturnIsNotNull() throws Exception {
		final WebSearch webSearchTest = WebSearch.instanceOf();

		String result = webSearchTest.instantAnswerSearch("paper");
		
		System.out.println(result);
	}
	
	

}
