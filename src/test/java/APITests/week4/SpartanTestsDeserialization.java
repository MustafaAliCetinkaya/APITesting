package APITests.week4;

import APITests.utilities.SpartanTestBase;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
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
    @Test
    public void test2GsonExample() {
        Gson gson=new Gson();

        String myJsonBody= "{id=15, phone=1938695106, name='Meta', gender='Female'}";

        //Convert from java to json body
        SpartanPojo spartanPojo=gson.fromJson(myJsonBody, SpartanPojo.class);

        System.out.println(spartanPojo);

        //Serialization from Java Object to JSON BODY
        SpartanPojo spartan =new SpartanPojo(101, 9798761225l,"Pala","Male");

        //Converting custom class to json (Serialization)
        String convertedBodyToJson= gson.toJson(spartan);
        System.out.println("convertedBodyToJson = " + convertedBodyToJson);



    }
}