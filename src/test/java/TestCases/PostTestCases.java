package TestCases;

import Basic.Base;
import Basic.Utility;
import io.restassured.response.Response;
import org.reqres.payload.Login;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PostTestCases extends Base {
    @Test(priority = 7)
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
    @DataProvider(name = "userLogin")
    public Object[][] getUserLogin() {
        return new Object[][]{{"janet.weaver@reqres.in","test1234"},
                {"emma.wong@reqres.in","test1234"}
        };
    }

    @Test(dataProvider = "userLogin", priority = 8)
    public void validateUserLogin(String username,String password) {
        Login login = new Login();
        login.username = username;
        login.email=username;
        login.password = password;
        Response registerResponse = postAPICall("postRegister", login);
        assertEquals(registerResponse.statusCode(),200);
        Response loginResponse = postAPICall("postLogin",login);
        assertEquals(loginResponse.statusCode(),200);
        assertNotNull(readJsonPathValue(loginResponse.asString(),"token"),username+ " Login successfully with token: "+readJsonPathValue(loginResponse.asString(),"token"));

    }
}
