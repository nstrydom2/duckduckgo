import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import junit.framework.TestCase;

import java.util.List;

public class WebSearchTest {
	private final WebSearch webSearchTest = WebSearch.instanceOf();

	@Ignore
	@Test
	public void returnsJsoupGetResult() throws Exception {
		assertNotNull(webSearchTest.getPage("test"));
	}

	@Test
	public void printsElementsIAmLookingFor() throws Exception {
		List<String> testList = webSearchTest.search("Rebecca Frazer");

		testList.forEach(System.out::println);

		assertNotNull(testList);
	}
	
	@Ignore
	@Test
	public void testWebInstantAnswerSearchReturnIsNotNull() throws Exception {
		String result = webSearchTest.instantAnswerSearch("paper");
		
		System.out.println(result);
	}
	
	

}
