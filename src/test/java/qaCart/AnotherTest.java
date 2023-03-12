package qaCart;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
/*
* we must write import static to import Rest assured to skip to
*  address rest assured
* */
import static io.restassured.RestAssured.*;
import static   io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class AnotherTest {
    private String url1="https://64078a882f01352a8a7e29f0.mockapi.io/api/v1/";
    private String user1 ="users";
    private String num= "users/3";


    @Test
    public void testcasw(){
        // to get the website which we will test the api
        given().baseUri(url1)
                /*
                *  to get the endpoint which we talk to it
                *  determine which type of request

                 * */
                .when().get(user1)

                /*
                * to show all the response
                * */
                .then( ).log().all().assertThat().statusCode(200)
                /*
                * to check something in body we must add assertThat then add body ( JSON path, equal to )
                * we can use is to more readable
                * */
                .assertThat().body("[0].name",is(equalTo("Terrance Marks")))

             /*
             * you can add more than one thing to check on it in body */
                .assertThat().body("[0].id",equalTo("2"),"name",hasItem("Larry Schmeler Jr."))
                   /*
                   * to make sure specfic array has item
                   *search for all names to make sure has this name
                   * if I give wrong name is return to me wrong message
                   * JSON path name don't math
                   *
                   * use hasItems to check on more than one item in same fucntion
                   *  */
                .assertThat().body("name",hasItem("Terence Hyatt"),"name",hasItems("Terence Hyatt","Tomas Torp"))
                /*
                * to check if the item not in array
                * */
                .assertThat().body("name",not(hasItem("saad")))
                /*
                *  When use contains must the arrangement of items  is right
                * and we must put the exact number of items
                * */

               // .assertThat().body("id",contains("2","3"))

                /*it fails because the Items  isn't exact number as is body
                * containINAnyOrder  check the count only the arrangement of items don't matter
                * */

                /*
                * we can use empty or not empty to check the array is empty or not
                * */

                .assertThat().body("name",not(empty()))
                /*to check the size of array  or
                * use the grovey method to use the equal method
                * */
                .assertThat().body("name",hasSize(40),"name.size()",equalTo(40))
                   /*
                   * to check every item in list has some condition
                   * as start with specif thing */
                .assertThat().body("createdAt",everyItem(startsWith("2023")))
               /*
               * to make sure the object has specific key as ID
               * */
                .assertThat().body("[0]",hasKey("id"),"[1]",hasKey("name"))
              /*TO make sure The array has ID
              * */
                .assertThat().body("[0]",hasValue("Afghanistan"))
                /*to make sure specific key has specific value */
                .assertThat().body("[0]",hasEntry("name","Terrance Marks"));



    }
    @Test
    public void testoneUser(){
        given().baseUri(url1)
                /*
                 *  to get the endpoint which we talk to it
                 *  determine which type of request

                 * */
                .when().get(num)

                /*
                 * to show all the response
                 * assertion come after then  and must show what we need to see
                 * to get status code
                 *
                 * */

                /*
                * to make assert for response body must be  in the then method
                * */
                .then().log().all().assertThat().statusCode(200);


    }
    @Test
    void testcasw2() {
        // to save the response in variable
        Response response= given().baseUri(url1)
                /*
                 *  to extract all response

                 * */
                .when().get(user1).then().extract().response();
// to print all response
      //  JsonPath  path=new JsonPath(response.asString());
    //    System.out.println(response.asString());
        // to extract specific value only
//String res = response.path("[0].name");

      String res=  JsonPath.from(response.asString()).getString("[0].name");
        System.out.println(res);
    }
    @Test
    void testcaswLogs() {
        // to save the to get all logs  or specific as body or header or response
        given().baseUri(url1).log().all()
                /*
                 *  to extract all response

                 * */
                .when().get(user1).then().log().status().extract().response();
    }


    @Test
    void testcaswError() {
        // to save the to get all logs  or specific as body or header or response
        given().baseUri(url1).log().ifValidationFails()
                /*
                 * to print logs if the validation failed
                 * log.ifError  to print it test case fail

                 * */
                .when().get(user1).then().log().ifValidationFails().assertThat().body("[0]",empty()).extract().response();
    }}