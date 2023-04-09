package APITests.week7.lectureCodes;


import APITests.Pojo.Spartan;
import APITests.utilities.SpartanTestBase;
import io.restassured.http.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
public class SpartanPostRequestDemo extends SpartanTestBase {

       /*
    Given accept type and Content type is JSON
    And request json body is:
    {
      "gender":"Male",
      "name":"Severus",
      "phone":8877445596
   }
    When user sends POST request to '/api/spartans'
    Then status code 201
    And content type should be application/json
    And json payload/response/body should contain:
    "A Spartan is Born!" message
    and same data what is posted
 */
    @Test
    public void postMethod1(){

        String requestJsonBody = "{\"gender\":\"Male\",\n" +
                "\"name\":\"Keverus\",\n" +
                "\"phone\":8877445596}";

        Response response = given().accept(ContentType.JSON).and() //what we are asking from api which is JSON response
                .contentType(ContentType.JSON) //what we are sending to api, which is JSON also
                .body(requestJsonBody)
                .when()
                .post("/api/spartans");

        response.prettyPrint();

        //verify status code
        assertThat(response.statusCode(),is(201));
        assertThat(response.contentType(),is("application/json"));

        String expectedResponseMessage = "A Spartan is Born!";
        assertThat(response.path("success"),is(expectedResponseMessage));
        assertThat(response.path("data.name"),is("Keverus"));
        assertThat(response.path("data.gender"),is("Male"));
        assertThat(response.path("data.phone"),is(8877445596L));

    }


    @DisplayName("POST with Map to JSON")
    @Test
    public void postMethod2(){

        //create a map to keep request json body information
        Map<String,Object> requestJsonMap = new LinkedHashMap<>();
        requestJsonMap.put("name","Zeverus");
        requestJsonMap.put("gender","Male");
        requestJsonMap.put("phone",8877445596L);


        Response response = given().accept(ContentType.JSON).and() //what we are asking from api which is JSON response
                .contentType(ContentType.JSON) //what we are sending to api, which is JSON also
                .body(requestJsonMap).log().all()
                .when()
                .post("/api/spartans");

        //verify status code
        assertThat(response.statusCode(),is(201));
        assertThat(response.contentType(),is("application/json"));

        String expectedResponseMessage = "A Spartan is Born!";
        assertThat(response.path("success"),is(expectedResponseMessage));
        assertThat(response.path("data.name"),is("Zeverus"));
        assertThat(response.path("data.gender"),is("Male"));
        assertThat(response.path("data.phone"),is(8877445596L));

        response.prettyPrint();
    }

    @DisplayName("POST with Map to Spartan Class")
    @Test
    public void postMethod3(){
        //create one object from your pojo, send it as a JSON
       Spartan spartan = new Spartan();
        spartan.setName("Preverus");
        spartan.setGender("Male");
        spartan.setPhone(8877445596L);

        System.out.println("spartan = " + spartan);

        Response response = given().accept(ContentType.JSON).and() //what we are asking from api which is JSON response
                .contentType(ContentType.JSON) //what we are sending to api, which is JSON also
                .body(spartan).log().all()
                .when()
                .post("/api/spartans");

        //verify status code
        assertThat(response.statusCode(),is(201));
        assertThat(response.contentType(),is("application/json"));

        String expectedResponseMessage = "A Spartan is Born!";
        assertThat(response.path("success"),is(expectedResponseMessage));
        assertThat(response.path("data.name"),is("Preverus"));
        assertThat(response.path("data.gender"),is("Male"));
        assertThat(response.path("data.phone"),is(8877445596L));

        response.prettyPrint();


    }

    @DisplayName("POST with Map to Spartan Class")
    @Test
    public void postMethod4(){
        //this example we implement serialization with creating spartan object sending as a request body
        //also implemented deserialization getting the id,sending get request and saving that body as a response

        //create one object from your pojo, send it as a JSON
        Spartan spartan = new Spartan();
        spartan.setName("Odipous Bruce");
        spartan.setGender("Male");
        spartan.setPhone(8877445596L);

        System.out.println("spartan = " + spartan);
        String expectedResponseMessage = "A Spartan is Born!";

        int idFromPost = given().accept(ContentType.JSON).and() //what we are asking from api which is JSON response
                .contentType(ContentType.JSON) //what we are sending to api, which is JSON also
                .body(spartan).log().all()
                .when()
                .post("/api/spartans")
                .then()
                .statusCode(201)
                .contentType("application/json")
                .body("success", is(expectedResponseMessage))
                .extract().jsonPath().getInt("data.id");//Get the id of newly created spartan

        System.out.println("idFromPost = " + idFromPost);

        //send a get request to id
        Spartan spartanPosted = given().accept(ContentType.JSON)
                .and().pathParam("id", idFromPost)//Get the info of newly created spartan
                .when().get("/api/spartans/{id}")
                .then().statusCode(200).log().all().extract().as(Spartan.class);//Get and convert to class object

        //Compare whether sent and returned info is same
        assertThat(spartanPosted.getName(),is(spartan.getName()));
        assertThat(spartanPosted.getGender(),is(spartan.getGender()));
        assertThat(spartanPosted.getPhone(),is(spartan.getPhone()));
        assertThat(spartanPosted.getId(),is(idFromPost));

    }


}