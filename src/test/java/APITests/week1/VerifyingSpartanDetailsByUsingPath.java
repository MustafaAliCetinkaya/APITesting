package APITests.week1;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VerifyingSpartanDetailsByUsingPath {
    @BeforeClass
    public void setUpClass() {
        RestAssured.baseURI = "http://54.89.14.144:8000";
    }

    @Test
    public void spartanTestsVerifyingSpartanDetails() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id", 28)
                .when().get("/api/spartans/{id}");

        //Verify and print status code from response object
        Assert.assertEquals(response.statusCode(), 200);
        System.out.println("response.statusCode() = " + response.statusCode());

        //printing response content type from response object
        Assert.assertEquals(response.contentType(), "application/json");
        System.out.println("response.contentType() = " + response.contentType());

        System.out.println("path(\"id\") = " + response.body().path("id").toString());
        System.out.println("path(\"name\") = " + response.body().path("name").toString());
        System.out.println("path(\"gender\") = " + response.body().path("gender").toString());
        System.out.println("path(\"phone\") = " + response.body().path("phone").toString());

        System.out.println("-----------------------");

        int id = response.path("id");
        long phone = response.path("phone");
        String name = response.body().path("name");
        String gender = response.body().path("gender");

        System.out.println("id = " + id);
        System.out.println("phone = " + phone);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);

        //Verify Json keys and values
        Assert.assertEquals(id, 28);
        Assert.assertEquals(gender, "Male");
        Assert.assertEquals(name, "Woodrow");
        Assert.assertEquals(phone, 7411659821L);

    }

    @Test
    public void spartanTestsVerifyingSpartanDetails2() {
        Response response = RestAssured.get("/api/spartans");

        //Get details by using index like in array
        int firstId=response.path("id[0]");
        String firstName=response.path("name[0]");
        String firstGender=response.path("gender[0]");
        long firstPhone=response.path("phone[0]");

        System.out.println("firstPhone = " + firstPhone);
        System.out.println("firstGender = " + firstGender);
        System.out.println("firstName = " + firstName);
        System.out.println("firstId = " + firstId);

        //Get lastName by using index. Index should be ( -1 )
        String lastName=response.path("name[-1]");
        System.out.println("lastName = " + lastName);

        //Get all names only
        List<String> allNames = response.path("name");
        System.out.println("allNames = " + allNames);
        System.out.println("Number of the allNames = " + allNames.size());

        //Get all names only
        List<Object> allPhoneNumbers = response.path("phone");
        for (Object eachPhoneNumber : allPhoneNumbers) {
            System.out.println(eachPhoneNumber);
        }

        System.out.println("-----------------------------");
        //Dynamic way to write all details

        for (int i = 0; i < allNames.size(); i++) {
            int id=response.path("id["+i+"]");
            String name=response.path("name["+i+"]");
            String gender=response.path("gender["+i+"]");
            String phone=""+response.path("phone["+i+"]");

            System.out.println(i+1+". Id = " + id);
            System.out.println(i+1+". Gender = " + gender);
            System.out.println(i+1+". Name = " + name);
            System.out.println(i+1+". Phone = " + phone);

            System.out.println("-----------------------------");

        }
    }
}
