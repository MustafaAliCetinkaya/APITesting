package APITests.week5;

import APITests.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpartanPOSTRequestWithString extends SpartanTestBase {
    @DisplayName("POST Request with String")
    @Test
    public void test1() {
        //Sending json body as string
        Response response = (Response) given().accept(ContentType.JSON)
                .contentType("application/json")
                .body("{\n" +
                        "    \"gender\":\"Male\",\n" +
                        "    \"name\":\"Pala Remzi\",\n" +
                        "    \"phone\":988776543324\n" +
                        "\n" +
                        "}")
                .when().post("/api/spartans");

        //Validations / Verify Status Code is 201
        assertEquals(response.statusCode(), 201);
        assertEquals(response.contentType(), "application/json");

        //Verify request body
        assertEquals(response.jsonPath().getString("data.name"), "Pala Remzi");
        assertEquals(response.jsonPath().getString("data.gender"), "Male");
        assertEquals(response.jsonPath().getLong("data.phone"), 988776543324L);

    }

    @DisplayName("POST With Map")
    @Test
    public void POSTWithMap() {
//Create a map to be used as a body for post request

        Map<String, Object> addSpartanWithPost=new HashMap<>();
        addSpartanWithPost.put("name","Siavash Chan");
        addSpartanWithPost.put("gender","Female");
        addSpartanWithPost.put("phone",233456677889L);

        //Sending json body as MAP
        Response response = (Response) given().accept(ContentType.JSON)
                .contentType("application/json")
                .body(addSpartanWithPost)
                .when().post("/api/spartans");

        //Validations / Verify Status Code is 201
        assertEquals(response.statusCode(), 201);
        assertEquals(response.contentType(), "application/json");

        response.prettyPrint();

        //Verify request body
        assertEquals(response.jsonPath().getString("data.name"), "Siavash Chan");
        assertEquals(response.jsonPath().getString("data.gender"), "Female");
        assertEquals(response.jsonPath().getLong("data.phone"), 233456677889L);


    }
}