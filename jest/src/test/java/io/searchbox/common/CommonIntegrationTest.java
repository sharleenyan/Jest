package io.searchbox.common;

import com.google.gson.JsonObject;
import io.searchbox.action.GenericResultAbstractAction;
import io.searchbox.client.JestResult;
import org.elasticsearch.test.ESIntegTestCase;
import org.junit.Test;

import java.io.IOException;

/**
 * @author ferhat
 */

@ESIntegTestCase.ClusterScope(scope = ESIntegTestCase.Scope.SUITE, numDataNodes = 1)
public class CommonIntegrationTest extends AbstractIntegrationTest {

    @Test
    public void testVersion() throws IOException {
        JestResult result = client.execute(new GenericResultAbstractAction() {
            @Override
            public String getURI() {
                return "";
            }

            @Override
            public String getRestMethodName() {
                return "GET";
            }
        });
        assertTrue(result.getErrorMessage(), result.isSucceeded());

        JsonObject jsonObject = result.getJsonObject();

        assertEquals(200, result.getResponseCode());
        JsonObject versionObj = jsonObject.get("version").getAsJsonObject();
        assertEquals("2.0.0-rc1", versionObj.get("number").getAsString());
    }
}
