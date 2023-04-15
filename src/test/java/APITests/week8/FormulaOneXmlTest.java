package APITests.week8;

import io.restassured.path.xml.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;

public class FormulaOneXmlTest {

    @BeforeAll
    public static void setUp(){
        //http://ergast.com/api/f1/drivers/alonso
        baseURI = "http://ergast.com";
        basePath ="/api/f1";

    }

    @Test
    public void test1(){

        Response response = given()
                                 .pathParam("driver", "alonso")
                             .when()
                                     .get("/drivers/{driver}")
                             .then().statusCode(200).log().all()
                                    .extract().response();

        XmlPath xmlPath = response.xmlPath();

        //get given name
        String givenName = xmlPath.getString("MRData.DriverTable.Driver.GivenName");
        System.out.println("givenName = " + givenName);

        //get family name
        String familyName = xmlPath.getString("MRData.DriverTable.Driver.FamilyName");
        System.out.println("familyName = " + familyName);

        //get DateOfBirth
        String DateOfBirth = xmlPath.getString("MRData.DriverTable.Driver.DateOfBirth");
        System.out.println("DateOfBirth = " + DateOfBirth);

        //if you are trying to get attribute we use @ sign
        //get driverId
        String driverId = xmlPath.getString("MRData.DriverTable.Driver.@driverId");//to get attribute we use @ sign
        System.out.println("driverId = " + driverId);

        //get code
        String code = xmlPath.getString("MRData.DriverTable.Driver.@code");//to get attribute we use @ sign
        System.out.println("code = " + code);

        //get url
        String url = xmlPath.getString("MRData.DriverTable.Driver.@url");//to get attribute we use @ sign
        System.out.println("url = " + url);

    }
}