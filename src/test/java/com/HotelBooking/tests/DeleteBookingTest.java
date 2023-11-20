package com.HotelBooking.tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class DeleteBookingTest extends BaseTest {
    @Test
    public void deleteBookingTests() {


        Response response = given(spec)
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + createToken())
                .when()
                .delete("/booking/" + createBookingId());

        //response.prettyPrint();

        response
                .then()
                .statusCode(201);


        //1-token

        //2-booking rezervasyon

        //3-delete çağrısı yap
 /*   curl -X DELETE \
    https://restful-booker.herokuapp.com/booking/1 \
            -H 'Content-Type: application/json' \
            -H 'Cookie: token=abc123'*/
        //4-assertion


    }
}