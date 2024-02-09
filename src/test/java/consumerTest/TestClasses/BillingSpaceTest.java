package consumerTest.TestClasses;

import consumerTest.Payloads.Payloads;
import consumerTest.Pojos.AddBillingSpace;
import consumerTest.Pojos.UpdateBillingSpace;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

    public class BillingSpaceTest {

        public String spaceClusterID;
        public String rootSpaceId;

        @Test(priority = 1)
        public void addBillingSpace(ITestContext context){

            String token = Authentication.getAccessToken();
            Map<String, String> headers = new HashMap<>();
            if (token != null) {
                headers.put("Authorization", "Bearer " + token);
            }

            AddBillingSpace payload = Payloads.getCreateBillingSpacePayloadFromPojo();

            Response response = RestAssured.given().log().all()
                    .baseUri(Authentication.setUp())
                    .headers(headers)
                    .contentType(ContentType.JSON)
                    .body(payload)
                    .post("/space-cluster/start")
                    .then().log().all().extract().response();

            Assert.assertEquals(response.statusCode(),201);

            spaceClusterID = response.jsonPath().getString("spaceClusterId");
            context.setAttribute("spaceCluster_Id", spaceClusterID);

            rootSpaceId = response.jsonPath().getString("rootSpaceId");
            context.setAttribute("rootSpace_Id", rootSpaceId);

        }

        @Test(priority = 2)
        public void viewBillingSpace(){

            String token = Authentication.getAccessToken();
            Map<String, String> headers = new HashMap<>();
            if (token != null) {
                headers.put("Authorization", "Bearer " + token);
            }

            Response response = RestAssured.given().log().all()
                    .baseUri(Authentication.setUp())
                    .headers(headers)
                    .contentType(ContentType.JSON)
                    .get("/space-cluster/"+spaceClusterID+"/list")
                    .then().log().all().extract().response();

            Assert.assertEquals(response.statusCode(),200);

        }

        @Test(priority = 3, dependsOnMethods = "addBillingSpace")
        public void updateBillingSpace(){

            String token = Authentication.getAccessToken();
            Map<String, String> headers = new HashMap<>();
            if (token != null) {
                headers.put("Authorization", "Bearer " + token);
            }

            UpdateBillingSpace payload = Payloads.getUpdateBillingSpacePayloadFromPojo();

            Response response = RestAssured.given().log().all()
                    .baseUri(Authentication.setUp())
                    .headers(headers)
                    .contentType(ContentType.JSON)
                    .body(payload)
                    .put("/space-cluster/"+spaceClusterID+"/update")
                    .then().log().all().extract().response();

            Assert.assertEquals(response.statusCode(),201);

        }

    }
