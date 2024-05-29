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

public class GetTestWithQueryParameter {
    private static final Logger LOGGER = LogManager.getLogger(GetTestWithQueryParameter.class);

    @Test
    public void getAllUsersWithQueryParameter()
    {
        LOGGER.info("------API Test: Get All Users With Query Parameter-------");

        //Specify the base URL or endpoint of the REST API
        RestAssured.baseURI = "https://reqres.in/api/users";

        //Get the RequestSpecification of the Request that you want to send to the server.
        //The server is specified by the BaseURI what we have specified in the above.
        RequestSpecification httpRequest = RestAssured.given();

        //Make a request to the server by specifying the method type.
        //This will return the response from the server and store the response in a variable.
        Response response = httpRequest.queryParam("page", "2").request(Method.GET);
        LOGGER.debug(response.getBody().asPrettyString());

        //Assert that the correct status is returned.
        Assert.assertEquals(response.getStatusCode(), 200);

        JsonPath jsonPath = response.jsonPath();
        List<String> list = jsonPath.get("data.email");

        String emailId = "michael.lawson@reqres.in";
        boolean emailExist = list.contains(emailId);
        Assert.assertTrue(emailExist, emailId + "does not exist");

        LOGGER.info("--------End Test: Get All Users With Query Parameters--------");
    }
}
