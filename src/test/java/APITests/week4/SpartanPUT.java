package APITests.week4;

import APITests.utilities.SpartanTestBase;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class SpartanPUT extends SpartanTestBase {

    @DisplayName("POST (Updating data) With Map")
    @Test
    public void PUTWithMap() {
        //Create fake data for spartans
        Faker faker = new Faker();
        int id = faker.number().numberBetween(107, 117);
        String name = faker.name().firstName();

        //Using  Map for putting data
        Map<String, Object> updateSpartanWithPut = new HashMap<>();
        updateSpartanWithPut.put("name", name);
        updateSpartanWithPut.put("gender", "Female");
        updateSpartanWithPut.put("phone", 328756433412L);

        System.out.println("updateSpartanWithPut = " + updateSpartanWithPut+" updated id: "+id);

        //Send request body for updating
        given().accept(ContentType.JSON)
                .contentType("application/json")
                .and().pathParam("id", id)
                .and().body(updateSpartanWithPut)
                .when().put("/api/spartans/{id}")
                .then().assertThat().statusCode(204);

    }
}