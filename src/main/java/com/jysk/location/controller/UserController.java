package com.jysk.location.controller;

import com.jysk.location.model.UserDTO;
import com.jysk.location.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * This controller class is used for generate the UI and take request from user and delegate request to service layer and send response back to UI.
 */
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private static final String MESSAGE = "message";
    private static final String LOCATION = "location";

    /**
     * UI page with empty model object to fill the user details in it.
     * @param model used to bind the user fields.
     * @return login page.
     */
    @GetMapping("/")
    public String userLogin(Model model) {
        model.addAttribute("user", new UserDTO(null, null));
        return "login";
    }

    /**
     * Logs the details of a user based on the provided user data transfer object (DTO).
     * @param userDTO used to read the user details and log them inside the application.
     * @param model is used to bind the response object.
     * @return result page.
     */
    @PostMapping("/")
    public String userLogin(@ModelAttribute("user") UserDTO userDTO, Model model) {
        var user = userService.logUserDetails(userDTO);
        model.addAttribute(MESSAGE, "Successfully '" + user + "' loggedIn");
        return "result";
    }

    /**
     * Searches for a location based on the provided search field, which could be a city name or a zipcode.
     * @param searchField is used to search location.
     * @param userMessage is used to find the user information.
     * @param model is used to bind result.
     * @return result page.
     */
    @PostMapping("/search")
    public String searchLocations(
            @RequestParam("searchField") String searchField,
            @RequestParam("userMessage") String userMessage,
            Model model
    ) {
        var matchingLocation = userService.search(searchField);
        model.addAttribute(LOCATION, matchingLocation);
        model.addAttribute(MESSAGE, userMessage);
        return "result";
    }
}
