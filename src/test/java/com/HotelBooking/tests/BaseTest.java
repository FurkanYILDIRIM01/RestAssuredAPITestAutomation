package com.HotelBooking.tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;

import static java.lang.reflect.Array.*;
import java.lang.reflect.Array;
import java.util.Arrays;

import static io.restassured.RestAssured.given;


public class BaseTest {
    RequestSpecification spec;

    @BeforeEach
    public void setup(){
       spec = new RequestSpecBuilder()
                .setBaseUri("https://restful-booker.herokuapp.com")
                .addFilters(Arrays.asList(new RequestLoggingFilter(), new ResponseLoggingFilter()))
                .build();
    }

    public int createBookingId(){
        Response response = createBooking();
        return response.jsonPath().getJsonObject("bookingid");
    }

    public Response createBooking(){
        Response response=given(spec)
                .when()
                .contentType(ContentType.JSON)
                .body(bookingObject("Furkan","Yilmaz",150,true))
                .post("/booking");

       // response.prettyPrint();  setup methodunda belirtildi new RequestLoggingFilter(), new ResponseLoggingFilter()

        response.then()
                .statusCode(200);

        return response;
    }


    public String bookingObject(String firstNme, String lastName , int totalPrice , boolean depositePaid){
        JSONObject body = new JSONObject();
        body.put("firstname",firstNme);
        body.put("lastname",lastName);
        body.put("totalprice",totalPrice);
        body.put("depositpaid",depositePaid);

        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin","2023-11-21");
        bookingDates.put("checkout","2023-11-24");

        body.put("bookingdates",bookingDates);
        body.put("additionalneeds","breakfast");

        return body.toString();

    }

    public String createToken(){
        JSONObject body = new JSONObject();
        body.put("username","admin");
        body.put("password","password123");

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .when()
                .body(body.toString())
                .post("/auth");

       // response.prettyPrint();
        return response.jsonPath().getJsonObject("token");
    }
}
