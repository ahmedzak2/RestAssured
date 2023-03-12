package academy;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.testng.annotations.Test;
import pojo.Login;

import java.io.File;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Info {
    private String url1="https://64078a882f01352a8a7e29f0.mockapi.io/api/v1/";
    private String user1 ="users";
    private String num= "users/3";

    @Test
    public void testcasw() {
        // to get the website which we will test the api
        given().baseUri(url1)
                .when().get(user1);
    }
    @Test
    public void testBody(){
        given().baseUri("https://reqres.in/")
                /*
                * to use to search for something in API request
                * */
                .queryParam("id","7")
                .queryParam("first_name","Lindsay")
                .log().all()
                .when().get("/api/users?page=2")
                .then()
                .log().all()
                ;



    }
    @Test
    public void testBodyParams(){
        given().baseUri("https://reqres.in/")
                /*
                 * to use to search for something in API request
                 * 2 params in same request or line
                 * it can receive Hash map as headers
                 * */
                .queryParams("id","7","first_name","Michael")
                .log().all()
                .when().get("/api/users?page=2")
                .then()
                .log().all()
        ;



    }
@Test
    public void testLogin(){
        String login = "{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"cityslicka\"\n" +
                "}";
    given().baseUri("https://reqres.in")
           /*
           * to determine the body is JSON
           * without the header the it will use it as text
           * */
            .header("Content-Type","application/json")
          /*
          * to send body in request
          * */
            .body(login)
            .log().all()
            .when().post("/api/login")
            .then().log().all().assertThat().statusCode(200)
            ;

}
    @Test
    public void anotherTestLogin(){
        /*
        * don't use in project because it will be hard coded
        * */
        String login = "{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"cityslicka\"\n" +
                "}";
        given().baseUri("https://reqres.in")
                /*
                 * to determine the body is JSON
                 * without the header the it will use it as text
                 * As above
                 * */
                .contentType(ContentType.JSON)                /*
                 * to send body in request
                 * */
                .body(login)
                .log().all()
                .when().post("/api/login")
                .then().log().all().assertThat().statusCode(200)
        ;

    }
    @Test
    public void testLoginByuseAnotherbody(){
// to read json form file
        File file =new File("src/test/resources/login.json");
        given().baseUri("https://reqres.in")
                /*
                 * to determine the body is JSON
                 * without the header the it will use it as text
                 * */
                .header("Content-Type","application/json")
                /*
                 * to send body in request by get the info form JSON file 
                 * */
                .body(file)
                .log().all()
                .when().post("/api/login")
                .then().log().all().assertThat().statusCode(200)
        ;

    }

    @Test
    public void testLoginByuseHasMapbody(){
        /*
         * to use hash map to store headers
         * You must specify  tyep of key and value which store in has map
         * and we must convert form java to JSON by serializer and versa
         *       * By use Jackson Databind depecny it make serializtion or Deserializetion by default
         */
        HashMap<String,String> body = new HashMap<>();
        body.put("email","eve.holt@reqres.in");
        body.put("password","cityslicka");
        given().baseUri("https://reqres.in")
                /*
                 * to determine the body is JSON
                 * without the header the it will use it as text
                 * */
                .header("Content-Type","application/json")
                /*
                 * to send body in request by get the info form JSON file
                 * */
                .body(body)
                .log().all()
                .when().post("/api/login")
                .then().log().all().assertThat().statusCode(200)
        ;

    }
    @Test
    public void testLoginByusePoJOclassbody(){

/*
* to save the paramters as object in class
*
* */
        Login body = new Login("eve.holt@reqres.in","cityslicka");
//  body.setEmail("eve.holt@reqres.in");
  //body.setPassword("cityslicka");

        given().baseUri("https://reqres.in")
                /*
                 * to determine the body is JSON
                 * without the header the it will use it as text
                 * */
                .header("Content-Type","application/json")
                /*
                 * to send body in request by get the info form JSON file
                 * */
                .body(body)
                .log().all()
                .when().post("/api/login")
                .then().log().all().assertThat().statusCode(200)
        ;

    }
}
