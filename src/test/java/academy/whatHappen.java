package academy;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;

public class whatHappen {
    @Test
    public void testHeaders(){
        /*
         * to use hash map to store headers
         * You must specify  tyep of key and value which store in has map
         * */
        HashMap<String,String> hashMap=new HashMap<>();
        /*
         * to store  the header in map
         *
         * */
        hashMap.put("id","5");
        hashMap.put("first_name","Michael");
        /*
         * another method to use headers
         * this npt header to send this is paramters
         * */
        Header typeHeader = new Header("id","8");
        Headers headers =new Headers(typeHeader,typeHeader);
        given().baseUri("https://reqres.in/")
                /*to search for something by header key value
                 * to add more header must be as in code down
                 * or write headers
                 * */
                .header("first_name","Michael")
                .header("last_name","Edwards")
                .header("","","","")
                /*
                 * to make it object of class of header
                 * */
                .header(typeHeader)
                .headers(hashMap)
                .log().all()
                .when().get("/api/users?page=2")
                .then().log().all()
                .assertThat().statusCode(200).body("data[0].first_name",hasItem("Michael"));

    }

}
