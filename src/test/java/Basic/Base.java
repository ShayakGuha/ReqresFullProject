package Basic;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.reqres.payload.Login;

import java.util.Map;

public class Base extends Utility{


    public Response getAPICall(String type, Map<String,String> param) {
        Response response = RestAssured.given().queryParams(param).headers(createHeader()).get(readProperties("baseURL")+readProperties(type)).then().extract().response();
        logger.info(response.asString());
        deSerialize(response.asString(), "ReqresResponse");
        return response;
    }
    public Response getAPICall(String type, String id) {
        Response response = RestAssured.given().headers(createHeader()).get(readProperties("baseURL")+readProperties(type)+id).then().extract().response();
        logger.info(response.asString());
        deSerialize(response.asString(), "ReqresUsersResponse");
        return response;
    }
   public Response postAPICall(String type, Login login) {
       String jsonString = serializeLogInPayload(login);
       logger.info(jsonString);
       Response response = RestAssured.given().headers(createHeader()).body(jsonString).when().post(readProperties("baseURL")+readProperties(type)).then().extract().response();
       logger.info(response.asString());
       return response;
   }


}
