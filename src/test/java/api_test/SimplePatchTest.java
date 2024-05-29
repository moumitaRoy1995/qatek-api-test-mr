package api_test;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimplePatchTest {
    private static final Logger LOGGER = LogManager.getLogger(SimplePatchTest.class);

    @Test
    public void updateSingleFieldOfUser()
    {
        LOGGER.info("------API Test: Update user's single fields-------");

        //Specify the base URL or endpoint of the REST API
        RestAssured.baseURI = "https://reqres.in/api/users";

        //Get the RequestSpecification of the Request that you want to send to the server.
        //The server is specified by the BaseURI what we have specified in the above.
        RequestSpecification httpRequest = RestAssured.given();

        Faker faker = new Faker();

        String job = faker.job().title();
        LOGGER.debug("Updated user's job: " + job);

        JSONObject reqBody = new JSONObject();
        reqBody.put("job", job);

        httpRequest.header("Content-Type", "application/json");
        httpRequest.body(reqBody.toJSONString());

        //Make a request to the server by specifying the method type.
        //This will return the response from the server and store the response in a variable.
        String id = "2";
        Response response = httpRequest.request(Method.PATCH,id);
        LOGGER.debug(response.getBody().asPrettyString());
        //Assert that the correct status is returned.
        Assert.assertEquals(response.getStatusCode(), 200);

        JsonPath jsonPath = response.jsonPath();
        String actualJob = jsonPath.get("job");

        Assert.assertEquals(actualJob, job);

        LOGGER.info("--------End Test: Update user's single fields--------");
    }
}
