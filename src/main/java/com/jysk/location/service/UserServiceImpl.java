package com.jysk.location.service;

import com.jysk.location.model.LocationResponse;
import com.jysk.location.model.Locations;
import com.jysk.location.model.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.util.Arrays;

/**
 * This service class is consist of all business logic related to users.
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService{

    /**
     * Logs the details of a user based on the provided user data transfer object (DTO).
     * @param userDTO the data transfer object containing user details.
     * @return a username to display on UI.
     */
    public String logUserDetails(UserDTO userDTO) {
        var password = Base64Coder.encodeString(userDTO.password());
        log.debug("method: logUserDetails -> User '{}' with Password '{}' logged in successfully.", userDTO.userId(), password);
        return userDTO.userId();
    }

    /**
     * Searches for a location based on the provided search field, which could be a city name or a zipcode.
     * @param searchField searchField the search input, either a city name or a zipcode, to find a matching location
     * @return a LocationResponse object representing the found location (city and zipcode).
     */
    public LocationResponse search(String searchField) {
        log.debug("method: search -> User searching for '{}' location.", searchField);
        var result = findByZipcode(searchField);
        if (result == null) {
            result = findByCityName(searchField);
        }
        if (result != null) {
            log.debug("method: search -> Location Found with '{}'.", searchField);
            return new LocationResponse(
                    result.getCityName(),
                    result.getZipcode(),
                    null
            );
        } else {
            log.debug("method: search -> Location Not Found with '{}'.", searchField);
            return new LocationResponse(
                    null,
                    null,
                    "No location found with specified city or zipcode : " + searchField
            );
        }
    }

    /**
     * Search Location by zipcode
     * @param zipcode is used to identify the city.
     * @return a matched Locations object or null.
     */
    public Locations findByZipcode(String zipcode) {
        log.debug("method: findByZipcode -> Find location by '{}'.", zipcode);
        return Arrays.stream(Locations.values())
                .filter(location -> location.getZipcode().equals(zipcode))
                .findFirst()
                .orElse(null); // Return null if no match found.
    }

    /**
     * Search Location by city name
     * @param cityName is used to identify the city.
     * @return a matched Locations object or null.
     */
    public Locations findByCityName(String cityName) {
        log.debug("method: findByCityName -> Find location by '{}'.", cityName);
        return Arrays.stream(Locations.values())
                .filter(location -> location.getCityName().equalsIgnoreCase(cityName)) // Case-insensitive search
                .findFirst()
                .orElse(null); // Return null if no match found
    }

}
