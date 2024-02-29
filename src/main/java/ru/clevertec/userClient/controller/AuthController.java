package ru.clevertec.userClient.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.ServletException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.userClient.dto.UserReq;
import ru.clevertec.userClient.dto.UserResp;
import ru.clevertec.userClient.service.imp.AuthenticationService;
import ru.clevertec.userClient.security.TokenService;
import ru.clevertec.userClient.service.UserService;


@RestController
@RequestMapping("/auth")
@Tag(name = "Auth Controller", description = "Authentication operations")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;
    private final TokenService tokenService;
    private final UserService userService;

    @PostMapping("/sign-up")

    public UserResp signUp(@RequestBody @Valid UserReq request) {
        return authenticationService.signUp(request);
    }

    @Operation(
            method = "POST",
            summary = "Sign up a user",
            description = "Sign up a user",
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
    @PostMapping("/sign-in")
    public UserResp signIn(@RequestBody @Valid UserReq request) {
        UserResp j = authenticationService.signIn(request);
        return j;
    }

    @Operation(
            method = "GET",
            summary = "Get JWT token",
            description = "This method returns the JWT token",
            responses = @ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = String.class)
                    )
            )
    )
    @GetMapping("/getBody")
    public String getBody() {
        return tokenService.getJwtToken();
    }

    @Operation(
            method = "POST",
            summary = "Logout a user",
            description = "This method logs out a user",
            responses = @ApiResponse(
                    description = "Success",
                    responseCode = "204",
                    content = @Content(
                            mediaType = "application/json"
                    )
            )
    )
    @PostMapping("/logout")
    public ResponseEntity<Void> logout() throws ServletException {
        userService.logout();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}