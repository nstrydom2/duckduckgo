import org.bitnick.net.duckduckgo.WebSearchV2;
import org.bitnick.net.duckduckgo.entity.SearchResult;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class WebSearchV2Test {

    @Test
    public void inheritanceTest() throws Exception {
        WebSearchV2 webSearchTest = WebSearchV2.instanceOf();
        List<SearchResult> testObj = webSearchTest.search("Test");

        testObj.forEach(System.out::println);
        assertNotNull(testObj);
    }
}
