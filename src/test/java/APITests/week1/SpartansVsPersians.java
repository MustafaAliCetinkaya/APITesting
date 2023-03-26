package APITests.week1;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SpartansVsPersians {
    @BeforeClass
    public void setUpClass(){
        RestAssured.baseURI ="http://54.89.14.144:8000";
    }

    @Test
    public void spartanTestsWithPositivePathParameters() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id",18)
                .when().get("/api/spartans/{id}");

        //Verify and print status code from response object
        Assert.assertEquals(response.statusCode(),200);
        System.out.println("response.statusCode() = " + response.statusCode());

        //printing response content type from response object
        Assert.assertEquals(response.contentType(),"application/json");
        System.out.println("response.contentType() = " + response.contentType());

        //Verify name whether exists in the response
        Assert.assertTrue(response.body().asString().contains("Allen"));

        response.body().prettyPrint();
    }

    @Test
    public void spartanTestsWithNegativePathParameters() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id",1818)
                .when().get("/api/spartans/{id}");

        //Verify and print status code from response object
        Assert.assertEquals(response.statusCode(),404);
        System.out.println("response.statusCode() = " + response.statusCode());

        //printing response content type from response object
        Assert.assertEquals(response.contentType(),"application/json");
        System.out.println("response.contentType() = " + response.contentType());

        //Verify name whether exists in the response
        Assert.assertTrue(response.body().asString().contains("Not Found"));

        response.body().prettyPrint();
    }

    @Test
    public void queryParamOnlyFemaleWarriors() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().queryParam("gender","Female")
                .and().queryParam("nameContains","m")
                .when().get("/api/spartans/search");

        //Verify and print status code from response object
        Assert.assertEquals(response.statusCode(),200);
        System.out.println("response.statusCode() = " + response.statusCode());

        //printing response content type from response object
        Assert.assertEquals(response.contentType(),"application/json");
        System.out.println("response.contentType() = " + response.contentType());

        //Verify female whether exists in the response
        Assert.assertTrue(response.body().asString().contains("Female"));

        //Verify male does not exist in the response
        Assert.assertFalse(response.body().asString().contains("Male"));

        //Verify Melania whether exists in the response
        Assert.assertTrue(response.body().asString().contains("Melania"));

        response.body().prettyPrint();
    }

    @Test
    public void mapQueryParamOnlyFemaleWarriors() {
        //Creating map for query
        Map<String, Object>onlyFemaleWarriorsMap=new HashMap<>();
        onlyFemaleWarriorsMap.put("gender","Female");
        onlyFemaleWarriorsMap.put("nameContains","j");

        //Send request
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().queryParams(onlyFemaleWarriorsMap)
                .when().get("/api/spartans/search");

        //Verify and print status code from response object
        Assert.assertEquals(response.statusCode(),200);
        System.out.println("response.statusCode() = " + response.statusCode());

        //printing response content type from response object
        Assert.assertEquals(response.contentType(),"application/json");
        System.out.println("response.contentType() = " + response.contentType());

        //Verify female whether exists in the response
        Assert.assertTrue(response.body().asString().contains("Female"));

        //Verify male does not exist in the response
        Assert.assertFalse(response.body().asString().contains("Male"));

        //Verify Janette whether exists in the response
        Assert.assertTrue(response.body().asString().contains("Janette"));

        response.body().prettyPrint();
    }
}
