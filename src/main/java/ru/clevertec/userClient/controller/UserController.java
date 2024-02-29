package ru.clevertec.userClient.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.ServletException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.userClient.dto.UserReq;
import ru.clevertec.userClient.dto.UserResp;
import ru.clevertec.userClient.enums.Role;

import ru.clevertec.userClient.service.UserService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/users")
@Tag(name = "User Controller", description = "User operations")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(
            method = "PUT",
            summary = "Update a user",
            description = "This method updates a user",
            parameters = @Parameter(
                    name = "id",
                    schema = @Schema(implementation = Long.class)
            ),
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserReq.class)
                    )
            ),
            responses = @ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserResp.class)
                    )
            )
    )
    @PutMapping("/{id}")
    public ResponseEntity<UserResp> update(@PathVariable Long id, @RequestBody UserReq req) {
        return ok(userService.update(id, req));
    }

    @Operation(
            method = "POST",
            summary = "Save a user",
            description = "This method saves a user",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserReq.class)
                    )
            ),
            responses = @ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserResp.class)
                    )
            )
    )
    @PostMapping
    public ResponseEntity<UserResp> save(@RequestBody UserReq userReqReq) {
        return ok(userService.save(userReqReq));
    }

    @Operation(
            method = "GET",
            summary = "Find a user by ID",
            description = "This method finds a user by their ID",
            parameters = @Parameter(
                    name = "id",
                    schema = @Schema(implementation = Long.class)
            ),
            responses = @ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserResp.class)
                    )
            )
    )
    @GetMapping("/{id}")
    public ResponseEntity<UserResp> findById(@PathVariable Long id) {
        return ok(userService.findById(id));
    }

    @Operation(
            method = "GET",
            summary = "Find a user by username",
            description = "This method finds a user by their username",
            parameters = @Parameter(
                    name = "username",
                    schema = @Schema(implementation = String.class)
            ),
            responses = @ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserResp.class)
                    )
            )
    )
    @GetMapping("/username/{username}")
    public ResponseEntity<UserResp> findByUsername(@PathVariable String username) {
        return ok(userService.findByUsername(username));
    }

    @Operation(
            method = "GET",
            summary = "Find users by role",
            description = "This method finds users by their role",
            parameters = @Parameter(
                    name = "role",
                    schema = @Schema(implementation = String.class)
            ),
            responses = @ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = List.class, type = "array", subTypes = UserResp.class)
                    )
            )
    )
    @GetMapping("/role/{role}")
    public ResponseEntity<List<UserResp>> findByRole(@PathVariable String role) {
        return ok(userService.findByRole(role));
    }

    @Operation(
            method = "GET",
            summary = "Load user by username",
            description = "This method loads a user by their username",
            parameters = @Parameter(
                    name = "username",
                    schema = @Schema(implementation = String.class)
            ),
            responses = @ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserDetails.class)
                    )
            )
    )
    @GetMapping("/usersDetails/{username}")
    public ResponseEntity<UserDetails> loadUserByUsername(String username) {
        return ok(userService.loadUserByUsername(username));
    }

}
