package Basic;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.reqres.models.ReqresResponse;
import org.reqres.models.ReqresUsersResponse;
import org.reqres.payload.Login;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Utility {

    public ReqresResponse reqresResponse;
    public ReqresUsersResponse reqresUsersResponse;

    static Logger logger = LoggerFactory.getLogger(Utility.class);
    Map<String,String> apiHeader = new HashMap<>();
    public Map<String,String> createHeader() {
        apiHeader.put("x-api-key",readProperties("x-api-key"));
        apiHeader.put("accept",readProperties("accept"));
        apiHeader.put("Content-Type",readProperties("Content-Type"));
        return apiHeader;
    }

    public String readProperties(String key) {
        Properties prop = new Properties();

        try {
            prop.load(new FileInputStream("src/main/resources/config.properties"));
        }

        catch (IOException e) {
            logger.error(e.toString());
        }
        return prop.getProperty(key);
    }

    public static void assertEquals(Object actual, Object expected) {
        logger.info("actual: {} expected: {}", actual.toString(), expected.toString());
        Assert.assertEquals(actual, expected);
    }
    public static void assertNotNull(Object actual, String message) {
        logger.info(message);
        Assert.assertNotNull(actual, message);
    }

    public Object deSerialize(String response, String responseType) {
        Gson gson = new Gson();
        if(responseType.equals("ReqresResponse")) {
            reqresResponse = gson.fromJson(response, ReqresResponse.class);
            return reqresResponse;
        }
        else if((responseType.equals("ReqresUsersResponse"))) {
            reqresUsersResponse = gson.fromJson(response, ReqresUsersResponse.class);
            return reqresUsersResponse;
        }
        else
            logger.error("the ResponseType is not implemented Yet");
            return null;
    }

    public String serializeLogInPayload(Login login) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(login);
        return jsonString;
    }

    public String readJsonPathValue (String response, String key) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(response, JsonObject.class);
        return jsonObject.get(key).toString();
    }

}
