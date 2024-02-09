package consumerTest.TestClasses;

import consumerTest.Payloads.Payloads;
import consumerTest.Pojos.RegisterAcController;
import consumerTest.Pojos.UpdateAcController;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class AcControllerTest {

    String spaceCluster_Id;
    String space_Id;
    String AcControllerId;

    @Test(priority = 1)
    public void addAcController(ITestContext context){

        spaceCluster_Id = (String) context.getAttribute("spaceCluster_Id");
        space_Id = (String) context.getAttribute("space_Id");
        String id = (String) context.getAttribute("id");

        String token = Authentication.getAccessToken();
        Map<String, String> headers = new HashMap<>();
        if (token != null) {
            headers.put("Authorization", "Bearer " + token);
        }

        RegisterAcController payload = Payloads.getRegisterAcControllerPayloadFromPojo(id);

        Response response = RestAssured.given().log().all()
                .baseUri(Authentication.setUp())
                .headers(headers)
                .contentType(ContentType.JSON)
                .body(payload)
                .post("space-cluster/"+spaceCluster_Id+"/space/"+space_Id+"/smart-device/ac-controller/register")
                .then().log().all().extract().response();

        Assert.assertEquals(response.statusCode(),201);

        AcControllerId = response.jsonPath().getString("registeredAcControllers[0].id");

    }

    @Test(priority = 2, dependsOnMethods = "addAcController")
    public void updateAcController(ITestContext context){

        String id = (String) context.getAttribute("id");

        String token = Authentication.getAccessToken();
        Map<String, String> headers = new HashMap<>();
        if (token != null) {
            headers.put("Authorization", "Bearer " + token);
        }

        UpdateAcController payload = Payloads.getUpdateAcControllerPlugPayloadFromPojo(id);

        Response response = RestAssured.given().log().all()
                .baseUri(Authentication.setUp())
                .headers(headers)
                .contentType(ContentType.JSON)
                .body(payload)
                .put("space-cluster/"+spaceCluster_Id+"/space/"+space_Id+"/ac-controller/"+AcControllerId)
                .then().log().all().extract().response();

        Assert.assertEquals(response.statusCode(),201);

    }

}
