package APITests.week4;

import APITests.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        assertEquals(response.statusCode(),201);
        assertEquals(response.contentType(),"application/json");

        //Verify request body
        assertEquals(response.jsonPath().getString("data.name"),"Pala Remzi");
        assertEquals(response.jsonPath().getString("data.gender"),"Male");
        assertEquals(response.jsonPath().getLong("data.phone"),988776543324L);

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