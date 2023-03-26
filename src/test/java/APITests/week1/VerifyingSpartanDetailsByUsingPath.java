package APITests.week1;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class VerifyingSpartanDetailsByUsingPath {
    @BeforeClass
    public void setUpClass(){
        RestAssured.baseURI ="http://54.89.14.144:8000";
    }

    @Test
    public void spartanTestsVerifyingSpartanDetails() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id",28)
                .when().get("/api/spartans/{id}");

        //Verify and print status code from response object
        Assert.assertEquals(response.statusCode(),200);
        System.out.println("response.statusCode() = " + response.statusCode());

        //printing response content type from response object
        Assert.assertEquals(response.contentType(),"application/json");
        System.out.println("response.contentType() = " + response.contentType());

        System.out.println("path(\"id\") = " + response.body().path("id").toString());
        System.out.println("path(\"name\") = " + response.body().path("name").toString());
        System.out.println("path(\"gender\") = " + response.body().path("gender").toString());
        System.out.println("path(\"phone\") = " + response.body().path("phone").toString());

        System.out.println("-----------------------");

        int id=response.path("id");
        long phone=response.path("phone");
        String name=response.body().path("name");
        String gender=response.body().path("gender");

        System.out.println("id = " + id);
        System.out.println("phone = " + phone);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);

        //Verify Json keys and values
        Assert.assertEquals(id,28);
        Assert.assertEquals(gender,"Male");
        Assert.assertEquals(name,"Woodrow");
        Assert.assertEquals(phone,7411659821L);

    }


}
