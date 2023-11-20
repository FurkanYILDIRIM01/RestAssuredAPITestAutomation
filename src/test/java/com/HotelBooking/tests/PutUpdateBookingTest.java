package com.HotelBooking.tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class PutUpdateBookingTest extends BaseTest{

    @Test
    public void updateBookingTest(){

        // token olustur header da gonder
        String token = createToken();

        // rezervasyon-booking olustur

        Response CreatBookingresponse = createBooking();

        int bookingId = CreatBookingresponse.jsonPath().getJsonObject("bookingid");

        //request olustur

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .header("Cookie", "token="+token) //createToken() BaseTest içinden kullanılabilirdi.
                .body(bookingObject("Osman","Kanat",650,false))
                .put("/booking/"+bookingId);  //createBookingId() BaseTest içinden kullanılabilirdi.
       // response.prettyPrint();


        //Assertion

        String firstName = response.jsonPath().getJsonObject("firstname");
        Assertions.assertEquals("Osman",firstName);

        String lastName = response.jsonPath().getJsonObject("lastname");
        Assertions.assertEquals("Kanat",lastName);

        int totalPrice = response.jsonPath().getJsonObject("totalprice");
        Assertions.assertEquals(650,totalPrice);

       // boolean depositPaid = response.jsonPath().getJsonObject("depositpaid");
        //Assertions.assertEquals(false,depositPaid);
        //ikinci kullanım sekli
        Assertions.assertEquals(false,response.jsonPath().getJsonObject("depositpaid"));
    }

}
