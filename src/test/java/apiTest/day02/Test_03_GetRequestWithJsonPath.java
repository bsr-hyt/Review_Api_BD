package apiTest.day02;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;

import static org.testng.Assert.*;
import static io.restassured.RestAssured.*;

public class Test_03_GetRequestWithJsonPath {

    @BeforeClass
    public void beforeClassSetUp() {
        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void testWithJsonPath() {
        /**
         * /
         *     TASK
         *     Given accept type is json
         *     When user sends a GET request to /allusers/alluser
         *     Then the status Code should be 200
         *     And Content type json should be "application/json; charset=UTF-8"
         *
         */

        Response response = given().accept(ContentType.JSON)
                .queryParam("pagesize", 5)
                .queryParam("page", 1)
                .when()
                .get("/allusers/alluser");

        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json; charset=UTF-8");

        //jsonPath ile devam edelim.
        JsonPath jsonPath = response.jsonPath();

        //ilk elemanın id sinin 1 olduğunu assert edelim.
        assertEquals(jsonPath.getInt("id[0]"), 1);

        //beşinci(sonuncu)  eleamınınid sinin 33 olduğunu assert edelim.
        assertEquals(jsonPath.getInt("id[4]"), 33);
        assertEquals(jsonPath.getInt("id[-1]"), 33);

        //4.eleamının adının wilini3845@once olduğunu assert edelim.
        assertEquals(jsonPath.getString("name[3]"), "wilini3845@once");

        //bütün id leri alalım ve assert edelim.[1,5,24,29,33]
        List<Integer> actualIdList = jsonPath.getList("id");
        List<Integer> expectedList = new ArrayList<>(Arrays.asList(1, 5, 24, 29, 33));
//        List<Integer> expectedList = new ArrayList<>(List.of(1,5,24,29,33));
        assertEquals(actualIdList, expectedList);

        //2.yol(path metodu ile)
        System.out.println("response.path(\"id\") = " + response.path("id"));
        //Object o = response.path("id") //bu aslında bir list ancak java Object olarak alıgılıyor.

        //ilk elemanın skilllerinin ve 2.skillini assert edelim.[PHP,Java]
        List<String> userSkills = jsonPath.getList("skills[0]");
        assertEquals(userSkills.get(1),"Java");

        //2.yol
        assertEquals(jsonPath.getString("skills[0][1]"),"Java");

        //3.yol
        assertEquals(response.path("skills[0][1]"),"Java");

        //Not: jsonpathın kendine özel metodları var; getString, getInt gibi.
        //Bu yüzden response path ile yazdığın zaman bunun int mi list mi olduğunu senin belirtmen lazım.

        //Bütün elemanların bütün skilllerini alalım.(List of List) --> path metodu ile de olur.
        List<List<String>> allUsersSkills = jsonPath.getList("skills");
        System.out.println("allUsersSkills = " + allUsersSkills); //[[PHP, Java], [], [Cucumber, TestNG], [], [TesNg, Cucumber, RestAssured]]

        //ilk userın ilk eğitiminin school unun School or Bootcamp olduğunu assert edelim.(Hepsi path method ile de olur.)
        System.out.println("jsonPath.getString(\"education[0].school[0]\") = " + jsonPath.getString("education[0].school[0]"));
        System.out.println("jsonPath.get(\"education[0][0].school\") = " + jsonPath.get("education[0][0].school"));

        //---jsonPath ile;
        Map<String, Object> userOneFirstEducation = jsonPath.getMap("education[0][0]");
        System.out.println("userOneFirstEducation.get(\"school\") = " + userOneFirstEducation.get("school"));

        //---response.path ile;
        Map<String, Object> userOneFirstEducation2 = response.path("education[0][0]");
        System.out.println("userOneFirstEducation2 = " + userOneFirstEducation2);
    }
}
