package com.jysk.location.controller;

import com.jysk.location.model.LocationResponse;
import com.jysk.location.model.UserDTO;
import com.jysk.location.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
    private static final String RESULT_VIEW_NAME = "result";
    private static final String LOGIN_VIEW_NAME = "login";
    private static final String LOCATION = "location";
    private static final String LOGIN_URL = "/";
    private static final String SEARCH_URL = "/search";
    private static final String SEARCH_FIELD_PARAM = "searchField";
    private static final String USER_MESSAGE_PARAM = "userMessage";
    private static final String USER_MESSAGE = "Successfully 'testUser' loggedIn";

    /**
     * Test the loading of login UI.
     */
    @Test
    void testLogin() throws Exception {
        // Perform GET request to load login UI page.
        mockMvc.perform(get(LOGIN_URL))
                .andExpect(status().isOk())  // Check HTTP status
                .andExpect(view().name(LOGIN_VIEW_NAME));  // Expected view
    }

    /**
     * Test the login functionality with credentials.
     */
    @Test
    void testUserLoginSuccess() throws Exception {
        // given
        var user = "testUser";
        var password = "testPassword";
        var userIdParam = "userId";
        var passwordParam = "password";
        // Mock the service call
        when(userService.logUserDetails(any(UserDTO.class))).thenReturn("Successfully '" + user + "' loggedIn");

        // Perform POST request to /login with user data
        mockMvc.perform(post(LOGIN_URL)
                        .param(userIdParam, user)
                        .param(passwordParam, password))
                .andExpect(status().isOk())  // Check HTTP status
                .andExpect(view().name(RESULT_VIEW_NAME));  // Expected view
    }

    /**
     * Test the search functionality with a valid location.
     */
    @Test
    void testSearchLocationsSuccess() throws Exception {
        // given
        var cityName = "Berlin";
        var zipcode = "10115";
        // Mock the service call to return a valid location(LocationResponse)
        LocationResponse locationResponse = new LocationResponse(cityName, zipcode, null);
        when(userService.search(cityName)).thenReturn(locationResponse);

        // Perform POST request to /search with valid search field
        mockMvc.perform(post(SEARCH_URL)
                        .param(SEARCH_FIELD_PARAM, cityName)
                        .param(USER_MESSAGE_PARAM, USER_MESSAGE))
                .andExpect(status().isOk())  // Check HTTP status
                .andExpect(view().name(RESULT_VIEW_NAME))  // Expected view
                .andExpect(model().attributeExists(LOCATION))  // Check if location attribute is present
                .andExpect(model().attribute(LOCATION, locationResponse));  // Expected location object
    }

    /**
     * Test the search functionality with an invalid location.
     */
    @Test
    void testSearchLocationsFailure() throws Exception {
        // given
        var searchField = "unknownCity";
        // Mock the service call to return LocationResponse for invalid search
        LocationResponse locationResponse = new LocationResponse(
                null,
                null,
                "No location found with specified city or zipcode : " + searchField);
        when(userService.search(searchField)).thenReturn(locationResponse);

        // Perform POST request to /search with invalid search field
        mockMvc.perform(post(SEARCH_URL)
                        .param(SEARCH_FIELD_PARAM, searchField)
                        .param(USER_MESSAGE_PARAM, USER_MESSAGE))
                .andExpect(status().isOk())  // Check HTTP status
                .andExpect(view().name(RESULT_VIEW_NAME))  // Expected view
                .andExpect(model().attributeExists(LOCATION))  // Check if error object is present
                .andExpect(model().attribute(LOCATION, locationResponse));  // Expected error message object
    }
}
