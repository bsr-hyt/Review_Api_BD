package apiTest.day06;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;

public class Authorization {

   static String email = "Charity.Fritsch23@gmail.com";
   static String password = "123467Zzz";

    @BeforeClass
    public void beforeClassSetUp() {
        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void loginAndGetToken(){
        Response response = given().accept(ContentType.MULTIPART)
                .formParam("email", email) //Multipart olduğu için formParam yazdık.
                .and()
                .formParam("password", password)
                .when().log().all()
                .post("/allusers/login");

        response.prettyPrint();

        String token = response.path("token");
        System.out.println("token = " + token);
    }


    public static String getToken(){  //her classtan çağırabilmek için static yaptık.
        Response response = given().accept(ContentType.MULTIPART)
                .formParam("email", email) //Multipart olduğu için formParam yazdık.
                .and()
                .formParam("password", password)
                .when().log().all()
                .post("/allusers/login");

        return response.path("token");
    }


    public static String getToken(String email, String password){  //her classtan çağırabilmek için static yaptık.
        Response response = given().accept(ContentType.MULTIPART)
                .formParam("email", email) //Multipart olduğu için formParam yazdık.
                .and()
                .formParam("password", password)
                .when().log().all()
                .post("/allusers/login");

        return response.path("token");
    }


    public static Map<String,Object> getTokenWithMap(String email, String password){  //Neden map kullandık; bu emtodu çağırırken .headers("token", Authorization.getToken(email, password)) de "token" yazmamak için
        Response response = given().accept(ContentType.MULTIPART)
                .formParam("email", email) //Multipart olduğu için formParam yazdık.
                .and()
                .formParam("password", password)
                .when().log().all()
                .post("/allusers/login");

        Map<String,Object> map = new HashMap<>();
        map.put("token",response.path("token"));

        return map;
    }




}
