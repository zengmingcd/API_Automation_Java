package initial;

import ca.zzsh.framework.ConfigLoader;
import ca.zzsh.framework.EnvironmentManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class GlobalSet {
    @BeforeSuite
    public void setUp(){
        try{
            Reporter.log("Set Up Test Environment...");
            ConfigLoader my_config = new ConfigLoader("testEnv");
            RestAssured.baseURI = (String)my_config.get_by_key("host");
            String token = get_token((String)my_config.get_by_key("username"), (String)my_config.get_by_key("password"));
            HashMap<String, String> headers = new HashMap<String, String>();
            headers.put("Content-Type", "application/json");
            headers.put("Authorization", "Bearer " + token);
            RequestSpecBuilder my_request_builder = new RequestSpecBuilder();
            my_request_builder.addHeaders(headers);
            RestAssured.requestSpecification = my_request_builder.build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


    @AfterSuite
    public void tearDown(){
        Reporter.log("Tear Down Test Environment...");
    }

    private static String get_token(String username, String password){
        HashMap<String, String> body = new HashMap<String, String>();
        body.put("username", username);
        body.put("password", password);
        Response res = given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/auth/token")
                .then()
                .statusCode(200)
                .extract().response();
        return res.getBody().jsonPath().getString("result.token");
    }
}
