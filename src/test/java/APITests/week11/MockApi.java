package APITests.week11;

import io.restassured.http.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class MockApi {


    //https://e787164d-adbd-474e-8c98-6796a1e3af70.mock.pstmn.io


    @Test
    public void test1(){

        String response=given().baseUri("https://895b917c-52a5-4a5f-b2ee-7c68ea688fbb.mock.pstmn.io")
                .accept(ContentType.JSON)
        .when()
                .get("/customer")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstName",is("John"))
                        .extract().body().asPrettyString();

        System.out.println(response);

    }

    @Test
    public void test2(){

        given().baseUri("https://e787164d-adbd-474e-8c98-6796a1e3af70.mock.pstmn.io")
                .accept(ContentType.JSON)
                .when()
                .get("/employees")
                .prettyPrint();

    }

}