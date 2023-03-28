package APITests.week2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AutomationExercises1 {
    @BeforeClass
    public void setUpClass() {
        RestAssured.baseURI = "https://automationexercise.com";
    }

    @Test
    public void automationExercises() {
        //send a get request and save response inside the Response object
        //Right hand side is request part. We store the answer in the response.
        Response response = RestAssured.get("/api/productsList");//Simple string concentration for the remaining part of the URL

        //Verify and print status code from response object
        Assert.assertEquals(response.statusCode(), 200);//This comes from TestNG
        response.then().assertThat().statusCode(200);//Another assertion way

        System.out.println("response.statusCode() = " + response.statusCode());

        //printing response content type from response object
        System.out.println("response.contentType() = " + response.contentType());
        response.then().assertThat().contentType("text/html; charset=utf-8");
        Assertions.assertEquals("text/html; charset=utf-8",response.contentType());//This comes from Junit5


        //print different responses
        response.prettyPrint();
        System.out.println("----------------prettyPrint()-----------------");
        response.body().print();
        System.out.println("----------------body().print()-----------------");
        response.body().prettyPeek();
        System.out.println("----------------body().prettyPeek()-----------------");
        System.out.println(response.body().asString());
        System.out.println("----------------response.body().asString()-----------------");
    }

    @Test
    public void automationExercisesStoreDetailsInMAp() {

        Response response = RestAssured.get("/api/productsList");
        response.jsonPath().getList("products.id");

        System.out.println("responseAllItemsIDList = " + response.jsonPath().getList("products.id"));

        System.out.println("----------------------------------");

        System.out.println("responseAllItemsNameList = " + response.jsonPath().getList("products.name"));

        Assert.assertEquals(response.jsonPath().getList("products.id").size(), response.jsonPath().getList("products.name").size());

        System.out.println("----------------------------------");

        //list of maps to keep all information
        List<Map<String, Object>> queryData = new ArrayList<>();

        //number of columns
        int listSize = response.jsonPath().getList("products.id").size();

        //loop through each row
        for (int i = 1; i <= listSize; i++) {
            if (i != 34) {//34. item does not exist

                Map<String, Object> eachItemDetails = new LinkedHashMap<>();

                eachItemDetails.put("id", response.jsonPath().getList("products.id").get(i));
                eachItemDetails.put("name", response.jsonPath().getList("products.name").get(i));
                eachItemDetails.put("price", response.jsonPath().getList("products.price").get(i));
                eachItemDetails.put("brand", response.jsonPath().getList("products.brand").get(i));
                eachItemDetails.put("category", response.jsonPath().getList("products.category").get(i));
                eachItemDetails.put("category", response.jsonPath().getList("products.category").get(i));

                //add ready current map details to the list
                queryData.add(eachItemDetails);
            }
        }

        for (Map<String, Object> eachItemDetails : queryData) {
            System.out.println(eachItemDetails);
            System.out.println("----------------------------------");
        }
    }

}
