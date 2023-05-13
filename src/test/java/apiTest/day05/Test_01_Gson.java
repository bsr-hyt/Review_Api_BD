package apiTest.day05;

import apiTemplates.User;
import com.google.gson.Gson;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class Test_01_Gson {

    //json --> java : de-serialization

    //java --> json : serialization

    //----------

    //gsonın yaptıklarını RestAssured arka planda yapıyor.Bize çok büyük kolaylık sağlıyor.


    @Test
    public void jsonToJava_deSerialization(){
        /**
         {
         "id": 1549066,
         "name": "Leela Kakkar",
         "email": "kakkar_leela@anderson.test",
         "gender": "female",
         "status": "inactive"
         }
         */

        //gson objesi oluşturalım.
        Gson gson = new Gson();

        String jsonBody = " {\n" +
                "         \"id\": 1549066,\n" +
                "         \"name\": \"Leela Kakkar\",\n" +
                "         \"email\": \"kakkar_leela@anderson.test\",\n" +
                "         \"gender\": \"female\",\n" +
                "         \"status\": \"inactive\"\n" +
                "         }";

        //de-serialization map ile json->java
        Map<String,Object> mapBody = gson.fromJson(jsonBody, Map.class);
        //json objesini map içine attık. Böylece jsonı, gson kullanarak mape dönüştürdük;javaya dönüştürdük.
        System.out.println("mapBody.get(\"id\") = " + mapBody.get("id"));
        System.out.println("mapBody.get(\"name\") = " + mapBody.get("name"));

        //de-serialization with custom(özelleştirilmiş) class json->java
        User user = gson.fromJson(jsonBody, User.class);
        System.out.println("user.getId() = " + user.getId());
        System.out.println("user.getName() = " + user.getName());
    }

    @Test
    public void javaToJson_serializationWithMap() {
        //serialization
        //java objesini yazıp, jsona çevirip, çevrilen şeyide postmane girmek için kullanılır bu yöntem

        /**
         * id:61
         * name:Hasan
         * email: aaa@aa.com
         */

        //gson objesi oluşturalım.
        Gson gson = new Gson();

        Map<String,Object> map = new HashMap<>();
        map.put("id","98");
        map.put("name","Busra");
        map.put("email","budra@aa.com");

        System.out.println("map = " + map);

        String json = gson.toJson(map);
        System.out.println("json = " + json);
    }

    @Test
    public void javaToJson_serializationWithCustomClass() {

        /**
         * id:61
         * name:Hasan Yaka
         * email: hYaka@gmail.com
         * gender:male
         * status:inactive
         */

        //user classını kullanarak java->json a dönüştürme;

        User user = new User();
        user.setId(61);
        user.setName("Hasan Yaka");
        user.setEmail("hYaka@gmail.com");
        user.setGender("male");
        user.setStatus("inactive");

        System.out.println("user = " + user);

        Gson gson = new Gson();
        String json = gson.toJson(user);

        System.out.println("json = " + json);

    }

    @Test
    public void test2() {

        User user = new User(61,"Hasan Yaka","hYaka@gmail.com","male","inactive");

        System.out.println("user = " + user);

        Gson gson = new Gson();
        String json = gson.toJson(user);

        System.out.println("json = " + json);
    }
}
