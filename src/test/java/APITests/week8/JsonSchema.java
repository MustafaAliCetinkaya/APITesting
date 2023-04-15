package APITests.week8;

import APITests.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.internal.common.classpath.ClassPathResolver;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class JsonSchema extends SpartanTestBase {
    @Test
    public void test1() {
        given().accept(ContentType.JSON)
                .and().pathParam("id",17)
                .when().get("/api/spartans/{id}")
                .then().assertThat().statusCode(200)
                .and().assertThat().body(matchesJsonSchemaInClasspath("SpartanSchema.json"));
    }

}
