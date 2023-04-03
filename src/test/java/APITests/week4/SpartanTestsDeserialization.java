package APITests.week4;

import APITests.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class SpartanTestsDeserialization extends SpartanTestBase {
    @DisplayName("GET one spartan with POJO")
    @Test
    public void test1() {
        //Request part starts
       Response response= given().accept(ContentType.JSON)
                .and().pathParam("id", 15)
                .when().get("/api/spartans/{id}");

        //We convert json file by using as(SpartanPojo.class) to Java Object
        SpartanPojo spartanPojo=response.body().as(SpartanPojo.class);
        System.out.println("spartanPojo = " + spartanPojo);

        Assert.assertEquals(spartanPojo.getGender(),"Female");
        Assert.assertEquals(spartanPojo.getId(),15);
        Assert.assertEquals(spartanPojo.getPhone(),1938695106);
        Assert.assertEquals(spartanPojo.getName(),"Meta");

    }
}