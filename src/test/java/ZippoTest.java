import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.*;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ZippoTest {
    @Test
    public void test1() {
        given()
                // hazırlık işlemleri kodları
                .when()
                // endpoint(url), metod u verip istek gönderiyor

                .then()
        // assertion test data işlemleri
        ;
    }


    @Test
    public void Test2() { // aynı classda 2 given when çalışmıyor  birine ctrl+/ yap diğerini çalıştır
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
    public void StatusCodeTest() {
        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body()
                .statusCode(200) // statüs kodla bire bir aynı 200 se 200 404 se 404 yazacan yoksa hata raporu olutuyor
        ;
    }

    @Test
    public void contentTypeTest() {
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
    public void AssertionLibrary() {
        given()                                                     //        Postman                  RestAssured   ====> https://jsonpathfinder.com/
                .when()                                            //   body=pm.Response.json();      body
                .get("http://api.zippopotam.us/us/90210")       //  body.country                   body("Country")
                //  body.'post code'              body("post code")


                .then()
                .log().body()
                .statusCode(200) // assertion
                .body("country", equalTo("United States"))  // pom.xml e hamcreast eklendi  assertion kütüphanesi ******
        // import static org.hamcrest.Matchers.*;
        // dönen body nin country değişkeni " United States" eşit Mİ.
        ;

    }

    @Test
    public void checkStateInResponseBody() {
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
    public void checkHasItem() {
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
    public void bodyArrayHasSizeTest() {
        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .body("places", hasSize(1)); // places ın item size 1 e eşit mi
        ;
    }

    @Test
    public void bodyArrayHasSizeTest2() {
        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .body("places.size()", equalTo(1)); // places ın item size 1 e eşit mi
        ;
    }

    @Test
    public void combiningTest() {
        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then().statusCode(200)
                .body("places", hasSize(1))
                .body("places[0].state", equalTo("California"))
                .body("places[0].'place name'", equalTo("Beverly Hills"))

        ;

    }

    @Test
    public void pathParamTest() {

        given()
                .pathParam("ulke", "us")
                .pathParam("postaKod", 90210)
                .log().uri() // request link çalışmadan önceki hali

                .when()
                .get("http://api.zippopotam.us/{ulke}/{postaKod}")

                .then()
                .statusCode(200)
        ;
    }

    @Test
    public void queryParamTest() {
        // https://gorest.co.in/public/v1/users?page=3
        given()
                .param("page", 1) // ?page=1  şeklinde linke ekleniyor  // queryParam ile de kullanılabilir
                .log().uri()

                .when()
                .get("https://gorest.co.in/public/v1/users") // ?page=1

                .then()
                .statusCode(200)
                .log().body();
    }

    @Test
    public void queryParamTest2() {
        // https://gorest.co.in/public/v1/users?page=3
        // bu linkteki 1 den 10 kadar sayfaları çağırdığınızda response daki donen page degerlerinin
        // çağrılan page nosu ile aynı olup olmadığını kontrol ediniz.

        for (int i = 1; i <= 10; i++) {
            given()
                    .param("page",i)
                    .log().uri()
                    .when().get("https://gorest.co.in/public/v1/users")
                    .then()
                    //.log().body()
                    .body("meta.pagination.page",equalTo(i))

            ;
        }
        
    }
    //******************************************************************* değer atayıp istediğin zaman gereksiz kod yazma diye kullanılıyor.
    RequestSpecification requestSpec;
    ResponseSpecification responseSpec;

    @BeforeClass
    public  void Setup(){
        baseURI = "https://gorest.co.in/public/v1";

        requestSpec= new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .log(LogDetail.URI)  // log().uri()
                .build();

        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)  // statusCode(200)
                .log(LogDetail.BODY)  //log().body()
                .expectContentType(ContentType.JSON)
                .build();

    }
    @Test
    public void requestResponseSpecificationn(){
        given()
                .param("page",1)
                .spec(requestSpec)

                .when()
                .get("/users") // http hok ise baseUri baş tarafına gelir.

                .then()
                .spec(responseSpec)
        ;
    }


}
