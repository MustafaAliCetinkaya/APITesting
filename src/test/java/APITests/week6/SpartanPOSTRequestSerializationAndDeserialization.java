package APITests.week6;

import APITests.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpartanPOSTRequestSerializationAndDeserialization extends SpartanTestBase {
    @DisplayName("POST Request with Object")
    @Test
    public void test1() {
        //We convert Java Object to json file by passing the object inside the body. SERIALIZATION
        SpartanPojo spartanPojo = new SpartanPojo();
        spartanPojo.setGender("Female");
        spartanPojo.setName("Zeina");
        spartanPojo.setPhone(54987876543L);

        //Sending json body as OBJECT
        Response response = (Response) given().accept(ContentType.JSON)
                .contentType("application/json")
                .body(spartanPojo)
                .when().post("/api/spartans");

        //Validations / Verify Status Code is 201
        assertEquals(response.statusCode(), 201);
        assertEquals(response.contentType(), "application/json");

        //Verify request body
        assertEquals(response.jsonPath().getString("data.name"), "Zeina");
        assertEquals(response.jsonPath().getString("data.gender"), "Female");
        assertEquals(response.jsonPath().getLong("data.phone"), 54987876543L);

        response.prettyPrint();

        //GET REQUEST. We convert json file to Java Object. DESERIALIZATION
        Response response2 = given().accept(ContentType.JSON)
                .contentType("application/json")
                .pathParam("id",117)
                .when().get("/api/spartans/{id}");

        SpartanPojo spartanPojoResponse=response2.body().as(SpartanPojo.class);
        System.out.println("spartanPojoResponse = " + spartanPojoResponse);
    }
}