package testcases.demo;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class DemoTest {
    @Test (description = "Rest-assured test demo")
    public void test_rest_assured_request(){
        Reporter.log("Rest-assured test demo.");
        HashMap<String, String> body = new HashMap<String, String>();
        body.put("name", "Lawrence");
        body.put("salary", "123");
        body.put("age", "23");
        Response res = given()
                .log().all()
                .body(body)
                .when()
                .post("/create")
                .then()
                .log().all()
                .extract().response();
        Assert.assertEquals((int)res.path("data.id"), 9500, "User Id is error");
    }

    @Test (description = "DataProvider demo",
            dataProvider = "demo",
            dataProviderClass = DataProviders.class)
    public void test_demo_dataprovider(String param1, int param2){
        Reporter.log("DataProvider demo");
        Assert.assertEquals(param1, "AAA");
        Assert.assertEquals(param2, 111);
    }
}
