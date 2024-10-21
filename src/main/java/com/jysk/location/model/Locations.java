package com.jysk.location.model;

import lombok.Getter;

/**
 * These Locations Enum is used to store in memory data, to find out location with their zipcode and city name.
 */
@Getter
public enum Locations {
    BERLIN("10115", "Berlin"),
    HANDEWITT("24983", "Handewitt"),
    HAMBURG("20095", "Hamburg"),
    MUNICH("80331", "Munich"),
    COLOGNE("50667", "Cologne"),
    FRANKFURT("60311", "Frankfurt"),
    STUTTGART("70173", "Stuttgart"),
    DUSSELDORF("40213", "Dusseldorf"),
    LEIPZIG("04109", "Leipzig"),
    DORTMUND("44135", "Dortmund");

    private final String zipcode;
    private final String cityName;

    /**
     * Constructor for the Locations Enum
     * @param zipcode is used to identify city.
     * @param cityName is used to identify city.
     */
    Locations(String zipcode, String cityName) {
        this.zipcode = zipcode;
        this.cityName = cityName;
    }
}
