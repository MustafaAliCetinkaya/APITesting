package APITests.week4;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.util.List;

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
                /*.and().pathParam("studentId",34)
                .when().get("student/{studentId}");*/
                .when().get("student/all");

        System.out.println("----------------studentId==34-----------------");
        List<String> wantedStudent = response.jsonPath().getList("students.findAll {it.studentId==34}.firstName");
        System.out.println(wantedStudent);

        assertEquals(200,response.statusCode());
        assertEquals("application/json;charset=UTF-8",response.header("Content-Type"));
        assertTrue(response.header("transfer-encoding").contains("chunked"));
        assertTrue(response.headers().hasHeaderWithName("date"));

        System.out.println("----------------gender==Female-----------------");
        //get me first name of students whose batch number is 22
        List<String> empNames = response.jsonPath().getList("students.findAll {it.gender==\"Female\"}.firstName");
        System.out.println(empNames);

        System.out.println("----------------Search By Email-----------------");
        List<String> wantedStudent2 = response.jsonPath().getList("students.findAll {it.firstName==\"Glen\"}.contact.emailAddress");
        System.out.println(wantedStudent2);

        List<String> wantedStudent3 = response.jsonPath().getList("students.findAll {it.contact.emailAddress==\"glc@gmail.com\"}.studentId");
        System.out.println(wantedStudent3);
    }
}