package APITests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SpartanTests {

    String spartanBaseURL="http://54.89.14.144:8000";
    @Test
    public void viewSpartanTest1(){
        //send a get request and save response inside the Response object
        //Right hand side is request part. We store the answer in the response.
        Response response = RestAssured.get(spartanBaseURL + "/api/spartans");//Simple string concentration for the remaining part of the URL

        //print response status code. It must be 200
        System.out.println(response.statusCode());

        //print different responses
        response.prettyPrint();
        response.body().print();
        response.body().prettyPeek();
        System.out.println(response.body().asString());

    }

    @Test
    public void viewSpartanTest2(){
        Response response = RestAssured.get(spartanBaseURL + "/api/spartans");
        Assert.assertEquals(response.statusCode(),200);

        //Verify below written names exist in the response
        Assert.assertTrue(response.body().asString().contains("Allen"));
        Assert.assertTrue(response.body().asString().contains("Rodolfo"));
        Assert.assertTrue(response.body().asString().contains("Florrie"));
        Assert.assertTrue(response.body().asString().contains("Sinclair"));
    }

    @Test
    public void viewSpartanTest3(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(spartanBaseURL + "/api/spartans");

        //Verify and print status code from response object
        Assert.assertEquals(response.statusCode(),200);
        System.out.println("response.statusCode() = " + response.statusCode());

        //printing response content type from response object
        Assert.assertEquals(response.contentType(),"application/json");
        System.out.println("response.contentType() = " + response.contentType());
    }
}
