package APITests.week3;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OverallPractice {

    @BeforeAll
    public static void init() {
        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "https://api.api-ninjas.com/v1";
    }

    @DisplayName("GET request to API Ninja for Cars")
    @Test
    public void carsTest() {

        Response response = given().
                accept(ContentType.JSON).
                headers("X-Api-Key", "fTvPGrLMlIsPjpiNxn+mWQ==63ni5lWJUY33cSMk")    //X-Api-Key (required) - API Key associated with your account.
                .and().queryParam("make", "toyota")
                .and().queryParam("limit", 50)      //limit (optional) - How many results to return. Must be between 1 and 50. Default is 5.
                .when().log().all()
                .get("/cars");

        //verify status code
        assertEquals(200, response.statusCode());
        System.out.println("response.contentType() = " + response.contentType());
        assertEquals("application/json",response.contentType());

        //List of all models
        List<String> carsMap = response.jsonPath().getList("model");
        //System.out.println(carsMap);  ==> Prints only the models of the cars

        //list of maps to keep all information
        List<Map<String, String>> allModelsAndYears = new ArrayList<>();

        //number of columns
        int listSize = response.jsonPath().getList("cars").size();
        System.out.println(listSize);

        //loop through each car detail
        for (int i = 0; i < listSize; i++) {
            Map<String, String> eachCarInfo = new HashMap<>();
            eachCarInfo.put(response.jsonPath().getList("model").get(i).toString(), response.jsonPath().getList("year").get(i).toString());

            allModelsAndYears.add(eachCarInfo);
        }


        for (Map<String, String> eachModelsAndYear : allModelsAndYears) {
            System.out.println(eachModelsAndYear);
            System.out.println("----------------------------------");

        }

        response.prettyPrint();       //==> Prints all of the cars
    }

    @DisplayName("GET request to API Ninja for Cars")
    @Test
    public void carsTest2() {

        Response response = given().
                accept(ContentType.JSON).
                headers("X-Api-Key", "fTvPGrLMlIsPjpiNxn+mWQ==63ni5lWJUY33cSMk")    //X-Api-Key (required) - API Key associated with your account.
                .and().queryParam("make", "toyota")
                .and().queryParam("limit", 50)      //limit (optional) - How many results to return. Must be between 1 and 50. Default is 5.
                .when()
                .get("/cars");

        //verify status code
        assertEquals(200, response.statusCode());
        assertEquals("application/json",response.contentType());

        //Below part is about verifying headers
        System.out.println("response.AllHeaders() = " + response.headers().toString());
        Assertions.assertTrue(response.headers().hasHeaderWithName("Connection"));
        Assertions.assertTrue(response.headers().hasHeaderWithName("Access-Control-Allow-Origin"));
        System.out.println("----------------------------");
        //Get a specific header from response
        System.out.println("response.header(\"Date\") = " + response.header("Date"));
        System.out.println("response.header(\"Content-Length\") = " + response.header("Content-Length"));
        //Verify header paired infos from response
        Assertions.assertTrue(response.header("Date").contains("Fri, 31 Mar 2023"));
        Assertions.assertEquals("Miss from cloudfront",response.header("X-Cache"));

    }

    @DisplayName("GET request to API Ninja for Images")
    @Test
    public void randomImagesTest() {

        Response response = given().
                accept(ContentType.JSON).
                headers("X-Api-Key", "fTvPGrLMlIsPjpiNxn+mWQ==63ni5lWJUY33cSMk").    //X-Api-Key (required) - API Key associated with your account.
                headers("Accept","image/jpg")
                .and().queryParam("category", "city")
                .and().queryParam("limit", 50)      //limit (optional) - How many results to return. Must be between 1 and 50. Default is 5.
                .when()
                .get("/randomimage");

        //verify status code
        assertEquals(200, response.statusCode());
        assertEquals("image/jpg",response.contentType());

       response.body().peek();
    }
}
