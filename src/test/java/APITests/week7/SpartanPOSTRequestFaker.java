package APITests.week7;

import APITests.utilities.SpartanTestBase;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpartanPOSTRequestFaker extends SpartanTestBase {

    @DisplayName("POST With Map by using faker")
    @Test
    public void POSTWithMap() {
        //Create fake data for spartans
        Faker faker = new Faker();
        String name = faker.name().firstName();
        long phone= faker.number().numberBetween(100000000,99999999999L);

//Create a map to be used as a body for post request

        Map<String, Object> addSpartanWithPost=new HashMap<>();
        addSpartanWithPost.put("name",name);
        addSpartanWithPost.put("gender","Female");
        addSpartanWithPost.put("phone",phone);

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
        assertEquals(response.jsonPath().getString("data.name"), name);
        assertEquals(response.jsonPath().getString("data.gender"), "Female");
        assertEquals(response.jsonPath().getLong("data.phone"), phone);


    }
}