package APITests.week4;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CBTrainingApiWithJsonPath {

    @BeforeAll
    public static void init(){
        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "https://api.training.cydeo.com";

    }

    @DisplayName("GET Request to individual student")
    @Test
    public void test1(){
        //send a get request to student id 23401 as a path parameter and accept header application/json
        //verify status code=200 /content type=application/json;charset=UTF-8 /Content-Encoding = gzip
        //verify Date header exists
        //assert that
            /*
                firstName Vera
                batch 14
                section 12
                emailAddress aaa@gmail.com
                companyName Cybertek
                state IL
                zipCode 60606

                using JsonPath
             */

        Response response= given().accept(ContentType.JSON)
                .and().pathParam("studentId",34)
                .when().get("student/{studentId}");

        response.body().prettyPrint();

        assertEquals(200,response.statusCode());
        assertEquals("application/json;charset=UTF-8",response.header("Content-Type"));
        assertTrue(response.header("transfer-encoding").contains("chunked"));
        assertTrue(response.headers().hasHeaderWithName("date"));

    }
}