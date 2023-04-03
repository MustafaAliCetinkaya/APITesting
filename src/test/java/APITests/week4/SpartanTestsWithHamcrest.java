package APITests.week4;

import APITests.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class SpartanTestsWithHamcrest extends SpartanTestBase {
    @DisplayName("GET one spartan with Hamcrest Method")
    @Test
    public void test1() {
        //Request part starts
        given().accept(ContentType.JSON)
                .and().pathParam("id", 15)
                .when().get("/api/spartans/{id}")
                //response part starts
                .then().statusCode(200).and()
                .assertThat().contentType("application/json");

    }


@Test
    public void test2() {
        //Request part starts
        given().accept(ContentType.JSON)
                .and().pathParam("id", 15)
                .when().get("/api/spartans/{id}")
                //response part starts
                .then().statusCode(200).and()
                .assertThat().contentType("application/json").and()
                .assertThat().body("id", Matchers.equalTo(15),
                        "name",Matchers.equalTo("Meta"),
                        "gender", Matchers.equalTo("Female"),
                        "phone",Matchers.equalTo(1938695106));
    }
}