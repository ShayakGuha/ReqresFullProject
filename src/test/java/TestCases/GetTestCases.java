package TestCases;

import Basic.Base;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class GetTestCases extends Base{

    Map<String,String> queryParam = new HashMap<>();
    @Test(priority = 1)
    public void getCallWithValidId() {
        queryParam.put("page","11");
        queryParam.put("per_page","1");
        assertEquals(getAPICall("getWithUser",queryParam).statusCode(),200);
    }

    @Test(priority = 2)
    public void getCallWithInvalidData() {
        queryParam.put("page","13");
        queryParam.put("per_page","1");
        getAPICall("getWithUser",queryParam);
        assertEquals(reqresResponse.data.size(),0);
    }

    @Test
    public void validatePageNumber()
    {
        queryParam.put("page","1");
        queryParam.put("per_page","12");
        getAPICall("getWithUser",queryParam);
        assertEquals(reqresResponse.page,1);
    }

    @Test
    public void checkDataSize(){
        queryParam.put("page","1");
        queryParam.put("per_page","12");
        getAPICall("getWithUser",queryParam);
        assertEquals(reqresResponse.data.size(),12);

    }
    @Test
    public void getCallWithValidUser() {
        assertEquals(getAPICall("getWithUsers","3").statusCode(),200);

    }
    @DataProvider(name = "user")
    public Object[][] getUser() {
        return new Object[][]{{"2", "Janet", "Weaver", "janet.weaver@reqres.in"},
                {"3", "Emma", "Wong", "emma.wong@reqres.in"}
        };
    }
    @Test(dataProvider = "user")
    public void checkUserInfo(String id, String fname, String lname, String email)
    {
        getAPICall("getWithUsers",id);
        assertEquals(reqresUsersResponse.data.email,email);
        assertEquals(reqresUsersResponse.data.first_name,fname);
        assertEquals(reqresUsersResponse.data.last_name,lname);
    }


}
