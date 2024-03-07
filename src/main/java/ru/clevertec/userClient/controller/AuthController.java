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

}