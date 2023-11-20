package com.HotelBooking.tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;
import static io.restassured.RestAssured.given;


public class GetBookingTest extends BaseTest {

    @Test
    public void getAllBookingTest(){
        given(spec)
                .when()
                .get("/booking")
                .then()
                .statusCode(200);

    }
    @Test
    public void getBooking_with_firstname_filter_test(){
        //booking olustur
        int bookingID = createBookingId();

        //query parametre ekle
        spec.queryParam("firstname","Furkan");
        spec.queryParam("lastname","Yilmaz");

    //cagriyi gerçeklestir
        Response response = given(spec)
                .when()
                .get("/booking");

        //Assertion
        response
                .then()
                .statusCode(200);


        List<Integer> bookinglist = response.jsonPath().getList("bookingid");
        System.out.println(bookinglist);
        Assertions.assertTrue(bookinglist.contains(bookingID));

    }


}
