package api_test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GetTestWithPathVariable {
    //This is a GET operation with Path Variable
    private static final Logger LOGGER = LogManager.getLogger(GetTestWithPathVariable.class);

    @Test
    public void getSingleUsers()
    {
        LOGGER.info("------API Test: Get Single Users-------");

        //Specify the base URL or endpoint of the REST API
        RestAssured.baseURI = "https://reqres.in/api/users";

        //Get the RequestSpecification of the Request that you want to send to the server.
        //The server is specified by the BaseURI what we have specified in the above.
        RequestSpecification httpRequest = RestAssured.given();

        //Make a request to the server by specifying the method type.
        //This will return the response from the server and store the response in a variable.
        String id = "2";
        Response response = httpRequest.request(Method.GET,id);
        LOGGER.debug(response.getBody().asPrettyString());

        //Assert that the correct status is returned.
        Assert.assertEquals(response.getStatusCode(), 200);

        JsonPath jsonPath = response.jsonPath();
        String actualEmailId = jsonPath.get("data.email");

        String expectedEmailId = "janet.weaver@reqres.in";
        Assert.assertEquals(actualEmailId, expectedEmailId);

        LOGGER.info("--------End Test: Get Single Users--------");
    }

    @Test
    public void attemptToGetUserWithInvalidId()
    {
        LOGGER.info("------API Test: Attempt to retrieve user with invalid id-------");

        //Specify the base URL or endpoint of the REST API
        RestAssured.baseURI = "https://reqres.in/api/users";

        //Get the RequestSpecification of the Request that you want to send to the server.
        //The server is specified by the BaseURI what we have specified in the above.
        RequestSpecification httpRequest = RestAssured.given();

        //Make a request to the server by specifying the method type.
        //This will return the response from the server and store the response in a variable.
        String id = "23";
        Response response = httpRequest.request(Method.GET,id);
        LOGGER.debug(response.getBody().asPrettyString());

        //Assert that the correct status is returned.
        Assert.assertEquals(response.getStatusCode(), 404);

        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals(jsonPath.get().toString(), "{}");

        LOGGER.info("--------End Test: Attempt to retrieve user with invalid id--------");
    }


}
