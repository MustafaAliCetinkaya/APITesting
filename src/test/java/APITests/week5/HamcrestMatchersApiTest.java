package APITests.week5;


import io.restassured.http.*;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersApiTest {
    /*
       given accept type is Json
       And path param id is 15
       When user sends a get request to spartans/{id}
       Then status code is 200
       And content type is Json
       And json data has following
           "id": 15,
           "name": "Meta",
           "gender": "Female",
           "phone": 1938695106
        */

    @DisplayName("OneSpartan with Hamcrest and chaining")
    @Test
    public void test1(){

            given().log().all().
                    accept(ContentType.JSON)
                    .and().pathParam("id",15)
            .when()
                    .get("http://3.216.30.92:8000/api/spartans/{id}")
            .then()
                    .statusCode(200)
                    .and().assertThat()//Syntactic sugar to make the codes more readable
                    .contentType("application/json")
                    .and()
                    .body("id",equalTo(15),
       "name",is("Meta"),
                               "gender",is("Female"),
                               "phone",is(1938695106))
            .log().all();
    }

    @DisplayName("CBTraining Teacher request with chaining and matchers")
    @Test
    public void teacherData(){

                    given()
                            .accept(ContentType.JSON)
                            .and()
                            .pathParam("teacherId",3)
                            .and()

                    .when()
                            .get("https://api.training.cydeo.com/teacher/{teacherId}")
                    .then()
                            .statusCode(200)
                            .and()
                            .contentType("application/json;charset=UTF-8")
                            .and()
                            .header("server",is("envoy"))
                            .and()
                            .header("date",notNullValue())
                            .and().assertThat()
                            .body("teachers[0].firstName",is("Tet"))
                            .body("teachers[0].lastName",is("DS"))
                            .body("teachers[0].gender",equalTo("Male"));

    }

    @DisplayName("GET request to teacher/all and chaining")
    @Test
    public void teachersTest(){

        //verify Alexander,Darleen,Sean inside the all teachers
        given()
                .accept(ContentType.JSON)
        .when()
                .get("https://api.training.cydeo.com/teacher/all")
        .then()
                .statusCode(200)
                .and()
                .body("teachers.firstName",hasItems("Valter","Porter","Mario"));


    }



}