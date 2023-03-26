package APITests.week1;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SpartansVsPersians {
    @BeforeClass
    public void setUpClass(){
        RestAssured.baseURI ="http://54.89.14.144:8000";
    }

    @Test
    public void spartanTestsWithParameters1() {
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
    }
}
