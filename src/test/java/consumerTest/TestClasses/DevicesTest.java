package consumerTest.TestClasses;

import consumerTest.Payloads.Payloads;
import consumerTest.Pojos.RegisterDevices;
import consumerTest.Pojos.UpdateDevices;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class DevicesTest {

    String spaceCluster_Id;
    String space_Id;

    @Test(priority = 1)
    public void addDevice(ITestContext context){

        spaceCluster_Id = (String) context.getAttribute("spaceCluster_Id");
        space_Id = (String) context.getAttribute("space_Id");

        String token = Authentication.getAccessToken();
        Map<String, String> headers = new HashMap<>();
        if (token != null) {
            headers.put("Authorization", "Bearer " + token);
        }

        RegisterDevices payload = Payloads.getRegisterDevicePayloadFromPojo();

        Response response = RestAssured.given().log().all()
                .baseUri(Authentication.setUp())
                .headers(headers)
                .contentType(ContentType.JSON)
                .body(payload)
                .post("space-cluster/"+spaceCluster_Id+"/space/"+space_Id+"/power-consumer/device/register")
                .then().log().all().extract().response();

        Assert.assertEquals(response.statusCode(),201);

        String id = response.jsonPath().getString("registeredDevices[0].id");
        context.setAttribute("id", id);

    }

    @Test(priority = 2, dependsOnMethods = "addDevice")
    public void updateDevice(ITestContext context){

        String id = (String) context.getAttribute("id");

        String token = Authentication.getAccessToken();
        Map<String, String> headers = new HashMap<>();
        if (token != null) {
            headers.put("Authorization", "Bearer " + token);
        }

        UpdateDevices payload = Payloads.getUpdateDevicePayloadFromPojo(id);

        Response response = RestAssured.given().log().all()
                .baseUri(Authentication.setUp())
                .headers(headers)
                .contentType(ContentType.JSON)
                .body(payload)
                .put("space-cluster/"+spaceCluster_Id+"/space/"+space_Id+"/power-consumer/device/update")
                .then().log().all().extract().response();

        Assert.assertEquals(response.statusCode(),201);

    }

    @Test(priority = 3)
    public void viewAllDevices(){

        String token = Authentication.getAccessToken();
        Map<String, String> headers = new HashMap<>();
        if (token != null) {
            headers.put("Authorization", "Bearer " + token);
        }

        Response response = RestAssured.given().log().all()
                .baseUri(Authentication.setUp())
                .headers(headers)
                .contentType(ContentType.JSON)
                .get("power-consumer/list")
                .then().log().all().extract().response();

        Assert.assertEquals(response.statusCode(),200);

    }

}
