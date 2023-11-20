package com.HotelBooking.tests;
import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PatchUpdateBookingTest extends BaseTest{

    @Test
    public void patchUpdateBookingTest(){


        JSONObject body = new JSONObject();
        body.put("firstname","Ahmet");

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .header("Cookie","token="+createToken())
                .when()
                .body(body.toString())
                .patch("/booking/"+createBookingId());

       // response.prettyPrint();

        //4- assertion
        Assertions.assertEquals("Ahmet",response.jsonPath().getJsonObject("firstname"));


        /*curl -X PATCH \
        https://restful-booker.herokuapp.com/booking/1 \
        -H 'Content-Type: application/json' \
        -H 'Accept: application/json' \
        -H 'Cookie: token=abc123' \
        -d '{
        "firstname" : "James",
                "lastname" : "Brown"
    }'*/

        //1- token olustur
        //  String token = createToken(); Bu methoda gerek olmadan Header da doğrudan BaseTest içindaki createToken() methodunu kullanabiliriz.

        //2- rezervasyon-booking olustur
        // BaseTest de olusturulan createBookingId() methodu kulanıldı- Çağrı içinde "url"+ createBookingId()
        //birinci yol -kullanılmadı
        //int bookingId = createBookingId();

        //ikinci yol -kullanılmadı
        // Response Patchresponse = createBooking();
        //int bookingId = Patchresponse.jsonPath().getJsonObject("bookingid");


        //3- cagriyi yap

    }
}
