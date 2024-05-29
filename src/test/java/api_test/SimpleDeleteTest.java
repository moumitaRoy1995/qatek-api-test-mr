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

public class SimpleDeleteTest {
    private static final Logger LOGGER = LogManager.getLogger(SimpleDeleteTest.class);

    @Test
    public void deleteSingleUser()
    {
        LOGGER.info("------API Test: Delete Single user------");

        //Specify the base URL or endpoint of the REST API
        RestAssured.baseURI = "https://reqres.in/api/users";

        //Get the RequestSpecification of the Request that you want to send to the server.
        //The server is specified by the BaseURI what we have specified in the above.
        RequestSpecification httpRequest = RestAssured.given();

        //Make a request to the server by specifying the method type.
        //This will return the response from the server and store the response in a variable.
        String id = "2";
        Response response = httpRequest.request(Method.DELETE,id);

        //Assert that the correct status is returned.
        Assert.assertEquals(response.getStatusCode(), 204);

        LOGGER.info("--------EndTest: Delete Single user--------");
    }
}
