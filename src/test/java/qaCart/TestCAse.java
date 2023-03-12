package qaCart;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TestCAse {
    @Test
    public void test(){
        // to use restAssured
        RestAssured.given();
        RestAssured.when();
        RestAssured.when();
        // we can't use then alone
        RestAssured.given().when().then();
    }


 }
