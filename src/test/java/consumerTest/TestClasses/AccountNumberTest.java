package consumerTest.TestClasses;

import consumerTest.Payloads.Payloads;
import consumerTest.Pojos.AddAccountNumber;
import consumerTest.Pojos.UpdateAccountNumber;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class AccountNumberTest {


    @Test(priority = 1)
    public void addAccountNumber(){

        String token = Authentication.getAccessToken();
        Map<String, String> headers = new HashMap<>();
        if (token != null) {
            headers.put("Authorization", "Bearer " + token);
        }

        AddAccountNumber payload = Payloads.getCreateAccountNumberPayloadFromPojo();

        Response response = RestAssured.given().log().all()
                .baseUri(Authentication.setUp())
                .headers(headers)
                .contentType(ContentType.JSON)
                .body(payload)
                .post("/consumer/service-provider-account/register")
                .then().log().all().extract().response();

        Assert.assertEquals(response.statusCode(),201);

        String responseBody = response.getBody().asString();
        System.out.println("Response Body: " + responseBody);

    }

    @Test(priority = 2)
    public void viewAccountNumbers(){

        String token = Authentication.getAccessToken();
        Map<String, String> headers = new HashMap<>();
        if (token != null) {
            headers.put("Authorization", "Bearer " + token);
        }

        Response response = RestAssured.given().log().all()
                .baseUri(Authentication.setUp())
                .headers(headers)
                .contentType(ContentType.JSON)
                .get("/consumer/account-number/view")
                .then().log().all().extract().response();

        Assert.assertEquals(response.statusCode(),201);

    }

    @Test(priority = 3)
    public void updateAccountNumber(){

        String token = Authentication.getAccessToken();
        Map<String, String> headers = new HashMap<>();
        if (token != null) {
            headers.put("Authorization", "Bearer " + token);
        }

        UpdateAccountNumber payload = Payloads.getUpdateAccountNumberPayloadFromPojo();

        Response response = RestAssured.given().log().all()
                .baseUri(Authentication.setUp())
                .headers(headers)
                .contentType(ContentType.JSON)
                .body(payload)
                .patch("/consumer/service-provider-account/update")
                .then().log().all().extract().response();

        Assert.assertEquals(response.statusCode(),201);


    }

    @Test(priority = 4)
    public void viewUnusedAccountNumbers(){

        String token = Authentication.getAccessToken();
        Map<String, String> headers = new HashMap<>();
        if (token != null) {
            headers.put("Authorization", "Bearer " + token);
        }

        Response response = RestAssured.given().log().all()
                .baseUri(Authentication.setUp())
                .headers(headers)
                .contentType(ContentType.JSON)
                .get("/consumer/account-number/unassigned/view")
                .then().log().all().extract().response();

        Assert.assertEquals(response.statusCode(),201);

    }


}
