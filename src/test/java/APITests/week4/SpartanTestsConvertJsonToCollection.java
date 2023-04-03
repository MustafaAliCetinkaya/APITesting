package APITests.week4;

import APITests.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpartanTestsConvertJsonToCollection extends SpartanTestBase {
    @DisplayName("ConvertJsonToCollection")
    @Test
    public void test1() {
        Response response = (Response) given().accept(ContentType.JSON)
                .and().pathParam("id", 11)
                .when().get("/api/spartans/{id}");

        //We convert json file by using as(Map.class) to Java Collections-Map
        Map <String,Object>spartanMap = response.body().as(Map.class);  //De-Serialization (Collections)==> from Json to MAP
        System.out.println(spartanMap);

        //Verify expected values in map
        assertEquals("Nona",spartanMap.get("name"));
        assertEquals("Female",spartanMap.get("gender"));
        assertEquals(7.959094216E9,spartanMap.get("phone"));

    }
}