package consumerTest.TestClasses;

import consumerTest.Payloads.Payloads;
import consumerTest.Pojos.SubSpaces;
import consumerTest.Pojos.UpdateSpace;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class SpaceTest {

    String spaceCluster_Id;
    String spaceId;

        @Test(priority = 1)
        public void addSpace(ITestContext context) {

            spaceCluster_Id = (String) context.getAttribute("spaceCluster_Id");
            String rootSpace_Id = (String) context.getAttribute("rootSpace_Id");

            String token = Authentication.getAccessToken();
            Map<String, String> headers = new HashMap<>();
            if (token != null) {
                headers.put("Authorization", "Bearer " + token);
            }

            SubSpaces payload = Payloads.getSubSpacesPayloadFromPojo(rootSpace_Id);

            Response response = RestAssured.given().log().all()
                    .baseUri(Authentication.setUp())
                    .headers(headers)
                    .contentType(ContentType.JSON)
                    .body(payload)
                    .post("/space-cluster/"+spaceCluster_Id+"/space/add")
                    .then().log().all().extract().response();

            Assert.assertEquals(response.statusCode(),201);

            spaceId = response.jsonPath().getString("spaceId");
            context.setAttribute("space_Id", spaceId);

        }

    @Test(priority = 2, dependsOnMethods = "addSpace")
    public void updateSpace(){

        String token = Authentication.getAccessToken();
        Map<String, String> headers = new HashMap<>();
        if (token != null) {
            headers.put("Authorization", "Bearer " + token);
        }

        UpdateSpace payload = Payloads.getUpdateSpacePayloadFromPojo();

        Response response = RestAssured.given().log().all()
                .baseUri(Authentication.setUp())
                .headers(headers)
                .contentType(ContentType.JSON)
                .body(payload)
                .put("/space-cluster/"+spaceCluster_Id+"/space/"+spaceId+"/update")
                .then().log().all().extract().response();

        Assert.assertEquals(response.statusCode(),201);

    }

    @Test(priority = 3)
    public void viewSpace(){

        String token = Authentication.getAccessToken();
        Map<String, String> headers = new HashMap<>();
        if (token != null) {
            headers.put("Authorization", "Bearer " + token);
        }

        Response response = RestAssured.given().log().all()
                .baseUri(Authentication.setUp())
                .headers(headers)
                .contentType(ContentType.JSON)
                .get("/space-cluster/"+spaceCluster_Id+"/sub-root/"+spaceId+"/view")
                .then().log().all().extract().response();

        Assert.assertEquals(response.statusCode(),200);

    }

    }

