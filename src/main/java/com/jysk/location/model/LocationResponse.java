package com.jysk.location.model;

/**
 * This record is used to bind the response object.
 * @param cityName is used to fill searched city name.
 * @param zipcode is used to fill searched zipcode.
 * @param errorMessage is used to fill errorMessage.
 */
public record LocationResponse(String cityName, String zipcode, String errorMessage) {
}
