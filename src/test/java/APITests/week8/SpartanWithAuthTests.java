package APITests.week8;

import APITests.utilities.SpartanAuthTestBase;
import io.restassured.http.*;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;

public class SpartanWithAuthTests extends SpartanAuthTestBase {

    @DisplayName("GET /api/spartans as a public user(guest) expect 401 ")
    @Test
    public void test1(){
            given().accept(ContentType.JSON).
                    when().
            get("/api/spartans")
                    .then().statusCode(401)
                    .log().all();
//We did not introduce ourselves to the API. It does not know us.
//We should send our authorization info in the header.
    }

    @DisplayName("GET /api/spartans as admin user expect 200")
    @Test
    public void testAdmin(){
        //how to pass admin-admin as a username and password ?
        given()
                .auth().basic("admin","admin")
                .and().accept(ContentType.JSON)
                .log().all()
        .when()
                .get("/api/spartans")
        .then()
                .statusCode(200)
                .log().all();
//System knows us and since we have necessary authorization, it returns the spartans
    }

    @DisplayName("DELETE /spartans/{id} as editor user expect 403")
    @Test
    public void testEditorDelete(){
        given()
                .auth().basic("editor","editor")
                .and().accept(ContentType.JSON)
                .and().pathParam("id",30)
        .when()
                .delete("/api/spartans/{id}")
        .then()
                .statusCode(403)//We introduced ourselves to the system but still throws error.
                 .log().body();                 //Because we do not have permission to delete the spartan
    }

    /*
        As a homework,write a detailed test for Role Base Access Control(RBAC)
            in Spartan Auth app (7000)
            Admin should be able to take all CRUD-Create read update delete
            Editor should be able to take all CRUD
                other than DELETE
            User should be able to only READ data
                not update,delete,create (POST,PUT,PATCH,DELETE)
       --------------------------------------------------------
        Can guest even read data ? 401 for all

     */




}