package initial;

import ca.zzsh.framework.EnvironmentManager;
import io.restassured.RestAssured;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import static io.restassured.RestAssured.given;

public class GlobalSet {
    @BeforeSuite
    public void setUp(){
        System.out.println("Now setting up config before test.");
        EnvironmentManager em = new EnvironmentManager();
        RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";
        given().contentType("application/json");

    }


    @AfterSuite
    public void tearDown(){
        System.out.println("Now tearing down the test.");
    }
}
