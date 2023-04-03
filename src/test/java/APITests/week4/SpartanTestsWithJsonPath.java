package APITests.week4;

import APITests.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpartanTestsWithJsonPath extends SpartanTestBase {
    @DisplayName("GET one spartan with JsonPath Method")
    @Test
    public void test1() {
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 11)
                .when().get("/api/spartans/{id}");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());

        //How to read value with path() parameter
        //Below written method is done before

        int id = response.path("id");
        String name = response.path("name");
        String gender = response.path("gender");
        long phone = response.path("phone");
        System.out.println("-------------Path()---------------");
        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

        //How to read values from JsonPath()
        JsonPath jsonPath=response.jsonPath();
        int id1 = jsonPath.getInt("id");
        long phone1 = jsonPath.getLong("phone");
        String name1= jsonPath.getString("name");
        String gender1= jsonPath.getString("gender");
        System.out.println("-------------JsonPath()---------------");
        System.out.println("id1 = " + id);
        System.out.println("name1 = " + name);
        System.out.println("gender1 = " + gender);
        System.out.println("phone1 = " + phone);

    }

}