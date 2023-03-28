package APITests.week2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AutomationExercises2 {
    String automationExerciseURL = "https://automationexercise.com";

    @Test
    public void automationExercisesBrandList() {

        Response response = RestAssured.given().accept(ContentType.JSON).
                when().get(automationExerciseURL+"/api/brandsList");//Simple string concentration for the remaining part of the URL

        //Verify and print status code from response object
        Assert.assertEquals(response.statusCode(), 200);//This comes from TestNG
        response.then().assertThat().statusCode(200);//Another assertion way

        System.out.println("response.statusCode() = " + response.statusCode());

        //printing response content type from response object
        System.out.println("response.contentType() = " + response.contentType());
        response.then().assertThat().contentType("text/html; charset=utf-8");
        Assertions.assertEquals("text/html; charset=utf-8",response.contentType());//This comes from Junit5


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
    public void automationExercisesGetUserDetailByEmail() {

        Response response = RestAssured.given().accept(ContentType.JSON).
                when().get(automationExerciseURL+"/api/getUserDetailByEmail");//Simple string concentration for the remaining part of the URL

        //Verify and print status code from response object
        Assert.assertEquals(response.statusCode(), 200);//This comes from TestNG
        response.then().assertThat().statusCode(200);//Another assertion way

        System.out.println("response.statusCode() = " + response.statusCode());

        //printing response content type from response object
        System.out.println("response.contentType() = " + response.contentType());
        response.then().assertThat().contentType("text/html; charset=utf-8");
        Assertions.assertEquals("text/html; charset=utf-8",response.contentType());//This comes from Junit5


        System.out.println("----------------prettyPrint()-----------------");
        response.prettyPrint();
    }

}
