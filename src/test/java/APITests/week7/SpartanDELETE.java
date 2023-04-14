package APITests.week7;

import APITests.utilities.SpartanTestBase;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class SpartanDELETE extends SpartanTestBase {

    @DisplayName("deleteWithID")
    @Test
    public void deleteWithID() {
        //Create fake data for spartans
        Faker faker = new Faker();

        for (int i = 0; i < 90; i++) {

            int id = faker.number().numberBetween(146, 270);

                System.out.println(id+". spartan is deleted");
                //Send request body for updating
                given().accept(ContentType.JSON)
                        .contentType("application/json")
                        .and().pathParam("id", id)
                        .when().delete("/api/spartans/{id}")
                        .then().assertThat().statusCode(204);

        }


    }
}