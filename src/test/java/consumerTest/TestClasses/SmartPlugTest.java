package consumerTest.TestClasses;

import consumerTest.Payloads.Payloads;
import consumerTest.Pojos.RegisterSmartPlug;
import consumerTest.Pojos.UnlinkDevice;
import consumerTest.Pojos.UpdateSmartPlug;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class SmartPlugTest {

    String spaceCluster_Id;
    String space_Id;
    String SmartPlugId;

    @Test(priority = 1)
    public void addSmartPlug(ITestContext context){

        spaceCluster_Id = (String) context.getAttribute("spaceCluster_Id");
        space_Id = (String) context.getAttribute("space_Id");
        String id = (String) context.getAttribute("id");

        String token = Authentication.getAccessToken();
        Map<String, String> headers = new HashMap<>();
        if (token != null) {
            headers.put("Authorization", "Bearer " + token);
        }

        RegisterSmartPlug payload = Payloads.getRegisterSmartPlugPayloadFromPojo(id);

        Response response = RestAssured.given().log().all()
                .baseUri(Authentication.setUp())
                .headers(headers)
                .contentType(ContentType.JSON)
                .body(payload)
                .post("space-cluster/"+spaceCluster_Id+"/space/"+space_Id+"/smart-device/smart-plug/register")
                .then().log().all().extract().response();

        Assert.assertEquals(response.statusCode(),201);

        SmartPlugId = response.jsonPath().getString("registeredSmartPlugs[0].id");
        context.setAttribute("SmartPlugId", SmartPlugId);

    }

    @Test(priority = 2, dependsOnMethods = "addSmartPlug")
    public void updateSmartPlug(ITestContext context){

        String id = (String) context.getAttribute("id");

        String token = Authentication.getAccessToken();
        Map<String, String> headers = new HashMap<>();
        if (token != null) {
            headers.put("Authorization", "Bearer " + token);
        }

        UpdateSmartPlug payload = Payloads.getUpdateSmartPlugPayloadFromPojo(id);

        Response response = RestAssured.given().log().all()
                .baseUri(Authentication.setUp())
                .headers(headers)
                .contentType(ContentType.JSON)
                .body(payload)
                .put("space-cluster/"+spaceCluster_Id+"/space/"+space_Id+"/smart-plug/"+SmartPlugId)
                .then().log().all().extract().response();

        Assert.assertEquals(response.statusCode(),200);

    }

    @Test(priority = 3, dependsOnMethods = "addSmartPlug")
    public void UnlinkDeviceFromController(ITestContext context){

        String id = (String) context.getAttribute("id");

        String token = Authentication.getAccessToken();
        Map<String, String> headers = new HashMap<>();
        if (token != null) {
            headers.put("Authorization", "Bearer " + token);
        }

        UnlinkDevice payload = Payloads.getUnlinkDevicePayloadFromPojo(id);

        Response response = RestAssured.given().log().all()
                .baseUri(Authentication.setUp())
                .headers(headers)
                .contentType(ContentType.JSON)
                .body(payload)
                .put("space-cluster/"+spaceCluster_Id+"/space/"+space_Id+"/smart-plug/"+SmartPlugId+"/unlink")
                .then().log().all().extract().response();

        Assert.assertEquals(response.statusCode(),200);

    }

}
