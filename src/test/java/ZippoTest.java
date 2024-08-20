import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ZippoTest {
    @Test
    public void test1(){
        given()
                // hazırlık işlemleri kodları
                .when()
                // endpoint(url), metod u verip istek gönderiyor

                .then()
                 // assertion test data işlemleri
        ;
    }


    @Test
    public void Test2(){ // aynı classda 2 given when çalışmıyor  birine ctrl+/ yap diğerini çalıştır
//        given()
//                // hazırlık kısmı boş
//                .when()
//                .get("http://api.zippopotam.us/us/90210")  // url ye git
//
//                .then()
//                .log().body();// dönen body json data, log().All() : data dışındaki her şey.
//        System.out.println("........................................ .............................= " );
                given().when().get("http://api.zippopotam.us/us/90210")
                        .then().log().all()//log().All() : data dışındaki her şey.
        ;
    }
    @Test
    public void StatusCodeTest(){
        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body()
                .statusCode(200) // statüs kodla bire bir aynı 200 se 200 404 se 404 yazacan yoksa hata raporu olutuyor
        ;
    }

    @Test
    public void contentTypeTest(){
        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body()
                .statusCode(200)
                .contentType(ContentType.JSON); // dönen datanın tipi JSON mı
        ;
    }
    @Test
    public  void  AssertionLibrary(){
        given()                                                     //        Postman                  RestAssured   ====> https://jsonpathfinder.com/
                .when()                                            //   body=pm.Response.json();      body
                .get("http://api.zippopotam.us/us/90210")       //  body.country                   body("Country")
                                                                    //  body.'post code'              body("post code")


                .then()
                .log().body()
                .statusCode(200) // assertion
                .body("country",equalTo("United States"))  // pom.xml e hamcreast eklendi  assertion kütüphanesi ******
         // import static org.hamcrest.Matchers.*;
         // dönen body nin country değişkeni " United States" eşit Mİ.
        ;

    }

    @Test
    public void  checkStateInResponseBody(){
        // Soru : "http://api.zippopotam.us/us/90210"  endpoint indne dönen
        // place dizisinin ilk elemanının state değerinin  "California"
        // olduğunu doğrulayınız
        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then().log().body()
                .statusCode(200)
                .body("places[0].state", equalTo("California"));

    }
    @Test
    public void checkHasItem(){
        // Soru : "http://api.zippopotam.us/tr/01000"  endpoint in dönen
        // place dizisinin herhangi bir elemanında  "Dörtağaç Köyü" değerinin
        // olduğunu doğrulayınız

        given()

                .when()
                .get("http://api.zippopotam.us/tr/01000")

                .then()
                //.log().body()
                .body("places.'place name'", hasItem("Dörtağaç Köyü"))

        ;

    }
    // Soru : "http://api.zippopotam.us/us/90210"  endpoint in dönen
    // place dizisinin dizi uzunluğunun 1 olduğunu doğrulayınız.

    @Test
    public void bodyArrayHasSizeTest(){
        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .body("places", hasSize(1)); // places ın item size 1 e eşit mi
        ;
    }
    @Test
    public void bodyArrayHasSizeTest2(){
        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .body("places.size()", equalTo(1)); // places ın item size 1 e eşit mi
        ;
    }

}
