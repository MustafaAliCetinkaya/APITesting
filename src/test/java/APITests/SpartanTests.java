package APITests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class SpartanTests {

    String spartanBaseURL="http://54.89.14.144:8000";
    @Test
    public void viewSpartanTest1(){
        //send a get request and save response inside the Response object
        //Right hand side is request part. We store the answer in the response.
        Response response = RestAssured.get(spartanBaseURL + "/api/spartans");//Simple string concentration for the remaining part of the URL

        //print response status code
        System.out.println(response.statusCode());

        //print response body
        response.prettyPrint();
        response.body().print();
        response.body().prettyPeek();
        System.out.println(response.body().asString());

    }
}
