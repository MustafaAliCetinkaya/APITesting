package APITests.week6;

import APITests.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
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
    @DisplayName("ConvertJsonToCollection")
    @Test
    public void test2() {
        Response response = (Response) given().accept(ContentType.JSON)
                .when().get("/api/spartans");

        //We convert json file by using as(Map.class) to Java Collections-Map
        List<Map <String,Object>>spartanList = response.body().as(List.class);  //De-Serialization (Collections)==> from Json to MAP
        System.out.println("First spartan: "+spartanList.get(0));
        System.out.println("First spartan's name: "+spartanList.get(0).get("name"));

        int counter=1;
        for (Map<String, Object> eachMap : spartanList) {
            System.out.println(counter+". spartan : "+eachMap);
            System.out.println("--------------"+counter+" .spartan is written above"+"----------------");
            counter++;
        }

    }
}