package APITests.week3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class AutomationExercises3 {
    @BeforeClass
    public void setUpClass() {
        RestAssured.baseURI = "https://automationexercise.com";
    }

    @Test
    public void automationExercisesVerifyHeaders() {
        //send a get request and save response inside the Response object
        //Right hand side is request part. We store the answer in the response.
        Response response = RestAssured.get("/api/productsList");//Simple string concentration for the remaining part of the URL

        //Verify and print status code from response object
        Assert.assertEquals(response.statusCode(), 200);//This comes from TestNG
        response.then().assertThat().statusCode(200);//Another assertion way

        System.out.println("response.statusCode() = " + response.statusCode());

        //printing response content type from response object
        System.out.println("response.contentType() = " + response.contentType());
        response.then().assertThat().contentType("text/html; charset=utf-8");//Comes from restAssured
        Assertions.assertEquals("text/html; charset=utf-8",response.contentType());//This comes from Junit5
        System.out.println("----------------------------");
        //Below part is about verifying headers
        System.out.println("response.headers().toString() = " + response.headers().toString());
        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));
        Assertions.assertTrue(response.headers().hasHeaderWithName("NEL"));
        Assertions.assertTrue(response.headers().hasHeaderWithName("Server"));
        Assertions.assertTrue(response.headers().hasHeaderWithName("CF-RAY"));
        Assertions.assertTrue(response.headers().hasHeaderWithName("status"));
        System.out.println("----------------------------");
        //Get a specific header from response
        System.out.println("response.header(\"Date\") = " + response.header("Date"));
        System.out.println("response.header(\"NEL\") = " + response.header("NEL"));
        System.out.println("response.header(\"Server\") = " + response.header("Server"));
        System.out.println("response.header(\"status\") = " + response.header("status"));
        System.out.println("----------------------------");
        //Verify header paired infos from response
        Assertions.assertTrue(response.header("Date").contains("Thu, 30 Mar 2023"));
        Assertions.assertEquals("{\"success_fraction\":0,\"report_to\":\"cf-nel\",\"max_age\":604800}",response.header("NEL"));
        Assertions.assertEquals("cloudflare",response.header("Server"));
        Assertions.assertEquals("200 OK",response.header("status"));
        //Verify body contains necessary info from response
        Assertions.assertTrue(response.body().asString().contains("Blue Top"));
        Assertions.assertTrue(response.body().asString().contains("Men Tshirt"));
        Assertions.assertTrue(response.body().asString().contains("Summer White Top"));
        Assertions.assertTrue(response.body().asString().contains("Sleeves Printed Top - White"));
        Assertions.assertTrue(response.body().asString().contains("Printed Off Shoulder Top - White"));

    }
    
}
