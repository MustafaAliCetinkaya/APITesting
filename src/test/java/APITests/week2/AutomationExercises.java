package APITests.week2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class AutomationExercises {
    @BeforeClass
    public void setUpClass(){
        RestAssured.baseURI ="https://automationexercise.com";
    }

    @Test
    public void automationExercises() {
        //send a get request and save response inside the Response object
        //Right hand side is request part. We store the answer in the response.
        Response response = RestAssured.get("/api/productsList");//Simple string concentration for the remaining part of the URL

        //Verify and print status code from response object
        Assert.assertEquals(response.statusCode(),200);
        System.out.println("response.statusCode() = " + response.statusCode());

        //printing response content type from response object
        System.out.println("response.contentType() = " + response.contentType());

        //print different responses
        response.prettyPrint();
        System.out.println("----------------prettyPrint()-----------------");
        response.body().print();
        System.out.println("----------------body().print()-----------------");
        response.body().prettyPeek();
        System.out.println("----------------body().prettyPeek()-----------------");
        System.out.println(response.body().asString());
        System.out.println("----------------response.body().asString()-----------------");
    }
    @Test
    public void automationExercisesWithPositivePathParameters() {

        Response response = RestAssured.get("/api/productsList");
        response.jsonPath().getList("products.id");

        System.out.println("responseAllItemsIDList = " + response.jsonPath().getList("products.id"));
        System.out.println("responseAllItemsNameList = " + response.jsonPath().getList("products.name"));

        Assert.assertEquals(response.jsonPath().getList("products.id").size(),response.jsonPath().getList("products.name").size());

    }

}
