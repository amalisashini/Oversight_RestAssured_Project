package consumerTest.TestClasses;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Authentication {

    private static final String BASE_URL = "https://api.ovst-dev.syntaxgenie.com/api";
    private static final String USERNAME = "Sanduni_Herath";
    private static final String PASSWORD = "Saduni1998#";

    private static String accessToken;

    @BeforeTest
    public static String setUp() {
         RestAssured.baseURI = BASE_URL;
        return RestAssured.baseURI;
    }

    public static String getAccessToken(){

        return accessToken;

    }

    @Test
    public void testLoginAndGetToken() {
        Response response = RestAssured.given()
                .contentType("application/json")
                .body("{ \"username\": \"" + USERNAME + "\", \"password\": \"" + PASSWORD + "\",\"rememberMe\": true,\n" +
                        "  \"isLecoUser\": false }")
                .when()
                .post("/login")
                .then()
                .extract().response();
        Assert.assertEquals(response.statusCode(),200);

        accessToken = response.jsonPath().getString("token");
        System.out.println("Bearer Token: " + accessToken);
    }


}
