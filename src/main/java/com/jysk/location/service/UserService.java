package com.jysk.location.service;

import com.jysk.location.model.LocationResponse;
import com.jysk.location.model.UserDTO;

/**
 * This interface defines the contract for user-related operations,
 * serving as an abstract layer for implementations.
 */
public interface UserService {
    /**
     * Logs the details of a user based on the provided user data transfer object (DTO).
     * @param userDTO the data transfer object containing user details.
     * @return a username to display on UI.
     */
    String logUserDetails(UserDTO userDTO);

    /**
     * Searches for a location based on the provided search field, which could be a city name or a zipcode.
     * @param searchField searchField the search input, either a city name or a zipcode, to find a matching location
     * @return a LocationResponse object representing the found location (city and zipcode).
     */
    LocationResponse search(String searchField);
}
