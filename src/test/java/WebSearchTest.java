import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Ignore;
import org.junit.Test;

import junit.framework.TestCase;

import java.util.List;

public class WebSearchTest {
	private final WebSearch webSearchTest = WebSearch.instanceOf();
	
	@Test
	public void returnsJsoupGetResult() throws Exception {
		assertNotNull(webSearchTest.getPage("test"));
	}

	@Test
	public void printsElementsIAmLookingFor() throws Exception {
		List<String> testList = webSearchTest.search("test");

		testList.forEach(System.out::println);

		assertNotNull(testList);
	}
	
	@Ignore
	@Test
	public void testWebInstantAnswerSearchReturnIsNotNull() throws Exception {
		JSONObject jsonObject = webSearchTest.instantAnswerSearch("paper");
		
		System.out.println(jsonObject.toString());
		
		assertNotNull(jsonObject);
	}
	
	

}
