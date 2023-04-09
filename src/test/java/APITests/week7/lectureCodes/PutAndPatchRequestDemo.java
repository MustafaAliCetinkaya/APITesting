package APITests.week7.lectureCodes;

import APITests.Pojo.Spartan;
import APITests.utilities.SpartanTestBase;
import io.restassured.http.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PutAndPatchRequestDemo extends SpartanTestBase {


    @DisplayName("PUT request to one spartan for update with Map")
    @Test
    public void PUTRequest(){
        //just like post request we have different options to send body, we will go with map
        Map<String,Object> putRequestMap = new LinkedHashMap<>();
        putRequestMap.put("name","Dolgorukov");
        putRequestMap.put("gender","Male");
        putRequestMap.put("phone",8811111111L);

        given().contentType(ContentType.JSON) //hey api I am sending JSON body. We only send, do not get anything
                .body(putRequestMap).log().body()
                .and().pathParam("id",109)
                .when().put("/api/spartans/{id}")
                .then()
                .assertThat().statusCode(204);

        //send a GET request after update, make sure updated field changed, or the new info matching
        //with requestBody that we send

        //send a get request to id
        Spartan spartanPosted = given().accept(ContentType.JSON)
                .and().pathParam("id", 109)//Get the info of newly created spartan
                .when().get("/api/spartans/{id}")
                .then().statusCode(200).
                extract().as(Spartan.class);//Get and convert to class object

        //Compare whether sent and returned info is same
        assertThat(spartanPosted.getName(),is(putRequestMap.get("name")));
        assertThat(spartanPosted.getPhone(),is(putRequestMap.get("phone")));
        assertThat(spartanPosted.getGender(),is(putRequestMap.get("gender")));
    }

    @DisplayName("PATCH request to one spartan for partial update with Map")
    @Test
    public void PATCHRequest(){
        //just like post request we have different options to send body, we will go with map
        Map<String,Object> putRequestMap = new LinkedHashMap<>();
        putRequestMap.put("phone",8811111111L);
        putRequestMap.put("name","Coterimlous");

        given().contentType("application/json") //hey api I am sending JSON body
                .body(putRequestMap).log().body()
                .and().pathParam("id",119)
                .when().patch("/api/spartans/{id}")
                .then()
                .statusCode(204);

        //send a GET request after update, make sure updated field changed, or the new info matching
        //with requestBody that we send

        //send a get request to id
        Spartan spartanPosted = given().accept(ContentType.JSON)
                .and().pathParam("id", 119)//Get the info of newly created spartan
                .when().get("/api/spartans/{id}")
                .then().statusCode(200).
                extract().as(Spartan.class);//Get and convert to class object

        //Compare whether sent and returned info is same
        assertThat(spartanPosted.getName(),is(putRequestMap.get("name")));
        assertThat(spartanPosted.getPhone(),is(putRequestMap.get("phone")));

    }

    @DisplayName("DELETE one spartan")
    @Test
    public void deleteSpartan(){
        int idToDelete= 100;


            given().pathParam("id",idToDelete)
                    .when().delete("/api/spartans/{id}")
                    .then().statusCode(204);

            //send a get request after you delete make sure you are getting 404

    }


}