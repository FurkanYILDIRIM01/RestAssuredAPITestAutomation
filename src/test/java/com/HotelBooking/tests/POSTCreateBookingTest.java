package com.HotelBooking.tests;

import com.HotelBooking.models.Booking;
import com.HotelBooking.models.BookingDates;
import com.HotelBooking.models.BookingResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;


public class POSTCreateBookingTest extends BaseTest {
    @Test
    public void postCreateBookingTest(){

        Response response = createBooking();

        Assertions.assertEquals("Furkan", response.jsonPath().getJsonObject("booking.firstname"));
        Assertions.assertEquals("Yilmaz", response.jsonPath().getJsonObject("booking.lastname"));
        Assertions.assertEquals(150, (Integer) response.jsonPath().getJsonObject("booking.totalprice"));
        Assertions.assertEquals(true, response.jsonPath().getJsonObject("booking.depositpaid"));

    }
    @Test
    public void createBookingWithPojo(){
        BookingDates bookingDates = new BookingDates("2023-01-01","2023-01-03");
        Booking booking = new Booking("Orhan","Baba",546,false,bookingDates,"breakfast");

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .body(booking)
                .when()
                .post("/booking");

        response
                .then()
                .statusCode(200);

        BookingResponse bookingResponse = response.as(BookingResponse.class);
        System.out.println(bookingResponse+" booking response kaydedildi");

        Assertions.assertEquals("Orhan",bookingResponse.getBooking().getFirstname());
        Assertions.assertEquals("Baba",bookingResponse.getBooking().getLastname());

    }
}
