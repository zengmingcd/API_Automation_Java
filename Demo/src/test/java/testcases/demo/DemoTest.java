package testcases.demo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class DemoTest {
    @Test
    public void test_rest_assured_request(){
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
                .statusCode(200)
                .extract()
                .response();
        Assert.assertEquals((int)res.path("data.id"), 9500, "User Id is error");

    }
}
