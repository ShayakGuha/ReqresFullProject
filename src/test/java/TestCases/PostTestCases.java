package TestCases;

import Basic.Base;
import Basic.Utility;
import io.restassured.response.Response;
import org.reqres.payload.Login;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PostTestCases extends Base {
    @Test
    public void validatingUserRegistration()
    {
        Login login = new Login();
        login.username = "emma.wong@reqres.in";
        login.email = "emma.wong@reqres.in";
        login.password = "test1234";
        Response response = postAPICall("postRegister", login);
        assertEquals(response.statusCode(),200);
        assertNotNull(readJsonPathValue(response.asString(),"token"), "Registered successfully with token: "+readJsonPathValue(response.asString(),"token"));
    }

    @Test
    public void validateUserLogin() {
        Login login = new Login();
        login.username = "emma.wong@reqres.in";
        login.email = "emma.wong@reqres.in";
        login.password = "test1234";
        Response registerResponse = postAPICall("postRegister", login);
        assertEquals(registerResponse.statusCode(),200);
        Response loginResponse = postAPICall("postLogin",login);
        assertEquals(loginResponse.statusCode(),200);
        assertNotNull(readJsonPathValue(loginResponse.asString(),"token"), "Login successfully with token: "+readJsonPathValue(loginResponse.asString(),"token"));

    }
}
