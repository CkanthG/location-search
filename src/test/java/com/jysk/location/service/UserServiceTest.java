package com.jysk.location.service;

import com.jysk.location.model.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserServiceTest {

    UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl();
    }

    /**
     * Test the login functionality with credentials.
     */
    @Test
    void testLogUserDetailsSuccess() {
        // given
        var userDto = new UserDTO("Sreekanth", "sreekanth");
        // then
        var actual = userService.logUserDetails(userDto);
        assertEquals(userDto.userId(), actual);
    }

    /**
     * Test the search functionality with a valid location.
     */
    @Test
    void testSearchByCityNameSuccess() {
        var searchField = "Berlin";
        var actual = userService.search(searchField);
        assertEquals(searchField, actual.cityName());
    }

    /**
     * Test the search functionality with a valid location.
     */
    @Test
    void testSearchByZipcodeSuccess() {
        var searchField = "10115";
        var actual = userService.search(searchField);
        assertEquals(searchField, actual.zipcode());
    }

    /**
     * Test the search functionality with an invalid location.
     */
    @Test
    void testSearchByUnknownCityNameZipcodeFailed() {
        var searchField = "UnknownCity";
        var actual = userService.search(searchField);
        assertEquals("No location found with specified city or zipcode : " + searchField, actual.errorMessage());
    }
}