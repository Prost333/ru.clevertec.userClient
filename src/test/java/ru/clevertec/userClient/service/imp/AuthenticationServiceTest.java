package ru.clevertec.userClient.service.imp;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.clevertec.userClient.dto.UserReq;
import ru.clevertec.userClient.dto.UserResp;
import ru.clevertec.userClient.entity.User;
import ru.clevertec.userClient.enums.Role;
import ru.clevertec.userClient.exeption.EntityNotFoundException;
import ru.clevertec.userClient.security.JwtService;
import ru.clevertec.userClient.security.TokenService;
import ru.clevertec.userClient.service.UserService;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthenticationServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private JwtService jwtService;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Mock
    private HttpServletRequest httpServletRequest;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private TokenService tokenService;

    @InjectMocks
    private AuthenticationService authenticationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSignUp() {
        UserReq request = new UserReq();
        request.setUsername("username");
        request.setPassword("password");
        User user=new User();
        user.setPassword(request.getPassword());
        user.setUsername(request.getUsername());

        UserDetails userDetails = user;

        String encodedPassword = jwtService.generateToken(userDetails);
        when(passwordEncoder.encode(request.getPassword())).thenReturn(encodedPassword);

        UserResp expectedResponse = new UserResp("username", request.getPassword(), Role.SUBSCRIBER, encodedPassword);
        when(userService.save(request)).thenReturn(expectedResponse);

        UserResp signUpResponse = authenticationService.signUp(request);

        assertEquals(expectedResponse, signUpResponse);
        verify(userService).save(request);
    }


    @Test
    public void testSignIn() throws ServletException {
        UserReq request = new UserReq();
        request.setUsername("username");
        request.setPassword("password");
        User user=new User();
        user.setPassword(request.getPassword());
        user.setUsername(request.getUsername());

        UserDetails userDetails = user;

        String jwtToken = jwtService.generateToken(userDetails);
        when(jwtService.generateToken(userDetails)).thenReturn(jwtToken);

        UserResp userResp = new UserResp("username", "password", Role.SUBSCRIBER, jwtToken);
        when(userService.findByUsername(request.getUsername())).thenReturn(userResp);

        authenticationService.signIn(request);

        verify(httpServletRequest).login(request.getUsername(), request.getPassword());
        verify(authenticationManager).authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        verify(userService).loadUserByUsername(request.getUsername());
        verify(tokenService).setJwtToken(jwtToken);
        verify(tokenService).setUserResp(userResp);
    }

    @Test
    public void testSignIn_InvalidCredentials() {
        UserReq request = new UserReq();
        request.setUsername("");
        request.setPassword("");

        assertThrows(IllegalArgumentException.class, () -> authenticationService.signIn(request));
        verifyNoInteractions(httpServletRequest, authenticationManager, userService, jwtService, tokenService);
    }

    @Test
    public void testSignIn_FailLogin() throws ServletException {
        UserReq request = new UserReq();
        request.setUsername("username");
        request.setPassword("password");

        doThrow(new ServletException()).when(httpServletRequest).login(request.getUsername(), request.getPassword());

        assertThrows(EntityNotFoundException.class, () -> authenticationService.signIn(request));
        verify(httpServletRequest).login(request.getUsername(), request.getPassword());
        verifyNoMoreInteractions(authenticationManager, userService, jwtService, tokenService);
    }
}