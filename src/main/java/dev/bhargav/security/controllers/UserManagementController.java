package dev.bhargav.security.controllers;

import dev.bhargav.security.exception.ResourceNotFoundException;
import dev.bhargav.security.exception.UserAlreadyExistAuthenticationException;
import dev.bhargav.security.model.AccountDto;
import dev.bhargav.security.model.ApiGenericResponse;
import dev.bhargav.security.model.SignUpRequest;
import dev.bhargav.security.model.UserDto;
import dev.bhargav.security.service.UserManagementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User Management", description = "User Management APIs")
@RestController
@RequestMapping("/api/user")
public class UserManagementController {

    private static final Logger LOG = LoggerFactory.getLogger(UserManagementController.class);

    @Autowired
    UserManagementService userManagementService;

    @Operation(summary = "Get all users")
    @ApiResponse(responseCode = "200", description = "All existing users",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AccountDto.class))})
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.ALL_VALUE)
    public List<UserDto> getAllUsers() {
        return this.userManagementService.getAllUsers();
    }

    @Operation(summary = "Create a new user")
    @ApiResponse(responseCode = "200", description = "New user created successfully",
        content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiGenericResponse.class))})
    @PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiGenericResponse> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) throws UserAlreadyExistAuthenticationException {
        UserDto userDto;
        try {
            userDto = this.userManagementService.registerNewUser(signUpRequest);
        } catch (UserAlreadyExistAuthenticationException e) {
            throw new UserAlreadyExistAuthenticationException("Username is already in use");
        }
        return ResponseEntity.ok().body(new ApiGenericResponse(true, "User registered successfully: " + userDto.getUsername()));
    }

    @Operation(summary = "Delete a user")
    @ApiResponse(responseCode = "200", description = "User deleted successfully",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiGenericResponse.class))})
    @DeleteMapping(value = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.ALL_VALUE)
    public ResponseEntity<ApiGenericResponse> deleteUser(@PathVariable("username") String username) throws ResourceNotFoundException {
        this.userManagementService.deleteUser(username);
        return ResponseEntity.ok().body(new ApiGenericResponse(true, "User '" + username + "' has been deleted successfully"));
    }
}
