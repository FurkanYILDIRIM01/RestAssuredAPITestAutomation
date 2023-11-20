package com.HotelBooking.tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class GetBookingByIDTest extends BaseTest{
    @Test
    public void getAllBookingByIDTest(){

        Response newBooking = createBooking();
        int bookingID = newBooking.jsonPath().getJsonObject("bookingid");

        Response response = given(spec)
                .when()
                .get("/booking/" + bookingID); //createBookingId() BaseTest içinden kullanılabilirdi.

        response
                .then()
                .statusCode(200);


        String firstname = response.jsonPath().getJsonObject("firstname");
        String lastname = response.jsonPath().getJsonObject("lastname");
        int totalPrice = response.jsonPath().getJsonObject("totalprice");

        Assertions.assertEquals("Furkan",firstname);
        Assertions.assertEquals("Yilmaz",lastname);
        Assertions.assertEquals(150,totalPrice);

    }
}
