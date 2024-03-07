package ru.clevertec.userClient.service.imp;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.clevertec.userClient.controller.NewsClient;
import ru.clevertec.userClient.dto.UserReq;
import ru.clevertec.userClient.dto.UserResp;
import ru.clevertec.userClient.entity.User;
import ru.clevertec.userClient.enums.Role;
import ru.clevertec.userClient.exeption.EntityNotFoundException;
import ru.clevertec.userClient.security.JwtService;
import ru.clevertec.userClient.security.TokenService;
import ru.clevertec.userClient.service.UserService;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

}