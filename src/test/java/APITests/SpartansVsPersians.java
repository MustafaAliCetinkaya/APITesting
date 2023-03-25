package APITests;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class SpartansVsPersians {
    @BeforeClass
    public void setUpClass(){
        RestAssured.baseURI ="http://54.89.14.144:8000";
    }


}
