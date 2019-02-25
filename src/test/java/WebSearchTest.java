import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Ignore;
import org.junit.Test;

import junit.framework.TestCase;

public class WebSearchTest {
	private final WebSearch webSearchTest = new WebSearch();
	
	@Test
	public void returnsJsoupGetResult() throws Exception {
		assertNotNull(webSearchTest.getPage("test"));
	}

	@Test
	public void printsElementsIAmLookingFor() throws Exception {
		webSearchTest.search("test");
	}
	
	@Ignore
	@Test
	public void testWebInstantAnswerSearchReturnIsNotNull() throws Exception {
		JSONObject jsonObject = webSearchTest.instantAnswerSearch("paper");
		
		System.out.println(jsonObject.toString());
		
		assertNotNull(jsonObject);
	}
	
	

}
