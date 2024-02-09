package consumerTest.TestClasses;

import consumerTest.Payloads.Payloads;
import consumerTest.Pojos.Schedule;
import consumerTest.Pojos.UpdateSmartPlug;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class ScheduleTest {

    String spaceCluster_Id;
    String space_Id;
    String semiAutomationSchedule_Id;

    @Test(priority = 1)
    public void addSchedule(ITestContext context){

        spaceCluster_Id = (String) context.getAttribute("spaceCluster_Id");
        space_Id = (String) context.getAttribute("space_Id");
        String id = (String) context.getAttribute("id");

        String token = Authentication.getAccessToken();
        Map<String, String> headers = new HashMap<>();
        if (token != null) {
            headers.put("Authorization", "Bearer " + token);
        }

        Schedule payload = Payloads.getSchedulePayloadFromPojo(id);

        Response response = RestAssured.given().log().all()
                .baseUri(Authentication.setUp())
                .headers(headers)
                .contentType(ContentType.JSON)
                .body(payload)
                .post("space-cluster/"+spaceCluster_Id+"/sub-root/"+space_Id+"/power-consumer-usage/semi-automated/schedule")
                .then().log().all().extract().response();

        Assert.assertEquals(response.statusCode(),201);

        semiAutomationSchedule_Id = response.jsonPath().getString("semiAutomationScheduleId");

    }

    @Test(priority = 2, dependsOnMethods = "addSchedule")
    public void updateSchedule(ITestContext context){

        String id = (String) context.getAttribute("id");

        String token = Authentication.getAccessToken();
        Map<String, String> headers = new HashMap<>();
        if (token != null) {
            headers.put("Authorization", "Bearer " + token);
        }

        Schedule payload = Payloads.getSchedulePayloadFromPojo(id);

        Response response = RestAssured.given().log().all()
                .baseUri(Authentication.setUp())
                .headers(headers)
                .contentType(ContentType.JSON)
                .body(payload)
                .put("space-cluster/"+spaceCluster_Id+"/sub-root/"+space_Id+"/power-consumer-usage-schedule/semi-automated/"+semiAutomationSchedule_Id+"/update")
                .then().log().all().extract().response();

        Assert.assertEquals(response.statusCode(),201);

    }

    @Test(priority = 3)
    public void viewSchedule(){

        String token = Authentication.getAccessToken();
        Map<String, String> headers = new HashMap<>();
        if (token != null) {
            headers.put("Authorization", "Bearer " + token);
        }

        Response response = RestAssured.given().log().all()
                .baseUri(Authentication.setUp())
                .headers(headers)
                .contentType(ContentType.JSON)
                .get("space-cluster/"+spaceCluster_Id+"/sub-root/"+space_Id+"/power-consumer-usage-schedule/semi-automated/"+semiAutomationSchedule_Id+"/view")
                .then().log().all().extract().response();

        Assert.assertEquals(response.statusCode(),200);

    }

}
