package APITests.week8;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.get;

public class govXmlTest {


    @Test
    public void test1() {
        //send a get request to
        //https://data.ct.gov/api/views/qm34-pq7e/rows.xml
        //get all the years
        //get all unknowns
        //get 2005 other
        //get 2007 _address
        Response response = get("https://data.ct.gov/api/views/yevq-tkwb/columns.xml")
                .then().statusCode(200).extract().response();

        //response.prettyPrint();
        XmlPath xmlPath = response.xmlPath();

        //get all the IDs
        String id = xmlPath.getString("response.viewColumn.@id");//to get attribute we use @ sign
        System.out.println("IDs = " + id);

        //get all the names
        String name = xmlPath.getString("response.viewColumn.@name");//to get attribute we use @ sign
        System.out.println("names = " + name);

        //get all smallest
        List<Integer> smallest = xmlPath.getList("response.viewColumn.cachedContents.smallest");
        System.out.println("fieldNames = " + smallest);

        //get all cardinality
        List<Integer> cardinality = xmlPath.getList("response.viewColumn.cachedContents.cardinality");
        System.out.println("cardinality = " + cardinality);


    }
}