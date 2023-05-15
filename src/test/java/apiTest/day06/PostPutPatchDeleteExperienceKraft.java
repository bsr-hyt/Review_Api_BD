package apiTest.day06;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;

public class PostPutPatchDeleteExperienceKraft {

    String email = "Charity.Fritsch23@gmail.com";
    String password = "123467Zzz";

    int experienceId;

    @BeforeClass
    public void beforeClassSetUp() {
        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test (priority = 0)
    public void addNewExperienceWithPost() {
        /**
         * HTTP method (GET, POST, PATCH vb)
         * Base Url
         * EndPoint
         * Headers (Gerekiyorsa)
         * Parameters Path or Query (Gerekiyorsa)
         * Body (POST, PUT, PATCH metodları için zorunludur.)
         * Token - Authorizaiton (Gerekiyosa)
         * */

        String requestBody = "{\n" +
                "  \"job\": \"Math Teacher\",\n" +
                "  \"company\": \"Kraft Techex\",\n" +
                "  \"location\": \"USA\",\n" +
                "  \"fromdate\": \"2019-01-01\",\n" +
                "  \"todate\": \"2020-01-01\",\n" +
                "  \"current\": \"false\",\n" +
                "  \"description\": \"Description\"\n" +
                "}";

        Response response = given().accept(ContentType.JSON)
                .and()
                .body(requestBody)
                .and()
                .headers("token", Authorization.getToken(email, password))
                .when()
                .post("/experience/add")
                .prettyPeek();

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=UTF-8");

        experienceId = response.path("id");
        System.out.println("experienceId = " + experienceId);

    }

    @Test (priority = 1)
    public void updateWithPutMethod(){
        String requestBody = "{\n" +
                "  \"job\": \"Geography Teacher\",\n" +
                "  \"company\": \"Kraft Techex\",\n" +
                "  \"location\": \"USA\",\n" +
                "  \"fromdate\": \"2019-01-01\",\n" +
                "  \"todate\": \"2020-01-01\",\n" +
                "  \"current\": \"false\",\n" +
                "  \"description\": \"Description\"\n" +
                "}";

        //experienceId = 1062

        Response response = given().accept(ContentType.JSON)
                .and()
                .body(requestBody)
                .and()
                .headers(Authorization.getTokenWithMap(email, password))
                .and()
                .queryParam("id", experienceId)
                .when()
                .put("/experience/updateput")
                .prettyPeek();

        Assert.assertEquals(response.statusCode(),200);
    }

    @Test (priority = 2)
    public void updateWithPatchMethod(){
        String requestBody = "{\n" +
                "  \"job\": \"Biology Teacher\"\n}";

        //experienceId = 1062

        Response response = given().accept(ContentType.JSON)
                .and()
                .body(requestBody)
                .and()
                .headers(Authorization.getTokenWithMap(email, password))
                .and()
                .pathParam("id", experienceId)
                .when()
                .patch("/experience/updatepatch/{id}")
                .prettyPeek();

        Assert.assertEquals(response.statusCode(),200);
    }

    @Test (priority = 3)
    public void updateWithPatchMethod_2(){
        Map<String,Object> requestBody = new HashMap<>();
        requestBody.put("job","saadas");
        requestBody.put("company","kalskdms");
        requestBody.put("location","Turkey");

        //experienceId = 1062

        Response response = given().accept(ContentType.JSON)
                .and()
                .body(requestBody)
                .and()
                .headers(Authorization.getTokenWithMap(email, password))
                .and()
                .pathParam("id", experienceId)
                .when()
                .patch("/experience/updatepatch/{id}")
                .prettyPeek();

        Assert.assertEquals(response.statusCode(),200);
    }

    @Test (priority = 4)
    public void deleteExperience(){

        //experienceId = 1062

        Response response = given().accept(ContentType.JSON)
                .and()
                .pathParam("id", experienceId)
                .and()
                .headers(Authorization.getTokenWithMap(email, password))
                .and()
                .when()
                .delete("/experience/delete/{id}")
                .prettyPeek();

        Assert.assertEquals(response.statusCode(),200);
    }

}
