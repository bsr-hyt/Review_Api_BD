package apiTest.day02;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static io.restassured.RestAssured.*;

public class Test_02_GetRequestWithPathMethod {

    @BeforeClass  //sadece bu classta kullanabilirsin, çünkü extent edilmedi; sadece bu classa ait.
    public void beforeClassSetUp(){
        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void testWithPathMethod() {
        /**
         *         "id": 24,
         *         "name": "mike",
         *         "email": "mike@gmail.com",
         *         "password": "$2y$10$KWJ2f3iTUFvkvzTS7/O0AOBmfwYknjscuwdA8n4c25gkzFqi9tswW",
         *         "about": "Excellent QA",
         *         "terms": "2",
         *         "date": "2022-09-12 20:50:38",
         *         "job": "SDET",
         *         "company": "Amazon",
         *         "website": "Krafttechnologie",
         *         "location": "USD",
         *         "skills": [
         *             "Cucumber",
         *             "TestNG"
         *         ],
         * */

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 24)
                .when()
                .get("/allusers/getbyid/{id}");

        //path metodu ile body'den veri alma
        System.out.println("response.body().path(\"name\").toString() = " + response.body().path("name").toString());
        System.out.println("response.path(\"id\") = " + response.path("id").toString());
        System.out.println("response.path(\"company\").toString() = " + response.path("company").toString());

        //bilgileri assert edelim.
        int id = response.path("id[0]");
        assertEquals(id,24);
        assertEquals(response.path("email[0]"),"mike@gmail.com");
        assertEquals(response.path("location[0]"),"USD");
    }

    @Test
    public void test3() {
        /**Class Task
         * Given accept type JSON
         * and Query parameter value pagesize 50
         * and Query parameter value page 1
         * When user send GET request to /allusers/alluser
         * Then response status code is 200
         * And response content type is "application/json; charset=UTF-8"
         * Verify that first id 1
         * verify that first name MercanS
         * verify that last id is 102
         * verify that last name is GHAN
         */

        Response response = given().accept(ContentType.JSON)
                .queryParam("pagesize", 50)
                .queryParam("page", 1)
//                .queryParam("name","isa")
                .when()
                .get("/allusers/alluser");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json; charset=UTF-8");

        //headers kontrol edelim
        assertEquals(response.header("Content-Type"),"application/json; charset=UTF-8");
        assertEquals(response.getHeader("Content-Type"),"application/json; charset=UTF-8");
        assertTrue(response.headers().toString().contains("Content-Type"));

        //ilk elemanı assert edelim.
        int idFirst = response.path("id[0]");
        assertEquals(idFirst,1);
        assertEquals(response.path("name[0]"),"MercanS");
        System.out.println("response.path(\"['name'][0]*************\") = " + response.path("[0]['name']")); //MercanS

        //son elemanı assert edelim.
        int lastid = response.path("id[-1]");
        assertEquals(lastid,102);
        assertEquals(response.path("name[-1]"),"GHAN");
        assertEquals(response.path("name[49]"),"GHAN");
        assertEquals(response.path("company[2]"),"Amazon");

        //3.elemanın 2. skillini assert edelim.
        System.out.println("response.path(\"skills[2]\") = " + response.path("skills[2]"));
        assertEquals(response.path("skills[2][1]"),"TestNG");

        //3.elemanın 2.educationın schoolunu alalım..
        System.out.println("response.path(\"education[2].school[1]\") = " + response.path("education[2].school[1]"));
        System.out.println("response.path(\"education[2][1].school\") = " + response.path("education[2][1].school"));
        System.out.println("response.path(\"education[2][1].school\") = " + response.path("education[2].school")); //bütün schoolları yazdırır.

        assertEquals(response.path("skills[2][1]"),"TestNG");

        //son elemanın edcation degreesini alalım.
        System.out.println("response.path(\"education[-1].degree[0]\") = " + response.path("education[-1].degree[0]"));

    }
}
